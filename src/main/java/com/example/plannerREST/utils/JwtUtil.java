package com.example.plannerREST.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtUtil {

    private final static SecretKey signKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final static int tokenDuration = 900000;

    public String createToken(UserDetails userDetails, String username) {

        Map<String, Object> claims = new HashMap<>();

        List<String> authorities = userDetails.getAuthorities().stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());
        claims.put("authorities", authorities);

        return Jwts
                .builder()
                .setHeaderParam("typ", "JWT")
                .addClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenDuration))
                .signWith(signKey)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {

        try {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (ExpiredJwtException e) {
            throw e;
        }
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token).getBody();
    }

    public String updateExpirationDateToken(String token) {

        final Claims claims = extractAllClaims(token);

        return Jwts
                .builder()
                .setHeaderParam("typ", "JWT")
                .addClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenDuration))
                .signWith(signKey)
                .compact();

    }
}
