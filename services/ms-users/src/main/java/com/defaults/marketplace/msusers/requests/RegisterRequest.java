package com.defaults.marketplace.msusers.requests;

import com.defaults.marketplace.msusers.models.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 20, message = "First name must be between 3 and 20 characters")
    private String firstName;
    @NotBlank(message = "Last name is required")
    @Size(min = 3, max = 20, message = "Last name must be between 3 and 20 characters")
    private String lastName;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String birthday;
    private String pictureUrl;
    @Size(max = 200, message = "Description must be less than 200 characters")
    private String description;
    private UserRole role;
}
