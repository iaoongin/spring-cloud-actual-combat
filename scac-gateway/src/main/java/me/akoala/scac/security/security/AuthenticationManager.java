//package me.akoala.scac.security.security;
//
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.ReactiveAuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class AuthenticationManager implements ReactiveAuthenticationManager {
//
//    @Resource
//    private PasswordEncoder passwordEncoder;
//
//    @Resource
//    private SecurityUserDetailsService securityUserDetailsService;
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public Mono authenticate(Authentication authentication) {
//
//        String username = authentication.getPrincipal().toString();
//        String password = passwordEncoder.encode(authentication.getCredentials().toString());
//        List<String> roles = new ArrayList();
//        List authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
//
//        Mono<UserDetails> byUsername = securityUserDetailsService.findByUsername(username);
//
////        .switchIfEmpty(Mono.error(new BadCredentialsException("用户名不存在")))
//        return byUsername
//                .flatMap(userDetails -> {
//                    if (password.equals(userDetails.getPassword())) {
//                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
//                        return Mono.just(authenticationToken);
//                    } else {
//                        return Mono.error(new BadCredentialsException("密码错误"));
//                    }
//                });
//    }
//
////    @Override
////    @SuppressWarnings("unchecked")
////    public Mono authenticate(Authentication authentication) {
////
////        String authToken = authentication.getCredentials().toString();
////        String username;
////        try {
////            username = tokenProvider.getUsernameFromToken(authToken);
////        } catch (Exception e) {
////            username = null;
////        }
////        if (username != null && !tokenProvider.isTokenExpired(authToken)) {
//////			Claims claims = tokenProvider.getAllClaimsFromToken(authToken);
//////			List roles = claims.get(AUTHORITIES_KEY, List.class);
////            List<String> roles = new ArrayList();
////            roles.add("ROLE_ADMIN");
////            List authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
////            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, username, authorities);
////            SecurityContextHolder.getContext().setAuthentication(auth);
////            return Mono.just(auth);
////        } else {
////            return Mono.empty();
////        }
////    }
//}