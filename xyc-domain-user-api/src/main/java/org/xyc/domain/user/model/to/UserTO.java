package org.xyc.domain.user.model.to;

import lombok.Data;
import org.xyc.domain.base.model.ModelBase;

import java.time.LocalDateTime;

/**
 * @author xuyachang
 * @date 2024/2/11
 */
@Data
public class UserTO extends ModelBase {

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 登录用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;
}
