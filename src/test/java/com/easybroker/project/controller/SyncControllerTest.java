package com.easybroker.project.controller;

import com.easybroker.project.dto.ContactDTO;
import com.easybroker.project.dto.LocationDTO;
import com.easybroker.project.dto.PropertyDTO;
import com.easybroker.project.dto.response.CombinedResponse;
import com.easybroker.project.service.EasyBrokerConcurrentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(SyncController.class)
class SyncControllerTest {

    @MockitoBean
    private EasyBrokerConcurrentService easyBrokerConcurrentService;

    @Autowired
    private MockMvc mockMvc;



    @Test
    void syncProperties() throws Exception {

        Mockito.doNothing().when(easyBrokerConcurrentService).syncAllPages();

        mockMvc.perform(get("/api/sync/syncPages")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string("Propiedades sincronizadas en BD"));
    }

    @Test
    void syncAll() throws Exception {

        PropertyDTO p1 = new PropertyDTO();
        ContactDTO c1 = new ContactDTO();
        LocationDTO l1 = new LocationDTO();

        CombinedResponse response = CombinedResponse.builder()
                .properties(List.of(p1))
                .contacts(List.of(c1))
                .location(l1)
                .build();

        Mockito.when(easyBrokerConcurrentService.syncResources()).thenReturn(response);

        mockMvc.perform(get("/api/sync/syncAll")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}