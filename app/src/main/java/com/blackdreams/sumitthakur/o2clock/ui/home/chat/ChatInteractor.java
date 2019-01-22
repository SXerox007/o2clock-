package com.blackdreams.sumitthakur.o2clock.ui.home.chat;

import com.blackdreams.sumitthakur.o2clock.base.BaseInteractor;

import chatpb.Chat;

/**
 * Developer: sumitthakur
 * Date: 22/01/19
 */

public interface ChatInteractor extends BaseInteractor {

    /**
     * on send button press
     * @param request request
     */
    void onSendButtonPress(final Chat.ChatMessage request);
}
