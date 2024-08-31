package org.xyc.domain.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.xyc.domain.base.exception.BusinessException;
import org.xyc.domain.user.cache.PermissionCache;
import org.xyc.domain.user.cache.RoleCache;
import org.xyc.domain.user.cache.UserCache;
import org.xyc.domain.user.converter.PermissionConverter;
import org.xyc.domain.user.converter.RoleConverter;
import org.xyc.domain.user.converter.UserConverter;
import org.xyc.domain.user.mapper.RolePermissionPOMapper;
import org.xyc.domain.user.mapper.RoleRelationPOMapper;
import org.xyc.domain.user.mapper.UserPOMapper;
import org.xyc.domain.user.model.po.*;
import org.xyc.domain.user.model.to.PermissionTO;
import org.xyc.domain.user.model.to.RoleTO;
import org.xyc.domain.user.model.to.UserTO;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
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

    private final RoleRelationPOMapper roleRelationPOMapper;

    private final RoleCache roleCache;
    private final PermissionCache permissionCache;
    private final RolePermissionPOMapper rolePermissionPOMapper;

    private final RoleConverter roleConverter;

    private final PermissionConverter permissionConverter;

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

    public UserTO findUserAndPermissionByPhone(String phone){
        QueryWrapper<UserPO> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone",phone);
        UserPO userPO = userPOMapper.selectOne(userQueryWrapper);
        //查用户角色
        List<RoleTO> roleTOList = new ArrayList<>();
        QueryWrapper<RoleRelationPO> roleRelationQuery = new QueryWrapper<>();
        roleRelationQuery.eq("user_id",userPO.getId());
        List<RoleRelationPO> roleRelationPOS = roleRelationPOMapper.selectList(roleRelationQuery);
        if(CollectionUtils.isNotEmpty(roleRelationPOS)){
            roleRelationPOS.forEach(o->{
                RolePO rolePO = roleCache.findById(o.getRoleId());
                RoleTO roleTO = roleConverter.convertT(rolePO);
                //查角色权限
                List<PermissionTO> permissionTOS = new ArrayList<>();
                QueryWrapper<RolePermissionPO> rolePermissionPOQuery = new QueryWrapper<>();
                rolePermissionPOQuery.eq("role_id",rolePO.getId());
                List<RolePermissionPO> rolePermissionPOS = rolePermissionPOMapper.selectList(rolePermissionPOQuery);
                if(CollectionUtils.isNotEmpty(rolePermissionPOS)){
                    rolePermissionPOS.forEach(p->{
                        PermissionPO permissionPO = permissionCache.findById(p.getPermissionId());
                        PermissionTO permissionTO = permissionConverter.convertT(permissionPO);
                        permissionTOS.add(permissionTO);
                    });
                }
                roleTO.setPermissionTOList(permissionTOS);
                roleTOList.add(roleTO);
            });
        }

        UserTO userTO = userConverter.convertT(userPO);
        userTO.setRoleTOList(roleTOList);
        return userTO;
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
