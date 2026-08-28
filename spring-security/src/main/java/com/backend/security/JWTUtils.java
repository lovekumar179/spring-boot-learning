package com.backend.security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JWTUtils {
  private String jwtSecret = "YS1zdHJpbmctc2VjcmV0LWF0LWxlYXN0LTI1Ni1iaXRzLWxvbmc=";
  private int jwtExpirationsMs = 172800000; // 48hrs(ms):172800000

  public String getJwtFromHeader() {
    return "";
  }

  public String generateTokenFormUsername(String userName){
    return Jwts.builder()
            .subject(userName)
            .issuedAt(new Date())
            .expiration(new Date(new Date().getTime() + jwtExpirationsMs))
            .signWith(key())
            .compact();
  }

  public boolean validateJwtToken(){
    return true;
  }

  public Key key(){
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }


}
