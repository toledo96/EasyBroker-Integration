package com.easybroker.project.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "properties")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
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
