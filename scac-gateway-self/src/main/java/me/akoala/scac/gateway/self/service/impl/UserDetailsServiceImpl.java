package me.akoala.scac.gateway.self.service.impl;

import me.akoala.scac.gateway.self.entity.Account;
import me.akoala.scac.gateway.self.service.AccountService;
import me.akoala.scac.gateway.self.service.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private AccountService accountService;

    @Override
    public Account findByUsername(String username) {
        return accountService.findByUsername(username);
    }

}
