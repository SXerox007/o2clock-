package com.blackdreams.sumitthakur.o2clock.ui.home.chat;

import chatpb.Chat;

/**
 * Developer: sumitthakur
 * Date: 22/01/19
 */

public class ChatInteractorImpl implements ChatInteractor {

    private ChatView chatView;

    ChatInteractorImpl(final ChatView chatView) {
        this.chatView = chatView;
    }

    @Override
    public void onSendButtonPress(final Chat.ChatMessage request) {

    }
}
