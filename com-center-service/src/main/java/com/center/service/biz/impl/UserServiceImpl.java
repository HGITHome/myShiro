package com.center.service.biz.impl;

import com.center.api.dto.UserDto;
import com.center.dao.entity.UserEo;
import com.center.dao.mapper.IUserDao;
import com.center.service.biz.IUserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.center.common.utils.RelationMapperUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return  userDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserDto userDto) throws Exception {
        UserEo userEo = new UserEo();
        BeanUtils.copyProperties(userDto,userEo);
        return userDao.insert(userEo);
    }

    @Override
    public UserDto selectByPrimaryKey(Integer id) {
        UserEo userEo = userDao.selectByPrimaryKey(id);
        UserDto userDto = new UserDto();
        if (null != userEo){
            BeanUtils.copyProperties(userEo,userDto);
        }
        return userDto;
    }

    @Override
    public List<UserDto> selectAll() {
        List<UserDto> userDtos = new ArrayList<>();
        List<UserEo> userEos = userDao.selectAll();
        if (CollectionUtils.isNotEmpty(userEos)){
            userEos.forEach(eo -> {
                UserDto userDto = new UserDto();
                BeanUtils.copyProperties(eo,userDto);
                userDtos.add(userDto);
            });
        }
        return userDtos;
    }

    @Override
    public int updateByPrimaryKey(UserDto userDto) throws Exception {
        UserEo userEo = new UserEo();
        RelationMapperUtils.entryAndDtoMapper(userDto,userEo,false);
        return userDao.updateByPrimaryKey(userEo);
    }
}
