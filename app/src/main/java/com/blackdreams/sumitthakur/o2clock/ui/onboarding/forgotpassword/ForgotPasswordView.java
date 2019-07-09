package com.blackdreams.sumitthakur.o2clock.ui.onboarding.forgotpassword;

import com.blackdreams.sumitthakur.o2clock.base.BaseView;

/**
 * Developer: sumitthakur
 * Date: 09/07/19
 */

public interface ForgotPasswordView extends BaseView {

    /**
     * on success
     */
    void onSuccess();

    /**
     *
     * @param message message
     */
    void onFailure(String title,String message);
}
