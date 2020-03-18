package me.akoala.scac.gateway.self.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (AccountRole)表实体类
 *
 * @author makejava
 * @since 2020-03-17 14:46:09
 */
@SuppressWarnings("serial")
public class AccountRole extends Model<AccountRole> {
    
    private String accountId;
    
    private Integer roleId;


    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.accountId;
    }
    }