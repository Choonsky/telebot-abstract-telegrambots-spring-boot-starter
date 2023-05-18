package com.nemirovsky.telegrambot.telegram;

import com.nemirovsky.telegrambot.model.Answer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class SendMessageBuilder {
    private SendMessage sendMessage;

    public SendMessageBuilder() {
        this.sendMessage = new SendMessage();
    }

    public SendMessageBuilder chatId(Long chatId) {
        this.sendMessage.setChatId(chatId);
        return this;
    }

    public SendMessageBuilder message(String message) {
        this.sendMessage.setText(message);
        return this;
    }

    public Answer build() throws Exception {
        Answer answer = new Answer();
        answer.setBotApiMethod(sendMessage);
        return answer;
    }
}
