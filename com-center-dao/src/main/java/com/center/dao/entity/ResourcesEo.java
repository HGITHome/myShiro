package com.center.dao.entity;

import com.center.dao.base.BaseEo;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "sys_resources")
public class ResourcesEo extends BaseEo {

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "url")
    private String url;

    @Column(name = "permission")
    private String permission;

    @Column(name = "parentId")
    private Long parentId;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "external")
    private Boolean external;

    @Column(name = "available")
    private Boolean available;

    @Column(name = "icon")
    private String icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getExternal() {
        return external;
    }

    public void setExternal(Boolean external) {
        this.external = external;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
