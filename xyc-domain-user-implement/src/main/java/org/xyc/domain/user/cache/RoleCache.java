package org.xyc.domain.user.cache;

import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.xyc.domain.base.cache.BaseCache;
import org.xyc.domain.user.common.RedisKey;
import org.xyc.domain.user.mapper.RolePOMapper;
import org.xyc.domain.user.model.po.RolePO;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author xuyachang
 * @date 2024/8/31
 */
@Component
@RequiredArgsConstructor
public class RoleCache implements BaseCache<RolePO> {

    private final RedisTemplate<String,String> redisTemplate;

    private final RolePOMapper rolePOMapper;
    @Override
    public RolePO findById(Long id) {
        String key = String.format(RedisKey.Role, id);
        String redisValue = redisTemplate.opsForValue().get(key);
        if(Objects.isNull(redisValue)){
            RolePO rolePO = rolePOMapper.selectById(id);
            if(Objects.nonNull(rolePO)){
                redisTemplate.opsForValue().set(key, JSON.toJSONString(rolePO),3600, TimeUnit.SECONDS);
            }
            return rolePO;
        }
        return JSON.parseObject(redisValue,RolePO.class);
    }

    @Override
    public void invalidate(Long id) {
        String key = String.format(RedisKey.Role, id);
        redisTemplate.delete(key);
    }
}
