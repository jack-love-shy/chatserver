package com.zy.chat.util;


import com.alibaba.fastjson.JSON;
import com.zy.chat.service.SaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Redis通用工具类
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SaveMessageService saveMessageService;

    // =============================common============================
    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */


    public boolean hset(String key, String item, Map value) {
        try {
            redisTemplate.opsForHash().put(key, item, JSON.toJSONString(value));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void Keys(String key){
        Map hashValue = redisTemplate.opsForHash().entries(key);
        System.out.println("hashValue = " + hashValue);
    }

    @Scheduled(fixedRate = 5000)
    public void savemessages(){
        System.out.println("每五秒存储一次");
        Set<String> keys=redisTemplate.keys("GEELY:HC1Y:station:*");
        for(String touser:keys){
            Set<String> fromusers=redisTemplate.opsForHash().keys(touser);
            for(String fromuser:fromusers){
              Map map=(Map<String,String>)redisTemplate.opsForHash().get(touser,fromuser);
                String time=map.get("time").toString();
                String content=map.get("content").toString();
                String type=map.get("type").toString();
                saveMessageService.save(touser,fromuser,time,content,type);
            }
        }

    }

}
