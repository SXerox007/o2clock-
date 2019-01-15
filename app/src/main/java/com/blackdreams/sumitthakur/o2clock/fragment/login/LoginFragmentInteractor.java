package com.blackdreams.sumitthakur.o2clock.fragment.login;

import com.blackdreams.sumitthakur.o2clock.base.BaseInteractor;

/**
 * Created by sumitthakur on 01/01/19.
 */

public interface LoginFragmentInteractor extends BaseInteractor {
    /**
     *
     * @param email login
     * @param password password
     */
    void onLoginClick(final String email,final String password);
}
