package com.zy.chat.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class ChatRedisUtils {

    @Autowired
    private RedisUtils redisUtils;
    /**
     * 功能描述： 创建已发送消息房间号
     * 根据ChatVO中的fromUserId和toUserId生成聊天房间号：问题id-小号用户id-大号用户id
     * 例如“1-2”： 小号在前，大号在后；保证房间号唯一
     *
     * @param content
     */
    public  Map<String,String> createChatNumber(String content,String type) {
        Map<String,String> maps=new HashMap<>();
        TimeUtils timeUtils = new TimeUtils();
        maps.put("content",content);
        maps.put("time",timeUtils.getCurrentTime());
        maps.put("type",type);
        return maps;
    }

    /**
     * 功能描述：将JavaBean对象的信息缓存进Redis
     *
     * @param chatVO 聊天信息JavaBean
     * @return 是否保存成功
     */
    public boolean hsaveCacheChatMessage(String key, String item, Map chatVO) {
        //判断key是否存在
        if (redisUtils.hasKey(key)) {
            boolean res = redisUtils.hset(key, item, chatVO);
            return res;
        } else {
            //不存在key时，将chatVO存进缓存，并设置过期时间
            boolean isCache = redisUtils.hset(key, item, chatVO);
            return isCache;
        }
    }

    public void Keys(String key) {
        redisUtils.Keys(key);
    }


}
