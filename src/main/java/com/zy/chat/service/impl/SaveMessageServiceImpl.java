package com.zy.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.chat.entity.Message;
import com.zy.chat.entity.UserLogin;
import com.zy.chat.mapper.LoginMapper;
import com.zy.chat.mapper.MessageMapper;
import com.zy.chat.service.LoginService;
import com.zy.chat.service.SaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveMessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements SaveMessageService {

    @Autowired
    private MessageMapper messageMapper;
    @Override
    public void save(String touser, String fromuser, String time, String content, String type) {
        Message message = new Message();
        message.setFromuser(fromuser);
        message.setType(type);
        message.setContent(content);
        message.setTouser(touser);
        message.setSendtime(time);
        messageMapper.insert(message);
    }
}
