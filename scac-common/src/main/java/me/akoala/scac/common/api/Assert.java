package me.akoala.scac.common.api;

import cn.hutool.core.util.StrUtil;

public class Assert {

    public static <T> T notNull(T object, MessageCode messageCode) throws IllegalArgumentException {
        if (object == null) {
            throw new ApiException(messageCode);
        }
        return object;
    }

    public static void isTrue(boolean expression, MessageCode messageCode) throws IllegalArgumentException {
        if (false == expression) {
            throw new ApiException(messageCode);
        }
    }

}
