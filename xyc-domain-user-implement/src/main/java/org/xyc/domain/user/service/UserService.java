package org.xyc.domain.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.xyc.domain.base.exception.BusinessException;
import org.xyc.domain.user.cache.UserCache;
import org.xyc.domain.user.converter.UserConverter;
import org.xyc.domain.user.mapper.UserPOMapper;
import org.xyc.domain.user.model.po.UserPO;
import org.xyc.domain.user.model.to.UserTO;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author xuyachang
 * @date 2024/2/17
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserPOMapper userPOMapper;
    private final UserConverter userConverter;

    private final UserCache userCache;

    public UserTO findUserById(Long id){
        //从缓存中获取
        UserPO itemPO = userCache.findById(id);
        return userConverter.po2to(itemPO);
    }

    public UserTO findUserByPhone(String phone){
        QueryWrapper<UserPO> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone",phone);
        UserPO userPO = userPOMapper.selectOne(userQueryWrapper);
        return userConverter.convertT(userPO);
    }


    public Boolean register(UserTO userTO){
        UserPO userPO = new UserPO();
        userPO.setName(userTO.getName());
        if(Objects.isNull(userTO.getUsername())){
            userPO.setUsername("new_user" + userTO.getPhone());
        }else {
            userPO.setUsername(userTO.getUsername());
        }
        userPO.setPhone(userTO.getPhone());
        if(Objects.nonNull(userTO.getPassword())){
            userPO.setPassword(DigestUtils.md5DigestAsHex(userTO.getPassword().getBytes(StandardCharsets.UTF_8)));
        }else{
            //没有密码时密码为空，无法使用密码登录
            userPO.setPassword(userTO.getPassword());
        }
        userPOMapper.insert(userPO);
        return Boolean.TRUE;
    }

    public Boolean updateById(UserTO userTO){
        if(Objects.isNull(userTO) || Objects.isNull(userTO.getId())){
            throw new BusinessException("入参或id为空");
        }
        userPOMapper.updateById(userConverter.to2po(userTO));
        //删除缓存
        userCache.invalidate(userTO.getId());
        return Boolean.TRUE;
    }
}
