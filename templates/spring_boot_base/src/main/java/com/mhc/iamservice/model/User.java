package com.mhc.iamservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import java.util.Map;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;

    private String username;
    private String email;
    private String password;

    private Set<Role> roles;

    // Flexible profile (schema-less advantage of Mongo)
    private Map<String, Object> profile;

    @Builder.Default
    private boolean enabled = true;
}

