package org.xyc.domain.user.model.po;

import lombok.Data;
import org.xyc.domain.base.model.ModelBase;

/**
 * 权限
 * @author xuyachang
 * @date 2024/8/29
 */
@Data
public class PermissionPO extends ModelBase {

    /**
     * 名称
     */
    private String name;
    /**
     * 权限类型
     */
    private String type;

    /**
     * 功能编码
     */
    private String functionCode;

    /**
     * 接口路径
     */
    private String path;

    /**
     * 描述
     */
    private String description;
}
