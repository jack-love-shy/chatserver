package com.zy.chat.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@TableName(value = "tb_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLogin {
    private Integer id;
    private String userId;
    private String username;
    private String password;
    private String avatarUrl;
}
