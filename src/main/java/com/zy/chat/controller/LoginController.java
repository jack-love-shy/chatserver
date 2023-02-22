package com.zy.chat.controller;


import com.zy.chat.entity.UserLogin;
import com.zy.chat.service.impl.UserLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/logincontroller"})
public class LoginController {
    @Autowired
    private UserLoginServiceImpl userLoginService;

    @RequestMapping(value = "/login/{username}/{password}")
        public ResponseEntity<UserLogin> login(@PathVariable(value = "username")String username, @PathVariable(value = "password")String password) throws Exception{

            UserLogin userLogin=this.userLoginService.login(username,password);
        System.out.println(userLogin);
            return new ResponseEntity<UserLogin>(userLogin, HttpStatus.OK);
        }
    }

