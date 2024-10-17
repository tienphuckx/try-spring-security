package com.tienphuckx.AuthenticateJWT.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {
    private static final String
            SECRET_KEY = "5C310264C29B7B4F1E991CB2DB365F9788CD7D57B5F1B168565F7D7DA2515B474FD19E1C11D9189AF7B9E16A6C5C8E53FC7A1406EB35F74588160E3281C6F9BC";
    private static final Long TOKEN_VALIDITY = TimeUnit.MINUTES.toMillis(30);

    public String generateToken(UserDetails userDetails) {

        // build clams
        Map<String, String> claims = new HashMap<>();
        claims.put("sub", userDetails.getUsername());
        claims.put("id", userDetails.getUsername());
        claims.put("iss", "https://tienphuckx.ueuo.com");

        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(TOKEN_VALIDITY)))
                .signWith(getSecretKey())
                .compact(); //final to json format
    }

    private SecretKey getSecretKey() {
        byte[] decodeKeyBytes = Base64.getDecoder().decode(SECRET_KEY); //convert the SECRET_KEY to SecretKey obj
        return Keys.hmacShaKeyFor(decodeKeyBytes);
    }

}
