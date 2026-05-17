package com.easybroker.project.service;

import com.easybroker.project.dto.PropertyDTO;
import com.easybroker.project.mapper.PropertyMapper;
import com.easybroker.project.model.Property;
import com.easybroker.project.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;


    public void saveProperties(List<PropertyDTO> propertyDTOs) {
        List<Property> properties = propertyDTOs.stream()
                .map(PropertyMapper::toEntity)
                .collect(Collectors.toList());

        propertyRepository.saveAll(properties);
    }



}