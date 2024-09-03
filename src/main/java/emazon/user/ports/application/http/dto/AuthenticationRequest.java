package emazon.user.ports.application.http.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequest {
    private String userEmail;
    private String userPassword;
}
