package org.xyc.domain.user.facade;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.xyc.domain.base.model.Response;
import org.xyc.domain.user.model.to.UserTO;

/**
 * @author xuyachang
 * @date 2024/5/23
 */
@FeignClient(value = "xyc-user",contextId = "UserReadFacade")
public interface UserReadFacade {

    @PostMapping("findUserAndPermissionByPhone")
    Response<UserTO> findUserAndPermissionByPhone(@RequestBody UserTO userTO);
}
