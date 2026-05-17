package com.easybroker.project.controller;

import com.easybroker.project.dto.response.CombinedResponse;
import com.easybroker.project.service.EasyBrokerConcurrentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sync")
@RequiredArgsConstructor
public class SyncController {

    private final EasyBrokerConcurrentService easyBrokerConcurrentService;


    @Operation(
            summary = "Sincronizar páginas de propiedades",
            description = "Obtiene varias páginas de propiedades desde EasyBroker en paralelo y las guarda en la base de datos."
    )
    @ApiResponse(responseCode = "200", description = "Propiedades sincronizadas correctamente en la BD")
    @GetMapping("/syncPages")
    public String syncProperties() {
        easyBrokerConcurrentService.syncAllPages();
        return "Propiedades sincronizadas en BD";
    }
    @Operation(
            summary = "Sincronizar todos los recursos",
            description = "Obtiene propiedades, contactos y ubicaciones desde EasyBroker de forma concurrente y devuelve un JSON combinado."
    )
    @ApiResponse(responseCode = "200", description = "Datos sincronizados correctamente")
    @GetMapping("/syncAll")
    public CombinedResponse syncAll() {
        return easyBrokerConcurrentService.syncResources();
    }
}