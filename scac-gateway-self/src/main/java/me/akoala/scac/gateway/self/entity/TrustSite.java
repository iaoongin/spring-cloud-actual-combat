package me.akoala.scac.gateway.self.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (TrustSite)表实体类
 *
 * @author makejava
 * @since 2020-03-17 15:39:23
 */
@SuppressWarnings("serial")
public class TrustSite extends Model<TrustSite> {
    
    private Integer id;
    //站点名称
    private String name;
    //站点路径
    private String url;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    }