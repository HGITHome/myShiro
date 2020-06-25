package com.center.service.biz;

import com.center.api.dto.UserDto;
import com.center.dao.entity.UserEo;

import java.util.List;

public interface IUserService {

    int deleteByPrimaryKey(Integer id);

    int insert(UserDto userDto) throws Exception;

    UserDto selectByPrimaryKey(Integer id);

    List<UserDto> selectAll();

    int updateByPrimaryKey(UserDto userDto) throws Exception;
}
