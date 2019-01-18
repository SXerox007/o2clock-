package com.blackdreams.sumitthakur.o2clock.fragment.home.drawer.chat.allusers;

import com.blackdreams.sumitthakur.o2clock.base.BaseView;

import java.util.List;

import chatpb.Chat;

/**
 * Developer: sumitthakur
 * Date: 16/01/19
 */

public interface AllUsersView extends BaseView {

    /**
     *  success
     */
    void onSuccess(final List<Chat.User> data);

    /**
     * failure
     */
    void onFailure(final String message,final String title);


    /**
     * login user data get
     */
    void onLoginUserDataSuccess(final Chat.User senderId);


    /**
     * p2p connection start success
     */
    void onP2PChatConnectionStartSuccess();
}
