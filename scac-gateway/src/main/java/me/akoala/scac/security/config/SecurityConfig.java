package me.akoala.scac.security.config;

import me.akoala.scac.security.handler.AuthenticationFallHandler;
import me.akoala.scac.security.handler.AuthenticationSuccessHandler;
import me.akoala.scac.security.handler.CustomHttpBasicServerAuthenticationEntryPoint;
import me.akoala.scac.security.security.SecurityUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 *
 **/
@EnableWebFluxSecurity
public class SecurityConfig {

    private static String loginPage = "/auth/login";
    //security的鉴权排除的url列表
    private static final String[] excludedAuthPages = {
            loginPage,
            "/auth/login",
            "/auth/logout",
            "/health",
            "/api/socket/**"
    };

//    @Resource
//    AuthenticationSuccessHandler authenticationSuccessHandler;
//    @Resource
//    AuthenticationFallHandler authenticationFallHandler;
//    @Resource
//   ;
////    @Resource
////    private SecurityContextRepository securityContextRepository;



    @Bean
    public ReactiveAuthenticationManager authenticationManager(SecurityUserDetailsService userDetailsService) {
        return new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
    }

    @Bean
    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http,
                                                      ReactiveAuthenticationManager authenticationManager,
                                                      AuthenticationSuccessHandler authenticationSuccessHandler,
                                                      AuthenticationFallHandler authenticationFallHandler,
                                                      CustomHttpBasicServerAuthenticationEntryPoint customHttpBasicServerAuthenticationEntryPoint
    ) {

        http
                .authorizeExchange()
                .pathMatchers(excludedAuthPages).permitAll()  //无需进行权限过滤的请求路径
                .pathMatchers(HttpMethod.OPTIONS).permitAll() //option 请求默认放行
                .anyExchange().authenticated()
                .and()
                .authenticationManager(authenticationManager)
//                .addFilterAt(new ReactorContextWebFilter(securityContextRepository), SecurityWebFiltersOrder.REACTOR_CONTEXT)
                .formLogin().loginPage(loginPage)
                .authenticationSuccessHandler(authenticationSuccessHandler) //认证成功
                .authenticationFailureHandler(authenticationFallHandler) //登陆验证失败
                .and().exceptionHandling().authenticationEntryPoint(customHttpBasicServerAuthenticationEntryPoint)  //基于http的接口请求鉴权失败
                .and().csrf().disable()//必须支持跨域
                .logout().disable()
        ;

        return http.build();
    }


}
