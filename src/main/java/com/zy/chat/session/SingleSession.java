package com.zy.chat.session;

import com.zy.chat.config.ChannelConfig;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.ConcurrentHashMap;



public class SingleSession extends ChannelConfig {
    //存放用户与对应通道
    private static ConcurrentHashMap<String, ChannelHandlerContext> userChannelMap=new ConcurrentHashMap<>();

    private SingleSession() {
    }

    public static ConcurrentHashMap<String, ChannelHandlerContext> getUserChannelMap() {

        return userChannelMap;
    }

    public static void setUserChannelMap(ConcurrentHashMap<String, ChannelHandlerContext> userChannelMap) {
        SingleSession.userChannelMap = userChannelMap;
    }

    public static void writeMap(){
        System.out.println(userChannelMap);
    }


}
