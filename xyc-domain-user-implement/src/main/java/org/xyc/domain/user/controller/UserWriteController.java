package org.xyc.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xyc.domain.base.model.Response;
import org.xyc.domain.user.model.to.UserTO;
import org.xyc.domain.user.service.UserService;

/**
 * @author xuyachang
 * @date 2024/2/11
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("api/user/write")
public class UserWriteController {

    private final UserService userService;

    @PostMapping("register")
    public Response<Boolean> register(UserTO userTO){
        return userService.register(userTO);
    }

}
