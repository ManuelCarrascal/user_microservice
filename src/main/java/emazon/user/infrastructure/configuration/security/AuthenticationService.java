package emazon.user.infrastructure.configuration.security;

import emazon.user.infrastructure.configuration.security.jwtconfiguration.JwtService;
import emazon.user.ports.application.http.dto.AuthenticationRequest;
import emazon.user.ports.application.http.dto.AuthenticationResponse;
import emazon.user.ports.persistence.mysql.entity.UserEntity;
import emazon.user.ports.persistence.mysql.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;

    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authRequest.getUserEmail(), authRequest.getUserPassword()
        );

        authenticationManager.authenticate(authToken);

        UserEntity user = userRepository.findByUserEmail(authRequest.getUserEmail()).orElseThrow(()-> new RuntimeException("User not found"));

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        return new AuthenticationResponse(jwt);

    }

    private Map<String, Object> generateExtraClaims(UserEntity user) {
      Map<String, Object> extraClaims = new HashMap<>();
      extraClaims.put("authorities",user.getAuthorities());
      return extraClaims;
    }
}