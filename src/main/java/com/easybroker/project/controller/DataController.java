package com.easybroker.project.controller;

import com.easybroker.project.dto.ContactDTO;
import com.easybroker.project.dto.LocationDTO;
import com.easybroker.project.dto.PropertyDTO;
import com.easybroker.project.webclient.EasyBrokerClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DataController {

    private final EasyBrokerClient client;

    @Operation(
            summary = "Obtener contactos",
            description = "Devuelve la lista de contactos desde la API de EasyBroker"
    )
    @ApiResponse(responseCode = "200", description = "Lista de contactos obtenida correctamente")
    @GetMapping("/contacts")
    public List<ContactDTO> getContacts() {
        return client.getContacts();
    }

    @Operation(
            summary = "Obtener propiedades",
            description = "Devuelve la lista de propiedades desde la API de EasyBroker. Se puede especificar la página."
    )
    @ApiResponse(responseCode = "200", description = "Lista de propiedades obtenida correctamente")
    @GetMapping("/properties")
    public List<PropertyDTO> getProperties(@RequestParam(defaultValue = "1") int page) {
        return client.getProperties(page);
    }

    @Operation(
            summary = "Obtener ubicaciones",
            description = "Devuelve las ubicaciones disponibles desde la API de EasyBroker"
    )
    @ApiResponse(responseCode = "200", description = "Ubicaciones obtenidas correctamente")
    @GetMapping("/locations")
    public LocationDTO getLocations(){
        return client.getLocations();
    }

}