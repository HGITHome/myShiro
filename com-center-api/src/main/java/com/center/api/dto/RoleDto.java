package com.center.api.dto;

import com.center.api.base.BaseDto;

/**
 * @ClassName RoleDto
 * @Description 角色信息dto
 * @Author yutang
 * @Date 2020/6/26 16:43
 * @Version V1.0
 **/
public class RoleDto extends BaseDto {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否可用，0可用，1不可用
     */
    private Integer available;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}
