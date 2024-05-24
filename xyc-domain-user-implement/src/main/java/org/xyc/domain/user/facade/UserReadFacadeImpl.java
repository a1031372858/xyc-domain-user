package org.xyc.domain.user.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.xyc.domain.base.model.Response;
import org.xyc.domain.user.model.to.UserTO;
import org.xyc.domain.user.service.UserService;

/**
 * @author xuyachang
 * @date 2024/5/23
 */
@RestController
@RequiredArgsConstructor
public class UserReadFacadeImpl implements UserReadFacade{

    private final UserService userService;

    @Override
    public Response<UserTO> findUserByPhone(@RequestBody UserTO userTO){
        return Response.success(userService.findUserByPhone(userTO.getPhone()));
    }
}
