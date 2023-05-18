package com.nemirovsky.telegrambot.telegram;

import jakarta.persistence.MappedSuperclass;
import com.nemirovsky.telegrambot.model.Answer;
import com.nemirovsky.telegrambot.model.TelebotUser;
import com.nemirovsky.telegrambot.model.UpdateExt;

@MappedSuperclass
public interface Command {
    Class handler();
    Object getFindBy();
    Answer getAnswer(UpdateExt update, TelebotUser user);
}

