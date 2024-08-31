package org.xyc.domain.user.model.po;

import lombok.Data;
import org.xyc.domain.base.model.ModelBase;

/**
 * 角色-用户关系
 * @author xuyachang
 * @date 2024/8/29
 */
@Data
public class RoleRelationPO extends ModelBase {

    /**
     * 角色Id
     */
    private Long roleId;
    /**
     * 用户Id
     */
    private Long userId;
}
