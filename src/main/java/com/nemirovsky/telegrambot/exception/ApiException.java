package com.nemirovsky.telegrambot.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ApiException extends Exception {

    public static final String MESSAGE_PREFIX = "TelegramBot API exception: ";
    public static final int CODE = 481;

    public ApiException(String message) {
        super(MESSAGE_PREFIX + message);
    }

}
