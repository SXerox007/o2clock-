package com.blackdreams.sumitthakur.o2clock.ui.onboarding.forgotpassword;

import com.blackdreams.sumitthakur.o2clock.base.BaseInteractor;

/**
 * Developer: sumitthakur
 * Date: 09/07/19
 */

public interface ForgotPasswordInteractor extends BaseInteractor {

    /**
     *
     * @param emailOrPhone email or phone
     * @param isEmail bool
     */
    void onContinueBtnPressed(final String emailOrPhone,final boolean isEmail);
}
