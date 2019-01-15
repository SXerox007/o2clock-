package com.blackdreams.sumitthakur.o2clock.ui.home;

import com.blackdreams.sumitthakur.o2clock.base.BaseInteractor;

/**
 * Created by sumitthakur on 01/01/19.
 */

public interface HomeInteractor extends BaseInteractor {

    /**
     * on logout click
     * @param accessToken access token
     */
    void onLogoutClick(final String accessToken);
}
