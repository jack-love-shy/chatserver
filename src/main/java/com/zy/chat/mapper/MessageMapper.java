package com.zy.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.chat.entity.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageMapper extends BaseMapper<Message> {
}
