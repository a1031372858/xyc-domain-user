package org.xyc.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.xyc.domain.base.model.Response;
import org.xyc.domain.user.model.po.UserPO;
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
        return userService.findUserByPhone(userTO.getPhone());
    }

    @GetMapping("getByPhone")
    public Response<UserTO> getUserByPhone(UserTO userTO){
        return userService.findUserByPhone(userTO.getPhone());
    }
}
