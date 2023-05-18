package com.nemirovsky.telegrambot.telegram.handler;

import com.nemirovsky.telegrambot.model.Answer;
import com.nemirovsky.telegrambot.model.TelebotUser;
import com.nemirovsky.telegrambot.model.UpdateType;
import com.nemirovsky.telegrambot.model.UpdateExt;
import com.nemirovsky.telegrambot.telegram.Command;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CommandHandler extends AbstractHandler {

    private HashMap<Object, Command> hashMap = new HashMap<>();

    @Override
    protected HashMap<Object, Command> createMap() {
        return hashMap;
    }

    @Override
    public UpdateType getHandleType() {
        return UpdateType.Command;
    }

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public boolean condition(TelebotUser user, UpdateExt update) {
        return hashMap.containsKey(update.getCommand());
    }

    @Override
    public Answer getAnswer(TelebotUser user, UpdateExt update) {
        return hashMap.get(update.getCommand()).getAnswer(update, user);
    }
}