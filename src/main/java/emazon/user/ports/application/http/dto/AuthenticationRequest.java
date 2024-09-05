package emazon.user.ports.application.http.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String userEmail;
    @NotBlank(message = "Password is required")
    private String userPassword;
}
