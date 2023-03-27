package com.defaults.marketplace.msusers.models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String birthday;
    private String pictureUrl;
    private String description;

    @Enumerated(EnumType.STRING)
    private UserRole role;

}
