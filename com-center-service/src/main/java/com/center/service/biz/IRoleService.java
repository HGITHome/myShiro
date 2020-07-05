package com.center.service.biz;

import com.center.api.dto.RoleDto;

import java.util.List;

/**
 * @ClassName IRoleService
 * @Description
 * @Author yutang
 * @Date 2020/6/26 16:24
 * @Version V1.0
 **/
public interface IRoleService {

   /**
    * 根据用户id获取用户的角色信息
    * @param userId
    * @return
    */
   List<RoleDto> listRolesByUserId(Long userId);

}
