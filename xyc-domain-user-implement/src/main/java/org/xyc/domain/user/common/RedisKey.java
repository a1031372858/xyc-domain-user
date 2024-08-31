package org.xyc.domain.user.common;

/**
 * @author xuyachang
 * @date 2024/3/17
 */
public interface RedisKey {
    String User = "user:user:%d";

    String Role = "user:role:%d";

    String Permission = "user:permission:%d";

}
