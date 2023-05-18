package com.nemirovsky.telegrambot.telegram;

import com.nemirovsky.telegrambot.exception.ApiException;
import com.nemirovsky.telegrambot.exception.TelebotUserNotFoundException;
import com.nemirovsky.telegrambot.model.TelebotState;
import com.nemirovsky.telegrambot.model.TelebotUser;
import com.nemirovsky.telegrambot.model.UpdateExt;
import com.nemirovsky.telegrambot.repository.TelebotStateRepository;
import com.nemirovsky.telegrambot.repository.TelebotUserRepository;
import org.springframework.stereotype.Service;

@Service
public class TelebotUserService {
    private final TelebotUserRepository userRepo;

    private final TelebotStateRepository stateRepo;

    public TelebotUserService(TelebotUserRepository userRepo, TelebotStateRepository stateRepo) {
        this.userRepo = userRepo;
        this.stateRepo = stateRepo;
    }

    public TelebotUser findUserByUpdate(UpdateExt updateExt) throws ApiException {

        if(userRepo.findByChatId(updateExt.getUserId()).isPresent()) {
            TelebotUser user =
                    userRepo.findByChatId(updateExt.getUserId())
                            .orElseThrow(() -> new TelebotUserNotFoundException(updateExt.getUserId().toString()));

            if(user.getUserName() == null && updateExt.getUserName() != null)
                user.setUserName(updateExt.getUserName());

            if(user.getUserName() != null)
                if (!user.getUserName().equals(updateExt.getUserName()))
                    user.setUserName(updateExt.getUserName());

            if(!user.getUserNameExt().equals(updateExt.getUserNameExt()))
                user.setUserNameExt(updateExt.getUserNameExt());

            return user;
        }
        try {
            TelebotUser user = new TelebotUser();
            user.setUserNameExt(updateExt.getUserNameExt());
            user.setStatus(1);
            user.setChatId(updateExt.getUserId());
            user.setUserName(updateExt.getUserName());

            TelebotState state = new TelebotState();
            state.setStateValue(null);
            state.setUser(user);

            stateRepo.save(state);

            user.setState(state);
            userRepo.save(user);

            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
