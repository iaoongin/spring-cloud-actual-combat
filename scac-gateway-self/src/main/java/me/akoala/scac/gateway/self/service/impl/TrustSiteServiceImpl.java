package me.akoala.scac.gateway.self.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.akoala.scac.gateway.self.dao.TrustSiteDao;
import me.akoala.scac.gateway.self.entity.TrustSite;
import me.akoala.scac.gateway.self.service.TrustSiteService;
import org.springframework.stereotype.Service;

/**
 * (TrustSite)表服务实现类
 *
 * @author makejava
 * @since 2020-03-17 15:39:23
 */
@Service("trustSiteService")
public class TrustSiteServiceImpl extends ServiceImpl<TrustSiteDao, TrustSite> implements TrustSiteService {

}