package com.blackdreams.sumitthakur.o2clock.ui.home.chat;

import android.widget.EditText;

import com.blackdreams.sumitthakur.o2clock.util.ValidateEditText;

import chatpb.Chat;

/**
 * Developer: sumitthakur
 * Date: 22/01/19
 */

public class ChatPresenterImpl implements ChatPresenter {

    private ChatView chatView;
    private ChatInteractor chatInteractor;

    ChatPresenterImpl(final ChatView chatView){
        this.chatView = chatView;
        this.chatInteractor = new ChatInteractorImpl(chatView);
    }

    @Override
    public void msgSend(final Chat.ChatMessage request) {
        chatInteractor.onSendButtonPress(request);
    }

    @Override
    public boolean msgEmptyValidation(final EditText msg) {
       return ValidateEditText.genericEmpty(msg);
    }
}
