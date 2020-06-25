package com.center.dao.entity;

import com.center.dao.base.BaseEo;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "sys_role")
public class RoleEo extends BaseEo {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "available")
    private Boolean available;

    @Transient
    private Integer selected;
}
