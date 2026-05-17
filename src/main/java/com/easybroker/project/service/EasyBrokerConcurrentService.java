package com.easybroker.project.service;

import com.easybroker.project.dto.ContactDTO;
import com.easybroker.project.dto.LocationDTO;
import com.easybroker.project.dto.PropertyDTO;
import com.easybroker.project.dto.response.CombinedResponse;
import com.easybroker.project.webclient.EasyBrokerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class EasyBrokerConcurrentService {

    private final EasyBrokerClient client;
    private final PropertyService propertyService;


    public void syncAllPages() {
        CompletableFuture<List<PropertyDTO>> page1 = CompletableFuture.supplyAsync(() -> client.getProperties(1));
        CompletableFuture<List<PropertyDTO>> page2 = CompletableFuture.supplyAsync(() -> client.getProperties(2));
        CompletableFuture<List<PropertyDTO>> page3 = CompletableFuture.supplyAsync(() -> client.getProperties(3));
        List<PropertyDTO> allProperties = CompletableFuture.allOf(page1, page2, page3)
                .thenApply(v -> {
                    List<PropertyDTO> result = new ArrayList<>();
                    result.addAll(page1.join());
                    result.addAll(page2.join());
                    result.addAll(page3.join());
                    return result;
                }).join();

        propertyService.saveProperties(allProperties);
    }

    public CombinedResponse syncResources(){
        CompletableFuture<List<PropertyDTO>> propertiesFuture =
                CompletableFuture.supplyAsync(() -> client.getProperties(1));

        CompletableFuture<List<ContactDTO>>  contactsFuture =
                CompletableFuture.supplyAsync(() -> client.getContacts());

        CompletableFuture<LocationDTO> locationFuture =
                CompletableFuture.supplyAsync(() -> client.getLocations());

        CompletableFuture.allOf(propertiesFuture,contactsFuture,locationFuture);

        CombinedResponse response = new CombinedResponse();
        response.setProperties(propertiesFuture.join());
        response.setContacts(contactsFuture.join());
        response.setLocation(locationFuture.join());

        return response;
    }

}
