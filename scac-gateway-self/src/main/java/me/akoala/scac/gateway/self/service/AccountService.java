package me.akoala.scac.gateway.self.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.akoala.scac.gateway.self.entity.Account;

/**
 * (Account)表服务接口
 *
 * @author makejava
 * @since 2020-03-17 14:46:08
 */
public interface AccountService extends IService<Account> {

    Account findByUsername(String username);
}