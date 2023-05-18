package com.nemirovsky.telegrambot.model;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class UpdateExt {
    @Getter
    private final UpdateType updateType;

    @Getter
    private final Long userId; // = chatId

    @Getter
    private String userName; // @username

    @Getter
    private String userNameExt; // Name "@username" FamilyName

    @Getter
    private String command; // если это команда, то запишем её

    @Getter
    private final Update update; // сохраним сам update, чтобы в случае чего, его можно было достать

    @Getter
    private final List<String> args; // просто поделим текст сообщения, в будущем это поможет

    public UpdateExt(Update update) {
        this.update = update;
        this.updateType = handleTelegramType();
        this.userId = handleUserId();
        this.args = handleArgs();
        this.command = handleCommand();
    }

    public String handleCommand() {
        if(update.hasMessage()) {
            if(update.getMessage().hasText()) {
                if(update.getMessage().getText().startsWith("/")) {
                    return update.getMessage().getText().split(" ")[0];
                } else return update.getMessage().getText();
            }
        } if(update.hasCallbackQuery()) {
            return update.getCallbackQuery().getData().split(" ")[0];
        }
        return "";
    }

    private UpdateType handleTelegramType() {

        if(update.hasCallbackQuery())
            return UpdateType.CallBack;

        if(update.hasMessage()) {
            if(update.getMessage().hasText()) {
                if(update.getMessage().getText().startsWith("/"))
                    return UpdateType.Command;
                else
                    return UpdateType.Text;
            } else if(update.getMessage().hasSuccessfulPayment()) {
                return UpdateType.SuccessPayment;
            } else if(update.getMessage().hasPhoto())
                return UpdateType.Photo;
        } else if(update.hasPreCheckoutQuery()) {
            return UpdateType.PreCheckoutQuery;
        } else if(update.hasChatJoinRequest()) {
            return UpdateType.ChatJoinRequest;
        } else if(update.hasChannelPost()) {
            return UpdateType.ChannelPost;
        }
        else if(update.hasMyChatMember()) {
            return UpdateType.MyChatMember;
        }
        if(update.getMessage().hasDocument()) {
            return UpdateType.Text;
        }
        return UpdateType.Unknown;
    }

    private Long handleUserId() {
        if (updateType == UpdateType.PreCheckoutQuery) {
            userNameExt = getNameByUser(update.getPreCheckoutQuery().getFrom());
            userName = update.getPreCheckoutQuery().getFrom().getUserName();
            return update.getPreCheckoutQuery().getFrom().getId();
        } else if(updateType == UpdateType.ChatJoinRequest) {
            userNameExt = getNameByUser(update.getChatJoinRequest().getUser());
            userName = update.getChatJoinRequest().getUser().getUserName();
            return update.getChatJoinRequest().getUser().getId();
        } else if (updateType == UpdateType.CallBack) {
            userNameExt = getNameByUser(update.getCallbackQuery().getFrom());
            userName = update.getCallbackQuery().getFrom().getUserName();
            return update.getCallbackQuery().getFrom().getId();
        } else if(updateType == UpdateType.MyChatMember){
            userNameExt = update.getMyChatMember().getChat().getTitle();
            userName = update.getMyChatMember().getChat().getUserName();
            return update.getMyChatMember().getFrom().getId();
        } else {
            userNameExt = getNameByUser(update.getMessage().getFrom());
            userName = update.getMessage().getFrom().getUserName();
            return update.getMessage().getFrom().getId();
        }
    }

    private List<String> handleArgs() {
        List<String> list = new LinkedList<>();

        if(updateType == UpdateType.Command) {
            String[] args = getUpdate().getMessage().getText().split(" ");
            Collections.addAll(list, args);
            list.remove(0);

            return list;
        } else if (updateType == UpdateType.Text) {
            list.add(getUpdate().getMessage().getText());

            return list;
        } else if (updateType == UpdateType.CallBack) {
            String[] args = getUpdate().getCallbackQuery().getData().split(" ");
            Collections.addAll(list, args);
            list.remove(0);

            return list;
        }
        return new ArrayList<>();
    }

    private String getNameByUser(User user) {
        if(user.getIsBot())
            return "SOME BOT";

        StringBuilder sb = new StringBuilder("");

        if(!user.getFirstName().isBlank() || !user.getFirstName().isEmpty())
            sb.append(user.getFirstName());

        if(!user.getUserName().isBlank() || !user.getUserName().isEmpty())
            sb.append(" \"").append(user.getUserName()).append("\"");

        if(!user.getLastName().isBlank() || !user.getLastName().isEmpty())
            sb.append(" ").append(user.getLastName());

        return sb.isEmpty() ? "Anonymous user" : sb.toString();
    }

    //Лог
    public String getLog() {

        return "USER_ID: " + getUserId() +
                ", USER_NAME: " + getUserNameExt() +
                ", TYPE: " + getUpdateType() +
                ", ARGS: " + getArgs().toString() +
                ", COMMAND: " + getCommand();
    }

}
