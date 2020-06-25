package com.center.dao.entity;

import com.center.dao.base.BaseEo;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "sys_user_role")
public class UserRoleRelationEo extends BaseEo {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
