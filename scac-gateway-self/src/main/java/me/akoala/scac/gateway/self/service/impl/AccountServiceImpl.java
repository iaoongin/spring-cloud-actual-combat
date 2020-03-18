package me.akoala.scac.gateway.self.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.akoala.scac.gateway.self.dao.AccountDao;
import me.akoala.scac.gateway.self.entity.Account;
import me.akoala.scac.gateway.self.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * (Account)表服务实现类
 *
 * @author makejava
 * @since 2020-03-17 14:46:08
 */
@Service("accountService")
public class AccountServiceImpl extends ServiceImpl<AccountDao, Account> implements AccountService {

    @Override
    public Account findByUsername(String username) {
        QueryWrapper<Account> qw = new QueryWrapper<>();
        qw.lambda().eq(Account::getUsername, username);
        return getOne(qw);
    }
}