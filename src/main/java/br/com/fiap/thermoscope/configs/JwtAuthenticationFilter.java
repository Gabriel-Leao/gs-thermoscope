package br.com.fiap.thermoscope.configs;

import br.com.fiap.thermoscope.entities.User;
import br.com.fiap.thermoscope.exceptions.ErrorResponse;
import br.com.fiap.thermoscope.exceptions.UnauthorizedException;
import br.com.fiap.thermoscope.models.UserDetailsImpl;
import br.com.fiap.thermoscope.repositories.UserRepository;
import br.com.fiap.thermoscope.services.auth.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (checkIfEndpointIsNotPublic(request)) {
            String token = recoveryToken(request);
            if (token != null) {
                try {
                    String subject = jwtService.validateToken(token);
                    User user = userRepository.findUserByEmail(subject)
                            .orElseThrow(() -> new UnauthorizedException("User not found"));

                    UserDetailsImpl userDetails = new UserDetailsImpl(user);
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(), null, userDetails.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                } catch (Exception ex) {
                    handleException(response, request, ex);
                    return;
                }
            } else {
                handleException(response, request, new UnauthorizedException("Token is missing or invalid"));
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void handleException(HttpServletResponse response, HttpServletRequest request, Exception ex) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "Unauthorized",
                ex.getMessage(),
                request.getRequestURI()
        );

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.getWriter().write(new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(errorResponse));
    }


    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String method = request.getMethod();
        String path = request.getRequestURI();
        AntPathMatcher pathMatcher = new AntPathMatcher();

        boolean isWhitelisted = Arrays.stream(SecurityConfig.PATH_WHITELIST)
                .anyMatch(whitelistPath -> pathMatcher.match(whitelistPath, path));

        boolean isUserCreation = method.equals("POST") && path.equals("/users");

        return !(isWhitelisted || isUserCreation);
    }
}
