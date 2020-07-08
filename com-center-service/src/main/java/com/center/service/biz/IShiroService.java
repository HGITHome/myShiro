package com.center.service.biz;

import com.center.api.dto.UserDto;

import java.util.Map;

/**
 * @author heyutang
 * @Title:
 * @Package com.center.service.biz
 * @Description:
 * @Company
 * @date 2020/7/8 17:07
 */
public interface IShiroService {

    /**
     * 初始化权限
     */
    Map<String, String> loadFilterChainDefinitions();

    /**
     * 重新加载权限
     */
    void updatePermission();

    /**
     * 重新加载用户权限
     *
     * @param user
     */
    void reloadAuthorizingByUserId(UserDto user);

    /**
     * 重新加载所有拥有roleId角色的用户的权限
     *
     * @param roleId
     */
    void reloadAuthorizingByRoleId(Long roleId);
}
