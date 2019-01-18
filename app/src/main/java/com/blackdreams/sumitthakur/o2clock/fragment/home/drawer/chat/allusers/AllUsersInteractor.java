package com.blackdreams.sumitthakur.o2clock.fragment.home.drawer.chat.allusers;

import com.blackdreams.sumitthakur.o2clock.base.BaseInteractor;

import chatpb.Chat;

/**
 * Developer: sumitthakur
 * Date: 16/01/19
 */

public interface AllUsersInteractor extends BaseInteractor {

    /**
     * Get all the users and groups
     */
    void allUsersAndGroups(final String accessToken);

    /**
     *
     * @param accessToken access Token
     */
    void getLogedInData(final String accessToken);


    /**
     *
     * @param sender sender data
     * @param reciever reciever data
     */
    void setupP2PChat(final Chat.User sender, final Chat.User reciever);
}
