package me.akoala.scac.gateway.self.entity;

import lombok.*;

import java.util.Collection;

/**
 *
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    private String username;
    private String password;
    private Collection<String> grantedAuthorities;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
}
