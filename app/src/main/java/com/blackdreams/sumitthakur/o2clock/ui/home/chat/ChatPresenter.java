package com.blackdreams.sumitthakur.o2clock.ui.home.chat;

import android.widget.EditText;

import com.blackdreams.sumitthakur.o2clock.base.BasePresenter;

import chatpb.Chat;

/**
 * Developer: sumitthakur
 * Date: 22/01/19
 */

public interface ChatPresenter extends BasePresenter {

    /**
     *  on message send tap
     * @param request reuest
     */
    void msgSend(final Chat.ChatMessage request);

    /**
     *  msg empty validation
     * @param msg message
     */
    boolean msgEmptyValidation(EditText msg);
}
