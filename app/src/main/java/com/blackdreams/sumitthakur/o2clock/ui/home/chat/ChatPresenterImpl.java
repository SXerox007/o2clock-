package com.blackdreams.sumitthakur.o2clock.ui.home.chat;

import chatpb.Chat;

/**
 * Developer: sumitthakur
 * Date: 22/01/19
 */

public class ChatPresenterImpl implements ChatPresenter {

    private ChatView chatView;

    ChatPresenterImpl(final ChatView chatView){
        this.chatView = chatView;
    }

    @Override
    public void msgSend(Chat.ChatMessage request) {

    }
}
