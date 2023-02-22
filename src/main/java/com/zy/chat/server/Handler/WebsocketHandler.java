package com.zy.chat.server.Handler;


import com.alibaba.fastjson.JSONObject;
import com.zy.chat.config.ChannelConfig;
import com.zy.chat.service.ChatService;
import com.zy.chat.session.SingleSession;
import com.zy.chat.util.ChatRedisUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import io.netty.util.AttributeKey;
import org.apache.ibatis.javassist.tools.web.Webserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;



@Component
@Qualifier("WebsocketHandler")
@ChannelHandler.Sharable
public class WebsocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static final Logger log= LoggerFactory.getLogger(Webserver.class);
    private  static ChatService chatService;
    @Autowired
    public void initChat(ChatService chatService){WebsocketHandler.chatService=chatService;}


    //连接建立后
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        log.info("服务器收到消息:",msg.text());
        System.out.println(msg.text());
        JSONObject jsonObject  = JSONObject.parseObject(msg.text());
        String type=jsonObject.getString("type");
        switch (type){
            case "text":
                chatService.SingleChat( ctx, msg);
                break;
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
     ChannelConfig.getChannelGroup().add(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("异常：{}",cause.getMessage());
        // 删除通道
        ChannelConfig.getChannelGroup().remove(ctx.channel());
        removeUserName(ctx);
        ctx.close();

    }
    /**
     * 删除用户与channel的对应关系
     * @param ctx
     */
    private void removeUserName(ChannelHandlerContext ctx){
        AttributeKey<String> key = AttributeKey.valueOf("username");
        String username = ctx.channel().attr(key).get();
        SingleSession.getUserChannelMap().remove(username);
    }



}
