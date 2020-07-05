package com.center.service.biz;

import com.center.api.dto.UserDto;
import com.center.dao.entity.UserEo;

import java.util.List;

public interface IUserService {

    /**
     * 通过主键删除用户信息
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存用户信息
     * @param userDto
     * @return
     * @throws Exception
     */
    int insert(UserDto userDto) throws Exception;

    /**
     * 通过主键获取用户信息
     * @param id
     * @return
     */
    UserDto selectByPrimaryKey(Long id);

    /**
     * 查询所有用户信息
     * @return
     */
    List<UserDto> selectAll();

    /**
     * 通过主键id更新用户信息
     * @param userDto
     * @return
     * @throws Exception
     */
    int updateByPrimaryKey(UserDto userDto) throws Exception;

    /**
     * 通过用户名查询用户信息
     * @param username
     * @return
     */
    UserDto queryUserByUsername(String username);
}
