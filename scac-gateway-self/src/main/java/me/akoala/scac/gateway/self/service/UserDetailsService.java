package me.akoala.scac.gateway.self.service;

import me.akoala.scac.gateway.self.entity.Account;

public interface UserDetailsService {

    Account findByUsername(String username);

}
