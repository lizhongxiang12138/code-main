package com.lzx.code.codedemo.controller;

import com.lzx.code.codedemo.entity.User;
import com.lzx.code.codedemo.service.UserService;
import com.lzx.code.japcommon.base.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述: 用户的api
 *
 * @Auther: lzx
 * @Date: 2019/7/11 17:14
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User> {

    private UserService userService;

    public UserController(UserService userService) {
        /*
        !!!!!!!!!!!!!!!!!!!!重点：
        这个时必须的   ·······~~~~~~~~注意哦
        */
        this.baseService = userService;
        this.userService = userService;
    }
}
