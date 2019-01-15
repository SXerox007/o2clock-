package com.blackdreams.sumitthakur.o2clock.fragment.login;

import com.blackdreams.sumitthakur.o2clock.base.BaseView;

/**
 * Created by sumitthakur on 01/01/19.
 */

public interface LoginFragmentView extends BaseView {

    /**
     * on login success
     */
    void onLoginSuccess();

    /**
     * on login error
     */
    void onLoginError(final String message,final String title);


    /**
     *
     * @param message message
     * @param title title
     */
    void showErrorDialog(final String message,final String title);
}
