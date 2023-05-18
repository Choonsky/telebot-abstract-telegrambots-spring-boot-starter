package com.nemirovsky.telegrambot.telegram;

import com.nemirovsky.telegrambot.model.Answer;
import com.nemirovsky.telegrambot.model.TelebotUser;
import com.nemirovsky.telegrambot.model.UpdateExt;
import com.nemirovsky.telegrambot.telegram.handler.CommandHandler;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class StartCommand implements Command {
    @Override
    public Class handler() {
        return CommandHandler.class;
    }

    @Override
    public Object getFindBy() {
        return "/start";
    }

    @SneakyThrows
    @Override
    public Answer getAnswer(UpdateExt update, TelebotUser user) {
        return new SendMessageBuilder().chatId(user.getChatId()).message("Hello!").build();
    }
}
