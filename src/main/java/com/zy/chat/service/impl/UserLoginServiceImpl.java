package com.zy.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.chat.entity.UserLogin;
import com.zy.chat.mapper.LoginMapper;
import com.zy.chat.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserLoginServiceImpl extends ServiceImpl<LoginMapper, UserLogin> implements LoginService {

    @Autowired
    private LoginMapper loginMapper;
    @Override
    public UserLogin login(String username, String password) {

        QueryWrapper<UserLogin> userLoginQueryWrapper = new QueryWrapper<>();
        userLoginQueryWrapper.eq("username",username).eq("password",password);
        return  this.loginMapper.selectOne(userLoginQueryWrapper);
    }
}
