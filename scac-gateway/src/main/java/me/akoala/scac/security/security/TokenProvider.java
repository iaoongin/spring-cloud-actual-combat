package me.akoala.scac.security.security;

import cn.hutool.crypto.SecureUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 **/
@Component
public class TokenProvider {

    Map<String, String> map = new ConcurrentHashMap();

    public String generateToken(UserDetails user) {
        String username = user.getUsername();
       return JWT.create()
                .withAudience(username)
               .sign(Algorithm.HMAC256(user.getPassword()));

//        String token = SecureUtil.md5(username + user.getPassword().getBytes());
//        map.put(username, token);
//        map.put(token, username);
//        return token;
    }

    public String getUsernameFromToken(String token) {
        DecodedJWT decode = JWT.decode(token);
//        decode.
        return map.get(token);
    }

    public boolean isTokenExpired(String authToken) {
        return map.get(authToken) != null;
    }
}
