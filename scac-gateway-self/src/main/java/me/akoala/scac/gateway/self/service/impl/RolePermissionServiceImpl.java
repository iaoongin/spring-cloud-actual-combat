package me.akoala.scac.gateway.self.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.akoala.scac.gateway.self.dao.RolePermissionDao;
import me.akoala.scac.gateway.self.entity.RolePermission;
import me.akoala.scac.gateway.self.service.RolePermissionService;
import org.springframework.stereotype.Service;

/**
 * (RolePermission)表服务实现类
 *
 * @author makejava
 * @since 2020-03-17 14:46:09
 */
@Service("rolePermissionService")
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionDao, RolePermission> implements RolePermissionService {

}