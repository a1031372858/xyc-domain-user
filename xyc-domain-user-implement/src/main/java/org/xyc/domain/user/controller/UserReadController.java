package org.xyc.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.xyc.domain.base.model.Response;
import org.xyc.domain.user.model.to.UserTO;
import org.xyc.domain.user.service.UserService;

/**
 * @author xuyachang
 * @date 2024/2/11
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("api/user/read")
public class UserReadController {

    private final UserService userService;


    @PostMapping("findByPhone")
    public Response<UserTO> findUserByPhone(@RequestBody UserTO userTO){
        return Response.success(userService.findUserByPhone(userTO.getPhone()));
    }

    @GetMapping("getByPhone")
    public Response<UserTO> getUserByPhone(UserTO userTO){
        return Response.success(userService.findUserByPhone(userTO.getPhone()));
    }

    @GetMapping("getUserById")
    public Response<UserTO> getUserByRedis(Long id){
        return Response.success(userService.findUserById(id));
    }
}
