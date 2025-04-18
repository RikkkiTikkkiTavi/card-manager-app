package com.example.service.web.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 *  Фильтр для обработки входящих jwt requests
 */
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private final JwtTokenUtils jwtTokenUtils;

    /**
     * Проверяем валидность подписи, проверяем жив ли еще токен.
     * Так же здесь происходит проверка данных вшитых в токен.
     * Происходит получение id пользователя из токена и передача его в качестве атрибута запроса
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        Long userId = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            try {
                username = jwtTokenUtils.getUsername(jwt);
                userId = jwtTokenUtils.getUserId(jwt);

            } catch (ExpiredJwtException e) {
                handleAuthenticationException(response, "TOKEN_EXPIRED", "Token expired");
                return;
            } catch (SignatureException e) {
                handleAuthenticationException(response, "INVALID_SIGNATURE", "Invalid token signature");
                return;
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    jwtTokenUtils.getRoles(jwt).stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList())
            );
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        if (userId != null) {
            request.setAttribute("userId", userId);
        }
        filterChain.doFilter(request, response);
    }

    private void handleAuthenticationException(HttpServletResponse response, String error, String description)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(
                String.format("{\"error\":\"%s\",\"description\":\"%s\"}", error, description)
        );
    }
}
