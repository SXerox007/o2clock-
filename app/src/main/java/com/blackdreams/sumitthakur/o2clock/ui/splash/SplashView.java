package com.blackdreams.sumitthakur.o2clock.ui.splash;

import com.blackdreams.sumitthakur.o2clock.base.BaseView;

/**
 * Created by sumitthakur on 31/12/18.
 */

public interface SplashView extends BaseView {

    /**
     * on accesstoken success
     */
    void onSuccess();

    /**
     * on accesstoken faliure
     */
    void onFailure();


    /**
     *
     * @param message message
     * @param title title
     * @param isRootedAccess isRooted Accesss
     */
    void showSystemFailureDialog(final String message,final String title,boolean isRootedAccess);
}
