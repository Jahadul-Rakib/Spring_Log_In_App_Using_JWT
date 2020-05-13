package com.rakib.utilities;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class JWTUtilities {


    public String jwtTokenProvider() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated()) {
            System.out.println("Not Authenticate !!!");
        }

        String Key = "SECUREOFFNOSECURITYqqqqqqqqqqwwwwwwwwwwwwwwweeeeeeeeeeeeeeeeee1111111111111111111333333333333333333333333333";
        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", authentication.getAuthorities().toString())
                .setIssuedAt(new java.util.Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                .signWith(Keys.hmacShaKeyFor(Key.getBytes()))
                .compact();

        return token;

    }

}
