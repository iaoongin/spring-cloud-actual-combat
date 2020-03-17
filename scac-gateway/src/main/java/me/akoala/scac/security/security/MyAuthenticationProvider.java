//package me.akoala.scac.security;
//
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//
//import java.io.Serializable;
//
//@Component
//public class MyAuthenticationProvider implements AuthenticationProvider, Serializable {
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        return authentication;
//    }
//
//    @Override
//    public boolean supports(Class<? extends Object> authentication) {
//        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
//    }
//
//}