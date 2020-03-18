package me.akoala.scac.gateway.self.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.akoala.scac.gateway.self.dao.PermissionDao;
import me.akoala.scac.gateway.self.entity.Permission;
import me.akoala.scac.gateway.self.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * (Permission)表服务实现类
 *
 * @author makejava
 * @since 2020-03-17 14:46:09
 */
@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, Permission> implements PermissionService {

}