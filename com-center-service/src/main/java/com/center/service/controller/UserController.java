package com.center.service.controller;

import com.center.api.dto.UserDto;
import com.center.service.biz.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping("/save")
    @ApiOperation(value = "添加用户接口")
    public void insert() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setNickname("测试");
        userService.insert(userDto);
    }

    @GetMapping("/detail/get")
    @ApiOperation(value = "获取用户详情接口")
    public UserDto detailGet(@RequestParam(name = "id")Long id){
        return userService.selectByPrimaryKey(id);
    }

    @GetMapping("/find/all")
    @ApiOperation(value = "查询所有用户信息")
    public List<UserDto> findAll(){
        return userService.selectAll();
    }
}
