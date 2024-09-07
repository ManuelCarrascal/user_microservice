package emazon.user.ports.application.http.controller;

import emazon.user.domain.api.IAuthServicePort;
import emazon.user.ports.application.http.dto.AuthenticationRequest;
import emazon.user.ports.application.http.dto.AuthenticationResponse;
import emazon.user.ports.application.http.util.openapi.controller.AuthRestControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = AuthRestControllerConstants.TAG_NAME, description = AuthRestControllerConstants.TAG_DESCRIPTION)
public class AuthRestController {

    private final IAuthServicePort authServicePort;

@PostMapping("/login")
@Operation(summary = AuthRestControllerConstants.OPERATION_SUMMARY)
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
    String token =authServicePort.login(request.getUserEmail(), request.getUserPassword());
    AuthenticationResponse response = new AuthenticationResponse(token);
    return ResponseEntity.ok(response);
}

    @PostMapping("/validate-token/{token}")
    public ResponseEntity<String> validateToken(@PathVariable String token) {
        return ResponseEntity.ok().body(token);
    }

}
