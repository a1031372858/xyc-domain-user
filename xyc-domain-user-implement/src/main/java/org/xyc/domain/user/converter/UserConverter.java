package org.xyc.domain.user.converter;

import org.mapstruct.Mapper;
import org.xyc.domain.base.BaseConverter;
import org.xyc.domain.user.model.po.UserPO;
import org.xyc.domain.user.model.to.UserTO;

/**
 * @author xuyachang
 * @date 2024/2/11
 */
@Mapper(componentModel = "spring")
public abstract class UserConverter extends BaseConverter<UserTO, UserPO> {
}
