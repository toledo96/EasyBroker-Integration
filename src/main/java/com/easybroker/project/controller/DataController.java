package com.easybroker.project.controller;

import com.easybroker.project.dto.ContactDTO;
import com.easybroker.project.dto.LocationDTO;
import com.easybroker.project.dto.PropertyDTO;
import com.easybroker.project.webclient.EasyBrokerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DataController {

    private final EasyBrokerClient client;

    @GetMapping("/contacts")
    public List<ContactDTO> getContacts() {
        return client.getContacts();
    }

    @GetMapping("/properties")
    public List<PropertyDTO> getProperties(@RequestParam(defaultValue = "1") int page) {
        return client.getProperties(page);
    }

    @GetMapping("/locations")
    public LocationDTO getLocations(){
        return client.getLocations();
    }



}