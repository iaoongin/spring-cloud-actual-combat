package me.akoala.scac.gateway.self.config;

import cn.hutool.core.util.StrUtil;
import me.akoala.scac.gateway.self.service.TokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;

@Component
public class AuthWebFilter implements WebFilter {

    private static final String TOKEN_PREFIX = "Bearer ";

    public static final String loginPage = "/auth/login.html";
    public static final String unAuthApiApi = "/unAuth";
    public static final String loginApi = "/auth/login";
    //security的鉴权排除的url列表
    private static final String[] excludedAuthPages = {
            loginPage,
            loginApi,
            unAuthApiApi,
            "/auth/logout",
            "/health",
            "/api/socket/**"
    };

    @Resource
    private TokenProvider tokenProvider;

    @Value("${eureka.instance.hostname}")
    private String hostName;
    @Value("${server.port}")
    private String port;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // 是否过滤
        if (checkIgnoreUrl(request)) {
            return chain.filter(exchange);
        }

        // 获取token
        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StrUtil.isNotEmpty(authorization) && authorization.startsWith(TOKEN_PREFIX)) {
            authorization = authorization.replaceAll(TOKEN_PREFIX, "");
            // 去校验
            if (checkToken(authorization)) {
                return chain.filter(exchange);
            }
        }
        // 重定向到登录API
        ServerHttpResponse response = exchange.getResponse();
//        response.setStatusCode()
        response.setStatusCode(HttpStatus.TEMPORARY_REDIRECT);
        response.getHeaders().setLocation(URI.create(unAuthApiApi));

//        request.
//        response.setStatusCode(HttpStatus.UNAUTHORIZED);
//        WsResponse<LoginErrorResponse> wsResponse = WsResponse.success(new LoginErrorResponse(String.format("http://%s:%s/auth/login.html", hostName, port)));
//        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONUtil.toJsonStr(wsResponse).getBytes());
//        response.writeWith(Flux.just(dataBuffer));
        return response.setComplete();
    }

    private boolean checkToken(String authorization) {
        return tokenProvider.isTokenNoExpired(authorization);
    }

    private boolean checkIgnoreUrl(ServerHttpRequest request) {
        String url = getUrl(request);
        for (String excludedAuthPage : excludedAuthPages) {
            if (excludedAuthPage.equals(url)) {
                return true;
            }
        }
        return false;
    }

    public static String getUrl(ServerHttpRequest request) {
        return request.getURI().getPath();
    }

}
