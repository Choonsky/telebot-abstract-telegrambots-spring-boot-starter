package com.nemirovsky.telegrambot.exception;

public class TelebotUserNotFoundException extends ApiException {
    public TelebotUserNotFoundException(String s) {
        super("Telebot user not found: " + s + "!");
    }
}