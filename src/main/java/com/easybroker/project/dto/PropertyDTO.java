package com.easybroker.project.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {
    private String publicId;
    private String title;
    private String location;
    private Double price;
    private String currency;
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer parkingSpaces;
    private String propertyType;
    private Double lotSize;
    private Double constructionSize;
    private String agent;
    private String imageUrl;
}
