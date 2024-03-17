package org.xyc.domain.user.cache;

import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.xyc.domain.base.cache.BaseCache;
import org.xyc.domain.user.common.RedisKey;
import org.xyc.domain.user.mapper.UserPOMapper;
import org.xyc.domain.user.model.po.UserPO;

import java.util.Objects;

/**
 * @author xuyachang
 * @date 2024/3/17
 */
@Component
@RequiredArgsConstructor
public class UserCache implements BaseCache<UserPO> {

    private final RedisTemplate<String,String> redisTemplate;

    private final UserPOMapper userPOMapper;

    @Override
    public UserPO findById(Long id) {
        String key = String.format(RedisKey.User, id);
        String redisValue = redisTemplate.opsForValue().get(key);
        if(Objects.isNull(redisValue)){
            UserPO userPO = userPOMapper.selectById(id);
            if(Objects.nonNull(userPO)){
                redisTemplate.opsForValue().set(key,JSON.toJSONString(userPO));
            }
            return userPO;
        }
        return JSON.parseObject(redisValue,UserPO.class);
    }

    @Override
    public void invalidate(Long id) {
        String key = String.format(RedisKey.User, id);
        redisTemplate.delete(key);
    }
}
