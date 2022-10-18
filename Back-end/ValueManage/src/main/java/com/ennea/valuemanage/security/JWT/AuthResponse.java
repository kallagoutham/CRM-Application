package com.ennea.valuemanage.security.JWT;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse{
    String username;
    String token;
    String Role;
}
