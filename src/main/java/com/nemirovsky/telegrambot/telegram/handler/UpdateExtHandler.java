package com.nemirovsky.telegrambot.telegram.handler;

import com.nemirovsky.telegrambot.exception.ApiException;
import com.nemirovsky.telegrambot.model.Answer;
import com.nemirovsky.telegrambot.model.UpdateExt;
import com.nemirovsky.telegrambot.telegram.TelebotUserService;
import org.springframework.stereotype.Service;

@Service
public class UpdateExtHandler {
    private final TelebotUserService userService;

    private final HandlersMap commandMap;

    public UpdateExtHandler(TelebotUserService userService, HandlersMap commandMap) {
        this.userService = userService;
        this.commandMap = commandMap;
    }

    public Answer request(UpdateExt updateExt) throws ApiException {
        return commandMap.execute(updateExt,
                userService.findUserByUpdate(updateExt));
    }
}
