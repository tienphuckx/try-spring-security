package com.tienphuckx.AuthenticateJWT;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;

//@SpringBootTest
public class JwtSecretMakerTest {
    @Test
    public void generateJwtSecret() {
        SecretKey secretKey = Jwts.SIG.HS512.key().build();
        String encodedSecretKey = DatatypeConverter.printHexBinary(secretKey.getEncoded());
        System.out.printf("\nSecretKey: [%s]\n", encodedSecretKey);
    }
}
