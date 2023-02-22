package com.zy.chat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.zy.chat.mapper")
public class StartChat {


    public static void main(String[] args) {
        SpringApplication.run(StartChat.class,args);
    }

}
