package com.nemirovsky.telegrambot.telegram.handler;

import jakarta.annotation.PostConstruct;
import com.nemirovsky.telegrambot.telegram.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public abstract class AbstractHandler implements Handler {

    protected final Map<Object, Command> allCommands = new HashMap<>();
    @Autowired
    private List<Command> commands;

    protected abstract HashMap<Object, Command> createMap();

    @PostConstruct
    private void init() {
        commands.forEach(c -> {
            allCommands.put(c.getFindBy(), c);
            if(Objects.equals(c.handler().getName(), this.getClass().getName())) {
                createMap().put(c.getFindBy(), c);
                System.out.println(c.getClass().getSimpleName() + " was added for " + this.getClass().getSimpleName());
            }
        });
    }
}
