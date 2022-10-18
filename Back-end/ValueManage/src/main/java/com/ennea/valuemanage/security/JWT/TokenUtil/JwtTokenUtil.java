package com.ennea.valuemanage.security.JWT.TokenUtil;


import com.ennea.valuemanage.Model.security.Authority;
import com.ennea.valuemanage.Model.security.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtTokenUtil {
    private static final long EXPIRE_DURATION = 10*60*60*1000;// 10 hrs
    @Value("${app.jwt.secret}")
    private String secretKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    public String generateAccessToken(User user){
        Map<String, Object> claims = new HashMap<>();

        claims.put("roles",user.getAuthorities().toString().replace("[","").replace("]",""));
        System.out.println(claims.get("roles"));
        claims.put("sub",user.getId()+","+user.getUsername());
        return Jwts.builder()
                .setIssuer("Machine")
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512,secretKey)
                .compact();
    }


    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            LOGGER.error("JWT expired", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex) {
            LOGGER.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("JWT is not supported", ex);
        } catch (SignatureException ex) {
            LOGGER.error("Signature validation failed");
        }
        return false;
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }


}
