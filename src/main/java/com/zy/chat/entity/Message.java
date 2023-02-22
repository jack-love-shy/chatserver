package com.zy.chat.entity;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private Integer id;

    private  String fromuser;

    private  String touser;

    private String content;

    private String sendtime;

    private String type;
}
