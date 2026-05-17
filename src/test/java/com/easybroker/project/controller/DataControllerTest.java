package com.easybroker.project.controller;

import com.easybroker.project.dto.ContactDTO;
import com.easybroker.project.dto.LocationDTO;
import com.easybroker.project.dto.PropertyDTO;
import com.easybroker.project.webclient.EasyBrokerClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(DataController.class)
class DataControllerTest {

    @MockitoBean
    private EasyBrokerClient client;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getContacts() throws Exception {

        List<ContactDTO> contacts = new ArrayList<>();
        ContactDTO contact = ContactDTO.builder()
                .id(1)
                .agent("agente")
                .phone("45678825")
                .email("agent@gmail.com")
                .fullName("agente")
                .createdAt(LocalDateTime.now().toString())
                .updatedAt(LocalDateTime.now().toString())
                .source("source")
                .build();

        contacts.add(contact);

        Mockito.when(client.getContacts()).thenReturn(contacts);

        mockMvc.perform(get("/api/v1/contacts")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("agent@gmail.com"));

    }

    @Test
    void getProperties() throws Exception {
        List<PropertyDTO> properties = new ArrayList<>();
        PropertyDTO property= PropertyDTO.builder()
                .publicId("EB-SC9592")
                .title("Modern Apartment")
                .location("Mexico City")
                .price(2500000.0)
                .currency("MXN")
                .bedrooms(3)
                .bathrooms(2)
                .parkingSpaces(1)
                .propertyType("Apartment")
                .lotSize(120.0)
                .constructionSize(95.0)
                .agent("Juan Pérez")
                .imageUrl("https://example.com/image.jpg")
                .build();

        properties.add(property);

        Mockito.when(client.getProperties(1)).thenReturn(properties);

        mockMvc.perform(get("/api/v1/properties")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Modern Apartment"));
    }

    @Test
    void getLocations() throws Exception {
        LocationDTO location = new LocationDTO();
        location.setName("Mexico City");
        location.setFullName("Ciudad de México");
        location.setType("city");

        Mockito.when(client.getLocations()).thenReturn(location);

        mockMvc.perform(get("/api/v1/locations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mexico City"))
                .andExpect(jsonPath("$.fullName").value("Ciudad de México"))
                .andExpect(jsonPath("$.type").value("city"));
    }


}