package me.akoala.scac.gateway.self.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.akoala.scac.gateway.self.dao.AccountRoleDao;
import me.akoala.scac.gateway.self.entity.AccountRole;
import me.akoala.scac.gateway.self.service.AccountRoleService;
import org.springframework.stereotype.Service;

/**
 * (AccountRole)表服务实现类
 *
 * @author makejava
 * @since 2020-03-17 14:46:09
 */
@Service("accountRoleService")
public class AccountRoleServiceImpl extends ServiceImpl<AccountRoleDao, AccountRole> implements AccountRoleService {

}