package com.easybroker.project.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {
    private Integer id;
    private String fullName;
    private String phone;
    private String email;
    private String agent;
    private String source;
    private String createdAt;
    private String updatedAt;
}