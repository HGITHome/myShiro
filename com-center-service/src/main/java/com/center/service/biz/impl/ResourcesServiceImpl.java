package com.center.service.biz.impl;


import com.center.api.dto.ResourcesDto;
import com.center.dao.entity.ResourcesEo;
import com.center.dao.entity.RoleEo;
import com.center.dao.entity.RoleResourcesRelationEo;
import com.center.dao.entity.UserRoleRelationEo;
import com.center.dao.mapper.IResourcesDao;
import com.center.dao.mapper.IRoleResourcesRelationDao;
import com.center.dao.mapper.IUserRoleRelationDao;
import com.center.service.biz.IResourcesService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName ResourcesServiceImpl
 * @Description
 * @Author yutang
 * @Date 2020/7/5 11:20
 * @Version V1.0
 **/

@Service
public class ResourcesServiceImpl implements IResourcesService {

    @Resource
    private IResourcesDao resourcesDao;

    @Resource
    private IUserRoleRelationDao userRoleRelationDao;

    @Resource
    private IRoleResourcesRelationDao roleResourcesRelationDao;

    @Override
    public List<ResourcesDto> findAllList() {
        List<ResourcesDto> resourcesDtos = new ArrayList<>();
        List<ResourcesEo> resourcesEos = resourcesDao.selectAll();
        if (CollectionUtils.isNotEmpty(resourcesEos)) {
            resourcesEos.forEach(eo -> {
                ResourcesDto resourcesDto = new ResourcesDto();
                BeanUtils.copyProperties(eo,resourcesDto);
                resourcesDtos.add(resourcesDto);
            });
        }
        return resourcesDtos;
    }

    @Override
    public List<ResourcesDto> listByUserId(Long userId) {
        List<ResourcesDto> resourcesDtos = new ArrayList<>();
        Example example = new Example(UserRoleRelationEo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("user_id",userId);
        // 查询用户的角色关系，获取角色id集合
        List<UserRoleRelationEo> userRoleRelationEos = userRoleRelationDao.selectByExample(example);
        if (CollectionUtils.isEmpty(userRoleRelationEos)) {
            return null;
        }
        List<Long> roleIds = userRoleRelationEos.stream().map(UserRoleRelationEo :: getRoleId).distinct().collect(Collectors.toList());
        Example example1 = new Example(RoleResourcesRelationEo.class);
        Example.Criteria criteria1 = example.createCriteria();
        criteria1.andIn("role_id",roleIds);
        // 查询角色跟资源的关系，获取资源主键id集合
        List<RoleResourcesRelationEo> roleResourcesRelationEos = roleResourcesRelationDao.selectByExample(example1);
        if (CollectionUtils.isEmpty(roleResourcesRelationEos)) {
            return null;
        }
        List<Long> resourcesIds = roleResourcesRelationEos.stream().map(RoleResourcesRelationEo :: getResourcesId).distinct().collect(Collectors.toList());
        Example example2 = new Example(ResourcesEo.class);
        Example.Criteria criteria2 = example.createCriteria();
        criteria2.andIn("id",resourcesIds);
        // 通过资源id集合查询资源信息
        List<ResourcesEo> resourcesEos = resourcesDao.selectByExample(example2);
        if (CollectionUtils.isNotEmpty(resourcesEos)) {
            resourcesEos.forEach(eo -> {
                ResourcesDto resourcesDto = new ResourcesDto();
                BeanUtils.copyProperties(eo,resourcesDto);
                resourcesDtos.add(resourcesDto);
            });
        }
        return resourcesDtos;
    }
}
