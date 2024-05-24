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
@FeignClient("xyc-user")
public interface UserReadFacade {

    @PostMapping("findUserByPhone")
    Response<UserTO> findUserByPhone(@RequestBody UserTO userTO);
}
