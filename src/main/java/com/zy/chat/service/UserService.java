package com.zy.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.chat.entity.UserInfo;


import java.util.List;

public interface UserService extends IService<UserInfo> {
        List<UserInfo> getFriendList(String userId);

 }
