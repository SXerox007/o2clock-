package com.blackdreams.sumitthakur.o2clock.fragment.home.drawer.chat.allusers;

import com.blackdreams.sumitthakur.o2clock.base.BaseInteractor;

/**
 * Developer: sumitthakur
 * Date: 16/01/19
 */

public interface AllUsersInteractor extends BaseInteractor {

    /**
     * Get all the users and groups
     */
    void allUsersAndGroups(String accessToken);
}
