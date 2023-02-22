package com.zy.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.chat.entity.UserInfo;
import com.zy.chat.entity.UserLogin;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LoginMapper  extends BaseMapper<UserLogin> {



}
