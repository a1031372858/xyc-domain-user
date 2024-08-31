package org.xyc.domain.user.converter;

import org.mapstruct.Mapper;
import org.xyc.domain.base.BaseConverter;
import org.xyc.domain.user.model.po.PermissionPO;
import org.xyc.domain.user.model.to.PermissionTO;

/**
 * @author xuyachang
 * @date 2024/8/31
 */
@Mapper(componentModel = "spring")
public abstract class PermissionConverter extends BaseConverter<PermissionTO, PermissionPO> {

}
