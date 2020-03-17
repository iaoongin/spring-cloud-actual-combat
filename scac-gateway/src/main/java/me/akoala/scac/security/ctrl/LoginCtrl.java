package me.akoala.scac.security.ctrl;

import me.akoala.scac.common.api.MessageCode;
import me.akoala.scac.security.rr.LoginRequest;
import me.akoala.scac.security.rr.LoginResponse;
import me.akoala.scac.security.security.TokenProvider;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 *
 **/
@Controller
public class LoginCtrl {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private ReactiveUserDetailsService userDetailsService;

    @Resource
    private TokenProvider tokenProvider;


    @GetMapping("/auth/login.html")
    public String loginPage() {
        return "login";
    }


    @PostMapping("/auth/login")
    @ResponseBody
    public Mono<ServerResponse> login(ServerRequest request) {
        Mono<LoginRequest> loginRequest = request.bodyToMono(LoginRequest.class);
//        loginRequest.flatMap(login-> login.getUsername());
        return loginRequest.flatMap(login -> userDetailsService.findByUsername(login.getUsername())
                .flatMap(user -> {
                    if (passwordEncoder.matches(login.getPassword(), user.getPassword())) {
                        return ServerResponse.ok().contentType(APPLICATION_JSON).body(BodyInserters.fromObject(new LoginResponse(tokenProvider.generateToken(user))));
                    } else {
                        return ServerResponse.badRequest().body(BodyInserters.fromObject(WsResponse.failure(MessageCode.AUTH_INVALID_CREDENTIALS)));
                    }
                })
                .switchIfEmpty(ServerResponse.badRequest().body(BodyInserters.fromObject(WsResponse.failure(MessageCode.AUTH_USER_DOES_NOT_EXIST)))));
    }

}
