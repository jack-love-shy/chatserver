package com.zy.chat.server;

import com.zy.chat.server.Handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


@Getter
@Setter

public class WebSocketServer implements Runnable{


    private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    private  int  port;

    private ChannelFuture channelFuture;


    @Autowired
    private ServerBootstrap serverBootstrap;

    @Autowired
    private EventLoopGroup bossGroup;

    @Autowired
    private  EventLoopGroup workerGroup;



    private ServerHandler serverHandler;


   
    public void run() {

        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.group(bossGroup,workerGroup);
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(serverHandler);
            }
        });
        try {
            channelFuture= serverBootstrap.bind(port).sync();

            // 对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.info(e.getMessage());
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public void close() {
        channelFuture.channel().close();
        Future<?> bossGroupFuture = bossGroup.shutdownGracefully();
        Future<?> workerGroupFuture = workerGroup.shutdownGracefully();
        try {
            bossGroupFuture.await();//？
            workerGroupFuture.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
