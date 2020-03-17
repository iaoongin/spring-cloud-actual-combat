package me.akoala.scac.common.api;

import lombok.Getter;

@Getter
public enum MessageCode {

    COMMON_SUCCESS("200", "执行成功"),
    COMMON_FAILURE("500", "执行失败"),
    AUTH_UNAUTHORIZED("401000", "身份鉴权失败."),
    AUTH_INVALID_CREDENTIALS("401001", "密码错误."),
    AUTH_USER_DOES_NOT_EXIST("401002", "用户不存在."),
    ;

    //Message 编码
    private String code;
    //Message 描叙
    private String message;

    MessageCode(String code) {
        this.code = code;
    }

    MessageCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String message) {
        this.message = message;
    }

    public static MessageCode getStatusCode(String status) {
        for (MessageCode unit : MessageCode.values()) {
            if (unit.getCode().equals(status)) {
                return unit;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "{code:'" + code + '\'' +
                ", message:'" + message + '\'' +
                '}';
    }
}