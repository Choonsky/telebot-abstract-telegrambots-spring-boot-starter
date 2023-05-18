package com.nemirovsky.telegrambot;

import jakarta.annotation.PostConstruct;
import com.nemirovsky.telegrambot.model.Answer;
import com.nemirovsky.telegrambot.model.UpdateExt;
import com.nemirovsky.telegrambot.telegram.handler.UpdateExtHandler;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;

import static com.nemirovsky.telegrambot.telegram.TempCache.chatIds;

@Component
public class BotComponent extends TelegramLongPollingBot {

    private final TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

    UpdateExtHandler handler;
    String botName;
    String botToken;

    @PostConstruct
    public void init() {
        try {
            botsApi.registerBot(this);
            System.out.println("Telegram bot has been registered!");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    BotComponent(@Value("${telegram.botToken}") String botToken,
                 @Value("${telegram.botName}") String botName, UpdateExtHandler handler) throws TelegramApiException {
        super(botToken);
        this.botToken = botToken;
        this.botName = botName;
        this.handler = handler;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        System.out.println("Got an update!" + update);

        if (update.hasMessage()) {
            var msg = update.getMessage();
            var chatId = String.valueOf(msg.getChatId());
            var userName = msg.getFrom().getUserName();
            chatIds.put(userName, chatId);
            if (msg.getText().contains("users")) {
                try {
                    sendNotification(chatId, chatIds.toString());
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    var reply = msg.getText().contains("zhopa") ? "No zhopa in serious chat!" : "Regular bot response on " +
                            "\"" + msg.getText() + "\" text.";
                    sendNotification(chatId, reply);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        UpdateExt updateExt = new UpdateExt(update);
        Answer answer = handler.request(updateExt);
        this.execute(answer.getBotApiMethod());
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void onRegister() {
        System.out.println("-------> Bot has been registered!");
        super.onRegister();
    }

    public void sendNotification(String chatId, String msg) throws TelegramApiException {
        var response = new SendMessage(chatId, msg);
        execute(response);
    }

}
