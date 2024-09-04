package emazon.user.ports.application.http.controller;

import emazon.user.infrastructure.configuration.security.AuthenticationService;
import emazon.user.ports.application.http.dto.AuthenticationRequest;
import emazon.user.ports.application.http.dto.AuthenticationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AuthRestControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthRestController authRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void authenticate_shouldReturnAuthenticationResponse() {
        AuthenticationRequest request = new AuthenticationRequest("test@example.com", "password123");
        AuthenticationResponse expectedResponse = new AuthenticationResponse("testToken");

        when(authenticationService.authenticate(request)).thenReturn(expectedResponse);

        ResponseEntity<AuthenticationResponse> response = authRestController.authenticate(request);

        assertEquals(ResponseEntity.ok(expectedResponse), response);
    }

}