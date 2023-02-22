package com.zy.chat.config;


import com.zy.chat.server.Handler.ServerHandler;
import com.zy.chat.server.WebSocketServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean(name = "bossGroup")
    public NioEventLoopGroup getBossGroup() {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        return boosGroup;
    }

    @Bean(name = "workerGroup")
    public NioEventLoopGroup getWorkerGroup() {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        return workerGroup;
    }

    @Bean(name = "serverBootstrap")
    public ServerBootstrap getServerBootstrap() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        return serverBootstrap;
    }

    @Bean(name = "webSocketServer")
    public WebSocketServer getWebSocketServer() {
        WebSocketServer webSocketServer = new WebSocketServer();
        webSocketServer.setPort(8081);
        ServerHandler serverHandler = new ServerHandler();
        webSocketServer.setServerHandler(serverHandler);
        return webSocketServer;
    }
}
