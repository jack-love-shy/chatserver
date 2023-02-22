package com.zy.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.chat.entity.UserInfo;
import com.zy.chat.entity.UserLogin;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<UserInfo> {
    @Select(" SELECT friend_id AS user_id,username,avatar_url FROM netty_chatroom.tb_friend RIGHT JOIN netty_chatroom.tb_user\n" +
            " \t\tON tb_friend.friend_id = tb_user.user_id\n" +
            " \t\tWHERE  tb_friend.user_id = #{userId};")
    List<UserInfo> getFriendList(String userId);

}
