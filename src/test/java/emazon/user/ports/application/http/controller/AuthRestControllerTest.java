package emazon.user.ports.application.http.controller;

import emazon.user.domain.api.IAuthServicePort;
import emazon.user.ports.application.http.dto.AuthenticationRequest;
import emazon.user.ports.application.http.dto.AuthenticationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AuthRestControllerTest {

    @Mock
    private IAuthServicePort authServicePort;

    @InjectMocks
    private AuthRestController authRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void authenticate() {
        String email = "test@example.com";
        String password = "password";
        String token = "mockedToken";

        AuthenticationRequest request = new AuthenticationRequest();
        request.setUserEmail(email);
        request.setUserPassword(password);

        when(authServicePort.login(email, password)).thenReturn(token);

        ResponseEntity<AuthenticationResponse> responseEntity = authRestController.authenticate(request);

        AuthenticationResponse expectedResponse = new AuthenticationResponse(token);
        AuthenticationResponse actualResponse = responseEntity.getBody();

        assert actualResponse != null;
        assertEquals(expectedResponse.getToken(), actualResponse.getToken());
    }
}
