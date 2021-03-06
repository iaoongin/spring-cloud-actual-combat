package me.akoala.scac.security.handler;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.akoala.scac.common.api.MessageCode;
import me.akoala.scac.security.entity.AuthUserDetails;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.WebFilterChainServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Base64;

@Component
public class AuthenticationSuccessHandler extends WebFilterChainServerAuthenticationSuccessHandler {

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        ServerWebExchange exchange = webFilterExchange.getExchange();
        ServerHttpResponse response = exchange.getResponse();
        //设置headers
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");

        //设置body
        WsResponse wsResponse = WsResponse.success();
        byte[] dataBytes = {};
        ObjectMapper mapper = new ObjectMapper();
        try {
            UserDetails user = (UserDetails) authentication.getPrincipal();
            AuthUserDetails userDetails = buildUser(user);
            byte[] authorization = (userDetails.getUsername() + ":" + userDetails.getPassword()).getBytes();
            String token = Base64.getEncoder().encodeToString(authorization);
            httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
//            JSONObject jsonObject1 = new JSONObject();
//            jsonObject1.rea
            JSONObject jsonObject = JSONUtil.parseObj(userDetails);
//            jsonObject
            jsonObject.put("token", token);
            wsResponse.setResult(jsonObject);
            dataBytes = mapper.writeValueAsBytes(wsResponse);
        } catch (Exception ex) {
            ex.printStackTrace();
            JSONObject result = new JSONObject();
            result.put("status", MessageCode.COMMON_FAILURE.getCode());
            result.put("message", "授权异常");

            dataBytes = result.toString().getBytes();
        }
        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(dataBytes);
        return response.writeWith(Mono.just(bodyDataBuffer));
    }


    private AuthUserDetails buildUser(UserDetails user) {
        AuthUserDetails userDetails = new AuthUserDetails();
        userDetails.setUsername(user.getUsername());
        userDetails.setPassword(user.getPassword().substring(user.getPassword().lastIndexOf("}") + 1));
        return userDetails;
    }
}