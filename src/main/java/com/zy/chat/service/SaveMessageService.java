package com.zy.chat.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.chat.entity.Message;



public interface SaveMessageService extends IService<Message> {

    public void save(String touser,String fromuser,String time,String content,String type);
}
