package com.easybroker.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LocationDTO {

    private String name;

    private String fullName;

    private String type;

    private List<LocalityDTO> localities =new ArrayList<>();

}
