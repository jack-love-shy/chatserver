package com.zy.chat.service;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public interface ChatService {

    public void SingleChat(ChannelHandlerContext ctx, TextWebSocketFrame msg);


}
