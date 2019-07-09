package com.blackdreams.sumitthakur.o2clock.ui.onboarding.forgotpassword;

import android.support.v7.widget.AppCompatAutoCompleteTextView;

import com.blackdreams.sumitthakur.o2clock.base.BasePresenter;

/**
 * Developer: sumitthakur
 * Date: 09/07/19
 */

public interface ForgotPasswordPresenter extends BasePresenter {

    /**
     *
     * @param emailOrPhone email or phone
     */
    void onContinue(final AppCompatAutoCompleteTextView emailOrPhone);
}
