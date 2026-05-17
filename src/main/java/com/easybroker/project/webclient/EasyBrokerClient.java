package com.easybroker.project.webclient;

import com.easybroker.project.dto.ContactDTO;
import com.easybroker.project.dto.parse.EasyBrokerParser;
import com.easybroker.project.dto.LocationDTO;
import com.easybroker.project.dto.PropertyDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Component
public class EasyBrokerClient {

    private final WebClient webClient;
    private final EasyBrokerParser parser;

    public EasyBrokerClient(WebClient webClient, EasyBrokerParser parser) {
        this.webClient = webClient;
        this.parser = parser;
    }


    public List<PropertyDTO> getProperties(int page) {
        String json = webClient.get()
                .uri("/properties?page=" + page + "&limit=20")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            return parser.parseProperties(json);
        } catch (WebClientResponseException ex) {
            throw new RuntimeException("Error en EasyBroker: " + ex.getStatusCode() + " - " + ex.getResponseBodyAsString(), ex);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<ContactDTO> getContacts() {
        String json = webClient.get()
                .uri("/contacts?page=1&limit=20")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            return parser.parseContacts(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public LocationDTO getLocations() {
        String json=  webClient.get()
                .uri("/locations")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            return parser.parseLocations(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
