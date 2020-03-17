package me.akoala.scac.security.rr;

import lombok.Data;

/**
 *
 **/
@Data
public class LoginRequest {
    private String username;
    private String password;
}
