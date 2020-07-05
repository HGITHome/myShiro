package com.center.service.biz;


import com.center.api.dto.ResourcesDto;

import java.util.List;

/**
 * @ClassName IResourcesService
 * @Description 资源服务类
 * @Author yutang
 * @Date 2020/7/5 11:19
 * @Version V1.0
 **/
public interface IResourcesService {

    /**
     * 查询所有资源列表
     * @return
     */
    List<ResourcesDto> findAllList();

    /**
     * 查询用户权限
     * @param userId 用户主键id
     * @return
     */
    List<ResourcesDto> listByUserId(Long userId);
}
