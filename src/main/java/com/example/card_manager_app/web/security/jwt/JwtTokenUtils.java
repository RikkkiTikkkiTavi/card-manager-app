package com.example.card_manager_app.web.security.jwt;

import com.example.card_manager_app.web.security.service.SecurityUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenUtils {

    private final JwtProperties jwtProperties;

    public String generateToken(SecurityUser securityUser) {

        Map<String, Object> claims = new HashMap<>();

        List<String> rolesList = securityUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        Long userId = securityUser.getId();

        claims.put("roles", rolesList);
        claims.put("userId", userId);

        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + 900000000000L);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(securityUser.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .compact();
    }

    public String getUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Long getUserId(String token) {
        return getAllClaimsFromToken(token).get("userId", Long.class);
    }

    public List<String> getRoles(String token) {
        return getAllClaimsFromToken(token).get("roles", List.class);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }
}
