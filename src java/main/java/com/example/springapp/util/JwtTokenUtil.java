package com.example.springapp.util;

import java.security.SignatureException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.springapp.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
 
@Component
public class JwtTokenUtil {

    @Value("${app.jwt.secretKey}")
    private String SECRET_KEY;

    @Value("${app.jwt.expirationTime}")
    private long EXPIRATION_TIME;

    private final String AUTHORIZATION = "Authorization";
    private final String BEARER = "Bearer";

    public String getUsername(String token) {
        return parseClaims(token).getSubject();
    }

    public String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith(BEARER)) {
            return bearerToken.substring(7).trim();
        }
        return null;
    }

    public String createToken(User user) {
        String email = user.getEmail();
        Claims claims = Jwts.claims().setSubject(email);
        String role = user.getUserRole();
        List<String> roles = Arrays.asList(role);

        return Jwts.builder()
                .claim("roles", roles)
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(SECRET_KEY.getBytes()))
                .compact();
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.err.println("JWT error: " + e.getMessage());
        }

        return false;
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    @SuppressWarnings("unchecked")
    public List<String> getRoles(String token) {
        return (List<String>) parseClaims(token).get("roles");
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isTokenExpired(String token) {
        return parseClaims(token).getExpiration().before(new Date());
    }
}
