package com.zy.chat.entity;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private Integer id;
    private String userId;
    private String username;
    private String password;
    private String avatarUrl;
}
