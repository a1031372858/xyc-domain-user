package org.xyc.domain.user.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.xyc.domain.base.model.Response;
import org.xyc.domain.user.model.to.UserTO;
import org.xyc.domain.user.service.UserService;

/**
 * @author xuyachang
 * @date 2024/6/23
 */
@RestController
@RequiredArgsConstructor
public class UserWriteFacadeImpl implements UserWriteFacade{

    private final UserService userService;

    @Override
    public Response<Boolean> updateUserName(UserTO userTO) {
        return Response.success(userService.updateById(userTO));
    }
}
