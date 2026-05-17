package com.easybroker.project.service;

import com.easybroker.project.dto.ContactDTO;
import com.easybroker.project.dto.LocationDTO;
import com.easybroker.project.dto.PropertyDTO;
import com.easybroker.project.dto.response.CombinedResponse;
import com.easybroker.project.webclient.EasyBrokerClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class EasyBrokerConcurrentServiceTest {

    @Mock
    private EasyBrokerClient client;

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private EasyBrokerConcurrentService easyBrokerConcurrentService;

    @Test
    void syncAllPages() {
        PropertyDTO p1 = new PropertyDTO();
        PropertyDTO p2 = new PropertyDTO();
        PropertyDTO p3 = new PropertyDTO();

        Mockito.when(client.getProperties(1)).thenReturn(List.of(p1));
        Mockito.when(client.getProperties(2)).thenReturn(List.of(p2));
        Mockito.when(client.getProperties(3)).thenReturn(List.of(p3));

        easyBrokerConcurrentService.syncAllPages();

        Mockito.verify(propertyService).saveProperties(List.of(p1,p2,p3));
    }

    @Test
    void syncResources() {

        PropertyDTO p1 = new PropertyDTO();
        ContactDTO c1 = new ContactDTO();
        LocationDTO l1 = new LocationDTO();

        Mockito.when(client.getProperties(1)).thenReturn(List.of(p1));
        Mockito.when(client.getContacts()).thenReturn(List.of(c1));
        Mockito.when(client.getLocations()).thenReturn(l1);

        CombinedResponse response = easyBrokerConcurrentService.syncResources();

        assertNotNull(response);
        assertEquals(1, response.getProperties().size());
        assertEquals(p1, response.getProperties().get(0));

        assertEquals(1, response.getContacts().size());
        assertEquals(c1, response.getContacts().get(0));

        assertEquals(l1, response.getLocation());

    }
}