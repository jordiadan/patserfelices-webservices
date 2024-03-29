package com.example.patserfelices.auth.security.jwt;

import com.example.patserfelices.auth.AppConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider {
    private final String secretKey;
    private final long tokenValidityInMilliseconds;
    private final UserDetailsService userService;

    public TokenProvider(AppConfig config, UserDetailsService userService) {
        this.secretKey = Base64.getEncoder().encodeToString(config.getSecret().getBytes());
        this.tokenValidityInMilliseconds = 1000L * config.getTokenValidityInSeconds();
        this.userService = userService;
    }

    public String createToken(String username) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + this.tokenValidityInMilliseconds);
        return Jwts.builder().setId(UUID.randomUUID().toString()).setSubject(username).setIssuedAt(now).signWith(SignatureAlgorithm.HS512, this.secretKey).setExpiration(validity).compact();
    }

    public Authentication getAuthentication(String token) {
        String username = ((Claims)Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody()).getSubject();
        UserDetails userDetails = this.userService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
