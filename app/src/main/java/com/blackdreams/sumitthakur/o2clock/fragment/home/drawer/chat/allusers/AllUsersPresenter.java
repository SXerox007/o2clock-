package com.blackdreams.sumitthakur.o2clock.fragment.home.drawer.chat.allusers;

import com.blackdreams.sumitthakur.o2clock.base.BasePresenter;

import chatpb.Chat;

/**
 * Developer: sumitthakur
 * Date: 16/01/19
 */

public interface AllUsersPresenter extends BasePresenter {

    /**
     * Get all users and groups
     */
    void getAllUsersAndGroups();


    /**
     * get the sender data
     */
    void getLoginUserData();

    /**
     *
     * @param sender sender data
     * @param reciever reciever data
     */
    void startChat(final Chat.User sender,final Chat.User reciever);
}
