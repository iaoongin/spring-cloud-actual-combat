package me.akoala.scac.gateway.self.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import me.akoala.scac.gateway.self.entity.Account;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 **/
@Component
public class TokenProvider {

    Map<String, String> map = new ConcurrentHashMap();

    public String generateToken(Account account) {
        String username = account.getUsername();

        String token = JWT.create()
                .withAudience(username)
                .sign(Algorithm.HMAC256(account.getPassword()));
        map.put(username, token);
        map.put(token, username);
        return token;

    }

    public boolean isTokenNoExpired(String authToken) {
        return map.get(authToken) != null;
    }
}
