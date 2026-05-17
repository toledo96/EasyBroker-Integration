package com.easybroker.project.dto.parse;

import com.easybroker.project.dto.ContactDTO;
import com.easybroker.project.dto.LocalityDTO;
import com.easybroker.project.dto.LocationDTO;
import com.easybroker.project.dto.PropertyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EasyBrokerParser {

    private final ObjectMapper objectMapper;

    public List<PropertyDTO> parseProperties(String json) throws Exception {
        JsonNode root = objectMapper.readTree(json);
        JsonNode content = root.get("content");

        List<PropertyDTO> properties = new ArrayList<>();

        for (JsonNode node : content) {
            PropertyDTO dto = new PropertyDTO();
            dto.setPublicId(node.get("public_id").asText());
            dto.setTitle(node.get("title").asText());
            dto.setLocation(node.get("location").asText());
            dto.setBedrooms(node.get("bedrooms").isNull() ? null : node.get("bedrooms").asInt());
            dto.setBathrooms(node.get("bathrooms").isNull() ? null : node.get("bathrooms").asInt());
            dto.setParkingSpaces(node.get("parking_spaces").isNull() ? null : node.get("parking_spaces").asInt());
            dto.setPropertyType(node.get("property_type").asText());
            dto.setLotSize(node.get("lot_size").isNull() ? null : node.get("lot_size").asDouble());
            dto.setConstructionSize(node.get("construction_size").isNull() ? null : node.get("construction_size").asDouble());
            dto.setAgent(node.get("agent").asText());
            dto.setImageUrl(node.get("title_image_full").asText());

            JsonNode operations = node.get("operations").get(0);
            dto.setPrice(operations.get("amount").asDouble());
            dto.setCurrency(operations.get("currency").asText());

            properties.add(dto);
        }
        return properties;
    }

    public List<ContactDTO> parseContacts(String json) throws Exception {
        JsonNode root = objectMapper.readTree(json);
        JsonNode content = root.get("content");

        List<ContactDTO> contacts = new ArrayList<>();
        for (JsonNode node : content) {
            ContactDTO dto = new ContactDTO();
            dto.setId(node.get("id").asInt());
            dto.setFullName(node.get("full_name").asText());
            dto.setPhone(node.get("phone").asText());
            dto.setEmail(node.get("email").asText());
            dto.setAgent(node.get("agent").asText());
            dto.setSource(node.get("source").asText());
            dto.setCreatedAt(node.get("created_at").asText());
            dto.setUpdatedAt(node.get("updated_at").asText());
            contacts.add(dto);
        }
        return contacts;
    }

    public LocationDTO parseLocations(String json) throws Exception {
        JsonNode root = objectMapper.readTree(json);

        LocationDTO dto = new LocationDTO();
        dto.setName(root.get("name").asText());
        dto.setFullName(root.get("full_name").asText());
        dto.setType(root.get("type").asText());

        JsonNode localitiesNode = root.get("localities");
        if (localitiesNode != null && localitiesNode.isArray()) {
            List<LocalityDTO> localities = new ArrayList<>();
            for (JsonNode locNode : localitiesNode) {
                LocalityDTO locality = new LocalityDTO();
                locality.setName(locNode.get("name").asText());
                locality.setFullName(locNode.get("full_name").asText());
                locality.setType(locNode.get("type").asText());
                localities.add(locality);
            }
            dto.setLocalities(localities);
        }

        return dto;
    }

}
