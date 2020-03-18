package me.akoala.scac.gateway.self.ctrl;

import me.akoala.scac.common.api.Assert;
import me.akoala.scac.common.api.MessageCode;
import me.akoala.scac.common.api.R;
import me.akoala.scac.gateway.self.config.AuthWebFilter;
import me.akoala.scac.gateway.self.entity.Account;
import me.akoala.scac.gateway.self.entity.TrustSite;
import me.akoala.scac.gateway.self.rr.LoginRequest;
import me.akoala.scac.gateway.self.rr.LoginResponse;
import me.akoala.scac.gateway.self.service.TokenProvider;
import me.akoala.scac.gateway.self.service.TrustSiteService;
import me.akoala.scac.gateway.self.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

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

    @Value("${deployUrl}")
    private String deployUrl;

    @Resource
    private TrustSiteService trustSiteService;

    /**
     * 登录页面
     *
     * @param model
     * @return
     */
    @GetMapping(AuthWebFilter.loginPage)
    public String loginPage(final Model model) {
        model.addAttribute("deployUrl", deployUrl);
        return "login";
    }

//    @GetMapping(AuthWebFilter.unAuthApi)
//    @ResponseBody
//    public R<UnAuthResponse> loginErrorApi() {
//        UnAuthResponse unAuthResponse = new UnAuthResponse(String.format("http://%s:%s/auth/login.html", hostName, port));
//        return R.just(MessageCode.AUTH_UNAUTHORIZED, unAuthResponse);
//    }

    /**
     * 登录接口
     *
     * @param lr
     * @return
     */
    @PostMapping(AuthWebFilter.loginApi)
    @ResponseBody
    public R<LoginResponse> login(LoginRequest lr) {

        // check site
        Assert.isTrue(checkSite(lr), MessageCode.AUTH_UN_SUPPORT_SITE);

        Account user = userDetailsService.findByUsername(lr.getUsername());
        Assert.notNull(user, MessageCode.AUTH_USER_DOES_NOT_EXIST);
        Assert.isTrue(passwordEncoder.matches(lr.getPassword(), user.getPassword()), MessageCode.AUTH_INVALID_CREDENTIALS);
        LoginResponse loginResponse = new LoginResponse(tokenProvider.generateToken(user));
        return R.success(loginResponse);

    }

    /**
     * 检查是否为授信地址
     *
     * @param lr
     * @return
     */
    private boolean checkSite(LoginRequest lr) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        List<TrustSite> list = trustSiteService.list();
        for (TrustSite trustSite : list) {
            if (antPathMatcher.match(trustSite.getUrl(), lr.getRedirectUrl())) {
                return true;
            }
        }
        return false;
    }
}


