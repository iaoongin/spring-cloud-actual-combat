package me.akoala.scac.common.api;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class R<T> {

    private String code;
    private String msg;
    private T data;

    public R(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public R(MessageCode messageCode, T data) {
        this.code = messageCode.getCode();
        this.msg = messageCode.getMsg();
        this.data = data;
    }

    public R(MessageCode messageCode) {
        this.code = messageCode.getCode();
        this.msg = messageCode.getMsg();
        this.data = null;
    }

    public static <T> R success(T data) {
        return new R(MessageCode.COMMON_SUCCESS, data);
    }

    public static <T> R just(MessageCode messageCode, T data) {
        return new R(messageCode, data);
    }

    public static <T> R just(MessageCode messageCode) {
        return new R(messageCode);
    }

    public static <T> R fail() {
        return new R(MessageCode.COMMON_FAILURE);
    }
}
