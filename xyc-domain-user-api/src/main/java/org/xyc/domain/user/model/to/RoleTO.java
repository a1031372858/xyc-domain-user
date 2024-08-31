package org.xyc.domain.user.model.to;

import lombok.Data;
import org.xyc.domain.base.model.ModelBase;

import java.util.List;

/**
 * 角色
 * @author xuyachang
 * @date 2024/8/29
 */
@Data
public class RoleTO extends ModelBase {

    /**
     * 姓名
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 权限列表
     */
    private List<PermissionTO> permissionTOList;
}
