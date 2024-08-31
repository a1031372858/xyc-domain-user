package org.xyc.domain.user.converter;

import org.mapstruct.Mapper;
import org.xyc.domain.base.BaseConverter;
import org.xyc.domain.user.model.po.RolePO;
import org.xyc.domain.user.model.to.RoleTO;

/**
 * @author xuyachang
 * @date 2024/8/31
 */
@Mapper(componentModel = "spring")
public abstract class RoleConverter extends BaseConverter<RoleTO, RolePO> {
}
