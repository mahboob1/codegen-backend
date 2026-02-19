package com.mhc.iamservice.security;

import com.mhc.iamservice.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final JwtConfig jwtConfig;

//    private Key getKey() {
//        return Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8));
//    }

//    private Key getKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(jwtConfig.getSecret()); // secret is base64-encoded
//        return Keys.hmacShaKeyFor(keyBytes);
//    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtConfig.getSecret()); // secret is base64-encoded
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // 1. Generate token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    // 2. Extract username
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // 3. Validate token
    public boolean validateToken(String token) {
        try {
            extractClaims(token);   // will throw if invalid/expired
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // 4. Check expiration
    public boolean isTokenExpired(String token) {
        return extractClaims(token)
                .getExpiration()
                .before(new Date());
    }

    // 5. Core parser
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getKey())              // ✅ new way
                .build()
                .parseSignedClaims(token)     // ✅ new way
                .getPayload();
    }
}
