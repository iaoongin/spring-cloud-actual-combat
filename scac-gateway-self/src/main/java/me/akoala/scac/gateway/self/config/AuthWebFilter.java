package me.akoala.scac.gateway.self.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import me.akoala.scac.common.api.MessageCode;
import me.akoala.scac.common.api.R;
import me.akoala.scac.gateway.self.rr.UnAuthResponse;
import me.akoala.scac.gateway.self.service.TokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Component
public class AuthWebFilter implements WebFilter {

    private static final String TOKEN_PREFIX = "Bearer ";

    public static final String loginPage = "/auth/login.html";
    public static final String unAuthApi = "/unAuth";
    public static final String loginApi = "/auth/login";
    //security的鉴权排除的url列表
    private static final String[] excludedAuthPages = {
            loginPage,
            loginApi,
            unAuthApi,
            "/auth/logout",
            "/health",
            "/api/socket/**"
    };

    @Resource
    private TokenProvider tokenProvider;

    @Value("${deployUrl}")
    private String deployUrl;

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

        ServerHttpResponse response = exchange.getResponse();
        /* // 重定向到登录API
//        response.setStatusCode()
        response.setStatusCode(HttpStatus.TEMPORARY_REDIRECT);
        response.getHeaders().setLocation(URI.create(unAuthApiApi));

        */

        // 直接输出内容
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);

        UnAuthResponse unAuthResponse = new UnAuthResponse(deployUrl + loginPage);
        R just = R.just(MessageCode.AUTH_UNAUTHORIZED, unAuthResponse);
        byte[] bytes = JSONUtil.toJsonStr(just).getBytes();
        response.getHeaders().setContentLength(bytes.length);
        DataBufferFactory dataBufferFactory = response.bufferFactory();
        Flux<DataBuffer> dataBufferFlux = Flux.just(dataBufferFactory.wrap(bytes));
        return response.writeWith(dataBufferFlux);
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
