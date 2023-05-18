package com.nemirovsky.telegrambot.model;

import lombok.Data;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.groupadministration.SetChatPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.send.SendVideoNote;
import org.telegram.telegrambots.meta.api.methods.send.SendVoice;
import org.telegram.telegrambots.meta.api.methods.stickers.AddStickerToSet;
import org.telegram.telegrambots.meta.api.methods.stickers.CreateNewStickerSet;
import org.telegram.telegrambots.meta.api.methods.stickers.SetStickerSetThumb;
import org.telegram.telegrambots.meta.api.methods.stickers.UploadStickerFile;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;

@Data
public class Answer {
    private SendDocument sendDocument;
    private SendPhoto sendPhoto;
    private SendVideo sendVideo;
    private SendVideoNote sendVideoNote;
    private SendSticker sendSticker;
    private SendAudio sendAudio;
    private SendVoice sendVoice;
    private SendMediaGroup sendMediaGroup;
    private SetChatPhoto setChatPhoto;
    private AddStickerToSet addStickerToSet;
    private SetStickerSetThumb setStickerSetThumb;
    private CreateNewStickerSet createNewStickerSet;
    private UploadStickerFile uploadStickerFile;
    private EditMessageMedia editMessageMedia;
    private SendAnimation sendAnimation;
    private BotApiMethod<?> botApiMethod;

}
