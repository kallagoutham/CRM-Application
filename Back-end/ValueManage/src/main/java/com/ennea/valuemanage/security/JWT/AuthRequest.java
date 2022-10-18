package com.ennea.valuemanage.security.JWT;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequest {
    String username;
    String password;
}
