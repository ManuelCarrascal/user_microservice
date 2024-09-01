package emazon.user.ports.application.http.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponse {
    private String userName;
    private String userLastName;
    private String userIdentityDocument;
    private String userPhone;
    private String userEmail;
    private LocalDate userBirthdate;
}
