package com.zy.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.chat.entity.UserLogin;

public interface LoginService extends IService<UserLogin> {
        public UserLogin login(String username,String userpassword);
}
