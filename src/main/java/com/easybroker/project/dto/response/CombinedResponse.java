package com.easybroker.project.dto.response;

import com.easybroker.project.dto.ContactDTO;
import com.easybroker.project.dto.LocationDTO;
import com.easybroker.project.dto.PropertyDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CombinedResponse {
    private List<PropertyDTO> properties;
    private List<ContactDTO> contacts;
    private LocationDTO location;
}
