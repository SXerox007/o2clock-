package com.blackdreams.sumitthakur.o2clock.ui.home.chat;

import com.blackdreams.sumitthakur.o2clock.base.BaseView;

import chatpb.Chat;

/**
 * Developer: sumitthakur
 * Date: 22/01/19
 */

public interface ChatView extends BaseView {

    /**
     * on msg send success
     */
    void onSuccess();

    /**
     *
     * @param message message
     * @param title title
     */
    void onFailure(final String message,final String title);


    /**
     *   on Message Recived
     * @param message message
     */
    void onMessageRecived(final Chat.ChatMessage message);

}
