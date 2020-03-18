package me.akoala.scac.gateway.self.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.akoala.scac.gateway.self.dao.RoleDao;
import me.akoala.scac.gateway.self.entity.Role;
import me.akoala.scac.gateway.self.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * (Role)表服务实现类
 *
 * @author makejava
 * @since 2020-03-17 14:46:09
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

}