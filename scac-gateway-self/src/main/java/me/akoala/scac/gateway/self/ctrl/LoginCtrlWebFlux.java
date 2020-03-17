//package me.akoala.scac.gateway.self.ctrl;
//
//import me.akoala.scac.common.api.MessageCode;
//import me.akoala.scac.common.api.WsResponse;
//import me.akoala.scac.gateway.self.rr.LoginRequest;
//import me.akoala.scac.gateway.self.rr.LoginResponse;
//import me.akoala.scac.gateway.self.service.TokenProvider;
//import me.akoala.scac.gateway.self.service.UserDetailsService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.ServerRequest;
//import org.springframework.web.reactive.function.server.ServerResponse;
//import reactor.core.publisher.Mono;
//
//import javax.annotation.Resource;
//import java.net.URI;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
//import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
//import static org.springframework.web.reactive.function.server.RouterFunctions.route;
//
//@Configuration
//public class LoginCtrlWebFlux {
//
//    @Bean
//    RouterFunction<ServerResponse> responseRouterFunction() {
//        LoginHandler handler = new LoginHandler();
//        return route()
//                .GET("/auth/login.html", handler::loginPage)
//                .POST("/auth/login", handler::login)
//                .build();
//    }
//
//    @Resource
//    private PasswordEncoder passwordEncoder;
//
//    @Resource
//    private TokenProvider tokenProvider;
//
//    @Resource
//    private UserDetailsService userDetailsService;
//
//    class LoginHandler {
//
//        public Mono<ServerResponse> loginPage(ServerRequest request) {
//            return ServerResponse.temporaryRedirect(URI.create("login"))
//                    .build();
//        }
//
//        public Mono<ServerResponse> login(ServerRequest request) {
//            Mono<LoginRequest> loginRequest = request.bodyToMono(LoginRequest.class);
//            return loginRequest.flatMap(login -> userDetailsService.findByUsername(login.getUsername())
//                    .flatMap(user -> {
//                        if (passwordEncoder.matches(login.getPassword(), user.getPassword())) {
//                            return ServerResponse.ok().contentType(APPLICATION_JSON).body(BodyInserters.fromObject(new LoginResponse(tokenProvider.generateToken(user))));
//                        } else {
//                            return ServerResponse.badRequest().body(BodyInserters.fromObject(WsResponse.failure(MessageCode.INVALID_CREDENTIALS)));
//                        }
//                    })
//                    .switchIfEmpty(ServerResponse.badRequest().body(BodyInserters.fromObject(WsResponse.failure(MessageCode.USER_DOES_NOT_EXIST)))));
//        }
//    }
//
//
//}
