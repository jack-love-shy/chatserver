package com.zy.chat.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.zy.chat.server.Handler.WebsocketHandler;
import com.zy.chat.service.ChatService;
import com.zy.chat.session.SingleSession;
import com.zy.chat.util.ChatRedisUtils;
import com.zy.chat.util.TimeUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class ChatServiceImpl implements ChatService {



    private static ChatRedisUtils chatRedisUtils;
    TimeUtils timeUtils = new TimeUtils();


    @Autowired
    public void init(ChatRedisUtils chatRedisUtils){ChatServiceImpl.chatRedisUtils=chatRedisUtils;}

    @Override
    public void SingleChat(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        JSONObject jsonObject  = JSONObject.parseObject(msg.text());
        String username=jsonObject.getString("username");
        String touser=jsonObject.getString("touser");
        String content=jsonObject.getString("realcontent");
        String type=jsonObject.getString("type");
        SingleSession.getUserChannelMap().put(username, ctx);
        ChannelHandlerContext toUserCtx= SingleSession.getUserChannelMap().get(touser);
        if(toUserCtx==null){
            String key=touser;
            Map item =chatRedisUtils.createChatNumber(content,type);
            chatRedisUtils.hsaveCacheChatMessage(key,username,item);
            sendMessage(ctx,"用户不在线");
        }else{
            String key=touser;
            Map item=chatRedisUtils.createChatNumber(content,type);
            chatRedisUtils.hsaveCacheChatMessage(key,username,item);
            JSONObject back = new JSONObject();
            back.put("fromuser", username);
            back.put("content", content);
            back.put("time",timeUtils.getCurrentTime());
            String jsonback=back.toJSONString();
            sendMessage(toUserCtx,jsonback);
        }
    }

    private void sendMessage(ChannelHandlerContext ctx, String responseJson) {
        ctx.channel().writeAndFlush(new TextWebSocketFrame(responseJson));
    }
}
