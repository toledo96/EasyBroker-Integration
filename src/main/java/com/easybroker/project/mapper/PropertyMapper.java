package com.easybroker.project.mapper;

import com.easybroker.project.dto.PropertyDTO;
import com.easybroker.project.model.Property;

public class PropertyMapper {

    public static Property toEntity(PropertyDTO dto) {
        return Property.builder()
                .publicId(dto.getPublicId())
                .title(dto.getTitle())
                .location(dto.getLocation())
                .price(dto.getPrice())
                .currency(dto.getCurrency())
                .bedrooms(dto.getBedrooms())
                .bathrooms(dto.getBathrooms())
                .parkingSpaces(dto.getParkingSpaces())
                .propertyType(dto.getPropertyType())
                .lotSize(dto.getLotSize())
                .constructionSize(dto.getConstructionSize())
                .agent(dto.getAgent())
                .imageUrl(dto.getImageUrl())
                .build();
    }
}

