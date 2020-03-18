package me.akoala.scac.gateway.self.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (Permission)表实体类
 *
 * @author makejava
 * @since 2020-03-17 14:46:09
 */
@SuppressWarnings("serial")
public class Permission extends Model<Permission> {
    
    private Integer id;
    //资源url
    private String resource;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
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