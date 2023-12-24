package com.example.backend.domain.service;

import com.example.backend.infrastructure.util.Variable;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
    private static String SECRET_KEY;
    private static long ACCESS_EXPIRATION_TIME;
    private static long REFRESH_EXPIRATION_TIME;


    @Autowired
    public JwtService(Variable variable) {
        SECRET_KEY = variable.getSecretKey();
        REFRESH_EXPIRATION_TIME = Long.parseLong(variable.getRefreshTokenExpirationTime());
        ACCESS_EXPIRATION_TIME = Long.parseLong(variable.getAccessTokenExpirationTime());
    }


    public String createJwtTokenAndSendAsCookie(String email, HttpServletResponse response) {

        String refreshToken = this.createRefreshToken(email);
        String accessToken = this.createAccessToken(email);
        System.out.println(accessToken);

        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setDomain("localhost");
        cookie.setMaxAge((int) REFRESH_EXPIRATION_TIME);
        response.addCookie(cookie);

        return accessToken;
    }

    private String createAccessToken(String credentials) {
        return Jwts.builder()
                .setSubject(credentials)
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    private String createRefreshToken(String credentials) {
        return Jwts.builder()
                .setSubject(credentials)
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            String actualToken = token.startsWith("Bearer ") ? token.substring(7) : token;
            System.out.println(actualToken);
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(actualToken)
                    .getBody();
            System.out.println(claims.toString());
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }
}
