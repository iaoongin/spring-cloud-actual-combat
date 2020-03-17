package me.akoala.scac.gateway.self.service.impl;

import cn.hutool.core.util.StrUtil;
import me.akoala.scac.gateway.self.entity.Account;
import me.akoala.scac.gateway.self.service.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final String USERNAME = "123";
    public static final String PASSWORD = "123";

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Account findByUsername(String username) {

        if (StrUtil.equals(USERNAME, username)) {
            String pwd = passwordEncoder.encode(PASSWORD);
            Account account = Account.builder().username(username).password(pwd).build();
            return account;
        }
        return null;
    }

}
