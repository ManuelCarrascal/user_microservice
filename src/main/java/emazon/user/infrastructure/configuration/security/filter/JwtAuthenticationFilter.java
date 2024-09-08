package emazon.user.infrastructure.configuration.security.filter;

import emazon.user.infrastructure.configuration.util.AuthenticationFilterConstants;
import emazon.user.ports.persistence.mysql.util.JwtService;
import emazon.user.ports.persistence.mysql.entity.UserEntity;
import emazon.user.ports.persistence.mysql.repository.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(AuthenticationFilterConstants.AUTH_HEADER);

        if (authHeader == null || !authHeader.startsWith(AuthenticationFilterConstants.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        String jwt = authHeader.substring(AuthenticationFilterConstants.TOKEN_PREFIX_LENGTH);
        try {
            if (!jwtService.isTokenValid(jwt)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, AuthenticationFilterConstants.INVALID_TOKEN);
                return;
            }
            String username = jwtService.extractUsername(jwt);
            UserEntity user = userRepository.findByUserId(Long.parseLong(username))
                    .orElseThrow(() -> new RuntimeException(AuthenticationFilterConstants.USER_NOT_FOUND));
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, AuthenticationFilterConstants.INVALID_TOKEN);
            return;
        }

        filterChain.doFilter(request, response);
    }
}