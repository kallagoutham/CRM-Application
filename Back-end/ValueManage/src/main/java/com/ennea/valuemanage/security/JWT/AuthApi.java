package com.ennea.valuemanage.security.JWT;

import com.ennea.valuemanage.security.JWT.TokenUtil.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import com.ennea.valuemanage.Model.security.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthApi {
    AuthenticationManager authManager;
    JwtTokenUtil jwtTokenUtil;

    public AuthApi(AuthenticationManager authManager,JwtTokenUtil jwtTokenUtil) {
        this.authManager = authManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("auth/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest){
        try{
            Authentication authentication= authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
            User user = (User) authentication.getPrincipal();
//            System.out.println(user.toString());
            String token = jwtTokenUtil.generateAccessToken(user);
            return ResponseEntity.ok(AuthResponse.builder().username(user.getUsername()).token(token).Role(user.getAuthorities().toString().replace("[","").replace("]","")).build());
        }
        catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
