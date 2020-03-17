package me.akoala.scac.gateway.self.service;

import me.akoala.scac.gateway.self.entity.Account;
import reactor.core.publisher.Mono;

public interface UserDetailsService {

    Account findByUsername(String username);

}
