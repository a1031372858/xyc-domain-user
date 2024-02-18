package org.xyc.domain.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xyc.domain.base.model.Response;
import org.xyc.domain.user.converter.UserConverter;
import org.xyc.domain.user.mapper.UserPOMapper;
import org.xyc.domain.user.model.po.UserPO;
import org.xyc.domain.user.model.to.UserTO;

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
}
