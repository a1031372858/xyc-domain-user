package org.xyc.domain.user.cache;

import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.xyc.domain.base.cache.BaseCache;
import org.xyc.domain.user.common.RedisKey;
import org.xyc.domain.user.mapper.PermissionPOMapper;
import org.xyc.domain.user.model.po.PermissionPO;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author xuyachang
 * @date 2024/8/31
 */
@Component
@RequiredArgsConstructor
public class PermissionCache implements BaseCache<PermissionPO> {

    private final RedisTemplate<String,String> redisTemplate;

    private final PermissionPOMapper permissionPOMapper;
    @Override
    public PermissionPO findById(Long id) {
        String key = String.format(RedisKey.Permission, id);
        String redisValue = redisTemplate.opsForValue().get(key);
        if(Objects.isNull(redisValue)){
            PermissionPO permissionPO = permissionPOMapper.selectById(id);
            if(Objects.nonNull(permissionPO)){
                redisTemplate.opsForValue().set(key, JSON.toJSONString(permissionPO),3600, TimeUnit.SECONDS);
            }
            return permissionPO;
        }
        return JSON.parseObject(redisValue,PermissionPO.class);
    }

    @Override
    public void invalidate(Long id) {
        String key = String.format(RedisKey.Permission, id);
        redisTemplate.delete(key);
    }
}
