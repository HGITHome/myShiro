package com.center.service.controller;

import com.center.api.dto.UserDto;
import com.center.service.biz.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping("/save")
    public void insert() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setNickname("测试");
        userService.insert(userDto);
    }

    @GetMapping("/detail/get")
    public UserDto detailGet(@RequestParam(name = "id")Integer id){
        return userService.selectByPrimaryKey(id);
    }

    @GetMapping("/find/all")
    public List<UserDto> findAll(){
        return userService.selectAll();
    }
}
