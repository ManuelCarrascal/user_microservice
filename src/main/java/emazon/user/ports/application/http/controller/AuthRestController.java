package emazon.user.ports.application.http.controller;

import emazon.user.infrastructure.configuration.security.AuthenticationService;
import emazon.user.ports.application.http.dto.AuthenticationRequest;
import emazon.user.ports.application.http.dto.AuthenticationResponse;
import emazon.user.ports.application.http.util.openapi.controller.AuthRestControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = AuthRestControllerConstants.TAG_NAME, description = AuthRestControllerConstants.TAG_DESCRIPTION)
public class AuthRestController {

    private final AuthenticationService authenticationService;

@PostMapping("/login")
@Operation(summary = AuthRestControllerConstants.OPERATION_SUMMARY)
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
    AuthenticationResponse response = authenticationService.authenticate(request);
    return ResponseEntity.ok(response);
}

}
