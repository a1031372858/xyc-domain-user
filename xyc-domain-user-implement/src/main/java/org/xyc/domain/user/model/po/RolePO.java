package org.xyc.domain.user.model.po;

import lombok.Data;
import org.xyc.domain.base.model.ModelBase;

/**
 * 角色
 * @author xuyachang
 * @date 2024/8/29
 */
@Data
public class RolePO extends ModelBase {

    /**
     * 姓名
     */
    private String name;

    /**
     * 描述
     */
    private String description;
}
