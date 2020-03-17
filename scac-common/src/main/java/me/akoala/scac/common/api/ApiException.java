package me.akoala.scac.common.api;

import lombok.Getter;

public class ApiException extends RuntimeException {

    @Getter
    private MessageCode messageCode;

    public ApiException(MessageCode messageCode) {
        super(messageCode.getMessage());
        this.messageCode = messageCode;
    }
}
