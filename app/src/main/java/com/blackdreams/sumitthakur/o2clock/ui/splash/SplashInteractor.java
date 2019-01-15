package com.blackdreams.sumitthakur.o2clock.ui.splash;

import com.blackdreams.sumitthakur.o2clock.base.BaseInteractor;

/**
 * Created by sumitthakur on 31/12/18.
 */

public interface SplashInteractor extends BaseInteractor {

    /**
     *
     * @param accessToken accessToken
     */
    void checkAccessToken(final String accessToken);
}
