package com.easybroker.project.controller;

import com.easybroker.project.dto.response.CombinedResponse;
import com.easybroker.project.service.EasyBrokerConcurrentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sync")
@RequiredArgsConstructor
public class SyncController {

    private final EasyBrokerConcurrentService easyBrokerConcurrentService;


    @GetMapping("/syncPages")
    public String syncProperties() {
        easyBrokerConcurrentService.syncAllPages();
        return "Propiedades sincronizadas en BD";
    }
    @GetMapping("/syncAll")
    public CombinedResponse syncAll() {
        return easyBrokerConcurrentService.syncResources();
    }
}