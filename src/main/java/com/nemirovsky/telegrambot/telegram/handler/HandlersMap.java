package com.nemirovsky.telegrambot.telegram.handler;

import jakarta.annotation.PostConstruct;
import com.nemirovsky.telegrambot.model.Answer;
import com.nemirovsky.telegrambot.model.TelebotUser;
import com.nemirovsky.telegrambot.model.UpdateType;
import com.nemirovsky.telegrambot.model.UpdateExt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class HandlersMap {
    private HashMap<UpdateType, List<Handler>> hashMap = new HashMap<>();
    private final List<Handler> handlers;

    public HandlersMap(List<Handler> handlers) {
        this.handlers = handlers;
    }

    @PostConstruct
    private void init() {
        for (Handler handler : handlers) {
            if (!hashMap.containsKey(handler.getHandleType()))
                hashMap.put(handler.getHandleType(), new ArrayList<>());

            hashMap.get(handler.getHandleType()).add(handler);
        }

        hashMap.values().forEach(h -> h.sort((o1, o2) -> o2.priority() - o1.priority()));
    }

    public Answer execute(UpdateExt updateExt, TelebotUser telebotUser) {
        if(!hashMap.containsKey(updateExt.getUpdateType()))
            return new Answer();

        for (Handler handler : hashMap.get(updateExt.getUpdateType())) {
            if(handler.condition(telebotUser, updateExt))
                return handler.getAnswer(telebotUser, updateExt);
        }
        return null;
    }

}