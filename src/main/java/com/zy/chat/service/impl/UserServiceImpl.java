package com.zy.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.chat.entity.UserInfo;
import com.zy.chat.entity.UserLogin;
import com.zy.chat.mapper.LoginMapper;
import com.zy.chat.mapper.UserMapper;
import com.zy.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserInfo> implements UserService {


    @Autowired
    private UserMapper userMapper;
    @Override
    public List<UserInfo> getFriendList(String userId) {
        return  userMapper.getFriendList(userId);
    }
}
