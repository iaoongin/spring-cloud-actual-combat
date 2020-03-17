package me.akoala.scac.gateway.self.ctrl;

import me.akoala.scac.common.api.Assert;
import me.akoala.scac.common.api.MessageCode;
import me.akoala.scac.common.api.R;
import me.akoala.scac.gateway.self.config.AuthWebFilter;
import me.akoala.scac.gateway.self.entity.Account;
import me.akoala.scac.gateway.self.rr.LoginErrorResponse;
import me.akoala.scac.gateway.self.rr.LoginRequest;
import me.akoala.scac.gateway.self.rr.LoginResponse;
import me.akoala.scac.gateway.self.service.TokenProvider;
import me.akoala.scac.gateway.self.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class LoginCtrl {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private TokenProvider tokenProvider;

    @Resource
    private UserDetailsService userDetailsService;

    @Value("${eureka.instance.hostname}")
    private String hostName;
    @Value("${server.port}")
    private String port;

    @GetMapping(AuthWebFilter.loginPage)
    public String loginPage() {
        return "login";
    }

    @GetMapping(AuthWebFilter.unAuthApiApi)
    @ResponseBody
    public R<LoginErrorResponse> loginErrorApi() {
        LoginErrorResponse loginErrorResponse = new LoginErrorResponse(String.format("http://%s:%s/auth/login.html", hostName, port));
        return R.just(MessageCode.AUTH_UNAUTHORIZED, loginErrorResponse);
    }

    @PostMapping(AuthWebFilter.loginApi)
    @ResponseBody
    public R<LoginResponse> login(LoginRequest lr) {
        Account user = userDetailsService.findByUsername(lr.getUsername());

        Assert.notNull(user, MessageCode.AUTH_USER_DOES_NOT_EXIST);
        Assert.isTrue(passwordEncoder.matches(lr.getPassword(), user.getPassword()), MessageCode.AUTH_INVALID_CREDENTIALS);
        LoginResponse loginResponse = new LoginResponse(tokenProvider.generateToken(user));
        return R.success(loginResponse);

    }
}


