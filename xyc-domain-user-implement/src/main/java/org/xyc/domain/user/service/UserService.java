package org.xyc.domain.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.xyc.domain.base.model.Response;
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

    public Response<UserTO> findUserByPhone(String phone){
        QueryWrapper<UserPO> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone",phone);
        UserPO userPO = userPOMapper.selectOne(userQueryWrapper);
        return Response.success(userConverter.convertT(userPO));
    }


    public Response<Boolean> register(UserTO userTO){
        UserPO userPO = new UserPO();
        userPO.setUsername("new_user" + userTO.getPhone());
        userPO.setPhone(userTO.getPhone());
        if(Objects.nonNull(userTO.getPassword())){
            userPO.setPassword(DigestUtils.md5DigestAsHex(userTO.getPassword().getBytes(StandardCharsets.UTF_8)));
        }else{
            //没有密码时密码为空，无法使用密码登录
            userPO.setPassword(userTO.getPassword());
        }
        userPOMapper.insert(userPO);

        return Response.success(Boolean.TRUE);
    }
}
