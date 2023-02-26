package org.mystore.helper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.ZonedDateTime;
import java.util.Date;

public class JwtTokenGenerator {

    public static String jwtToken(String tokenClaim)
    {
        return Jwts.builder()
                .setSubject("Any subject we can set like login")
                .claim("phone",tokenClaim)
                .setIssuedAt(new Date(new Date().getTime())).setExpiration(Date.from(ZonedDateTime.now().plusMinutes(10).toInstant()))
                .signWith(SignatureAlgorithm.HS256,"my secret key").compact();

    }

}