package com.center.service.shiro.realm;

import com.center.api.dto.ResourcesDto;
import com.center.api.dto.RoleDto;
import com.center.api.dto.UserDto;
import com.center.common.enums.UserStatusEnum;
import com.center.common.enums.UserTypeEnum;
import com.center.service.biz.IResourcesService;
import com.center.service.biz.IRoleService;
import com.center.service.biz.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName ShiroRealm
 * @Description
 * @Author yutang
 * @Date 2020/6/26 15:49
 * @Version V1.0
 **/
public class ShiroRealm extends AuthorizingRealm {


    @Resource
    private IUserService userService;

    @Resource
    private IRoleService roleService;

    @Resource
    private IResourcesService resourcesService;

    /**
     * 权限认证，为当前登录的Subject授予角色和权限（角色的权限信息集合）
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 这个是下面的那个方法存入的第一个参数
        Long userId = (Long) SecurityUtils.getSubject().getPrincipal();

        // 赋予角色
        List<RoleDto> roleList = roleService.listRolesByUserId(userId);
        for (RoleDto role : roleList) {
            info.addRole(role.getName());
        }

        // 赋予权限
        List<ResourcesDto> resourcesList = null;
        // 根据用户id查询用户信息
        UserDto user = userService.selectByPrimaryKey(userId);
        if (null == user) {
            return info;
        }
        // ROOT用户默认拥有所有权限
        if (UserTypeEnum.ROOT.toString().equalsIgnoreCase(user.getUserType())) {
            resourcesList = resourcesService.findAllList();
        } else {
            resourcesList = resourcesService.listByUserId(userId);
        }

        if (!CollectionUtils.isEmpty(resourcesList)) {
            Set<String> permissionSet = new HashSet<>();
            for (ResourcesDto resources : resourcesList) {
                String permission = null;
                if (!StringUtils.isEmpty(permission = resources.getPermission())) {
                    permissionSet.addAll(Arrays.asList(permission.trim().split(",")));
                }
            }
            info.setStringPermissions(permissionSet);
        }
        return info;
    }

    /**
     * 提供账户信息返回认证信息（用户的角色信息集合）
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户的输入的账号. 这个在拦截器那里调用shiro.login的接口进来的参数信息
        String username = (String) authenticationToken.getPrincipal();
        UserDto user = userService.queryUserByUsername(username);
        if (user == null) {
            throw new UnknownAccountException("账号不存在！");
        }
        if (user.getStatus() != null && UserStatusEnum.DISENABLE.getValue().equals(user.getStatus())) {
            throw new LockedAccountException("帐号已被锁定，禁止登录！");
        }

        // principal参数使用用户Id，方便动态刷新用户权限
        return new SimpleAuthenticationInfo(
                user.getId(),
                user.getPassword(),
                ByteSource.Util.bytes(username),
                getName()
        );
    }
}
