package com.zy.chat.controller;


import com.zy.chat.entity.UserInfo;
import com.zy.chat.entity.UserLogin;
import com.zy.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = {"/usercontroller"})
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/{userId}")
    public ResponseEntity<List<UserInfo>> getFriendList(@PathVariable(value = "userId")String userId) throws Exception{

        List<UserInfo> friendlist=this.userService.getFriendList(userId);

        return new ResponseEntity<List<UserInfo>>(friendlist, HttpStatus.OK);
    }
}
