package emazon.user.infrastructure.configuration.security;

import emazon.user.infrastructure.configuration.security.jwtconfiguration.JwtService;
import emazon.user.ports.application.http.dto.AuthenticationRequest;
import emazon.user.ports.application.http.dto.AuthenticationResponse;
import emazon.user.ports.persistence.mysql.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import emazon.user.domain.exception.AuthenticationFailureException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUserEmail(),
                            request.getUserPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Long userId = userRepository.findByUserEmail(request.getUserEmail())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"))
                    .getUserId();

            String jwtToken = jwtService.generateToken(userDetails, userId);

            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (AuthenticationException e) {
            throw new AuthenticationFailureException("Invalid credentials");
        }
    }
}