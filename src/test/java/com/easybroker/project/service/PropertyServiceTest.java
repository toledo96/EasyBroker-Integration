package com.easybroker.project.service;

import com.easybroker.project.dto.PropertyDTO;
import com.easybroker.project.model.Property;
import com.easybroker.project.repository.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class PropertyServiceTest {

    @InjectMocks
    private PropertyService propertyService;

    @Mock
    private PropertyRepository propertyRepository;


    @Test
    void saveProperties() {
        List<Property> properties = new ArrayList<>();

        Property property = Property.builder()
                .title("")
                .price(1234.32)
                .bathrooms(2)
                .agent("")
                .bedrooms(3)
                .propertyType("")
                .id(1L)
                .constructionSize(46.00)
                .location("Tuxtla")
                .imageUrl("")
                .lotSize(2322.32)
                .parkingSpaces(12)
                .publicId("2456tddf4")
                .build();

        properties.add(property);

        List<PropertyDTO> propertiesDto = new ArrayList<>();


        Mockito.when(propertyRepository.saveAll(Mockito.anyCollection()))
                .thenReturn(properties);

        propertyService.saveProperties(propertiesDto);

        assertEquals(1,properties.size());


        Mockito.verify(propertyRepository).saveAll(Mockito.anyCollection());

    }
}