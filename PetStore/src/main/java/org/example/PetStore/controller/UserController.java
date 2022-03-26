package org.example.PetStore.controller;

import cn.hutool.core.lang.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.PetStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "注册登录Controller", tags = {"注册登录Api"})

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    @ApiOperation(value = "注册账号", notes = "注册账号")
    @RequestMapping(value = "register", method = RequestMethod.GET) public Dict register(@RequestParam("address") String address) {
        return  userService.register(address);
    }
    @ApiOperation(value = "登录账号", notes = "登录账号")
    @RequestMapping(value = "login", method = RequestMethod.GET) public Dict login(@RequestParam("address") String address) {
        return  userService.login(address);
    }
}
