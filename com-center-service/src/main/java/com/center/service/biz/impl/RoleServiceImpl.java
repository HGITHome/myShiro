package com.center.service.biz.impl;

import com.center.api.dto.RoleDto;
import com.center.dao.entity.RoleEo;
import com.center.dao.entity.UserRoleRelationEo;
import com.center.dao.mapper.IRoleDao;
import com.center.dao.mapper.IUserRoleRelationDao;
import com.center.service.biz.IRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName RoleServiceImpl
 * @Description 角色service实现类
 * @Author yutang
 * @Date 2020/6/26 16:50
 * @Version V1.0
 **/

@Service
public class RoleServiceImpl implements IRoleService {

    @Resource
    private IRoleDao roleDao;

    @Resource
    private IUserRoleRelationDao userRoleRelationDao;

    @Override
    public List<RoleDto> listRolesByUserId(Long userId) {
        if (null == userId){
            throw new RuntimeException("参数非法!");
        }
        UserRoleRelationEo userRoleRelationEo = new UserRoleRelationEo();
        userRoleRelationEo.setUserId(userId);
        List<UserRoleRelationEo> userRoleRelationEos = userRoleRelationDao.select(userRoleRelationEo);
        if (CollectionUtils.isEmpty(userRoleRelationEos)) {
            return null;
        }
        List<Long> ids = userRoleRelationEos.stream().map(UserRoleRelationEo :: getRoleId).distinct().collect(Collectors.toList());
        Example example = new Example(RoleEo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id",ids);
        example.and(criteria);
        List<RoleEo> roleEos = roleDao.selectByExample(example);
        if (CollectionUtils.isNotEmpty(roleEos)){
            List<RoleDto> roleDtos = new ArrayList<>();
            roleEos.forEach(eo -> {
                RoleDto roleDto = new RoleDto();
                BeanUtils.copyProperties(eo,roleDto);
                roleDtos.add(roleDto);
            });
            return roleDtos;
        }
        return null;
    }
}
