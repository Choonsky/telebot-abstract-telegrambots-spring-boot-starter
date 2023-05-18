package com.nemirovsky.telegrambot.telegram.handler;

import jakarta.persistence.MappedSuperclass;
import com.nemirovsky.telegrambot.model.Answer;
import com.nemirovsky.telegrambot.model.TelebotUser;
import com.nemirovsky.telegrambot.model.UpdateType;
import com.nemirovsky.telegrambot.model.UpdateExt;

@MappedSuperclass
public interface Handler {
    UpdateType getHandleType();
    int priority();
    boolean condition(TelebotUser user, UpdateExt update);
    Answer getAnswer(TelebotUser user, UpdateExt update);
}

