package com.center.dao.entity;

import com.center.dao.base.BaseEo;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "sys_role_resources")
public class RoleResourcesRelationEo extends BaseEo {

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "resources_id")
    private Long resourcesId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(Long resourcesId) {
        this.resourcesId = resourcesId;
    }
}
