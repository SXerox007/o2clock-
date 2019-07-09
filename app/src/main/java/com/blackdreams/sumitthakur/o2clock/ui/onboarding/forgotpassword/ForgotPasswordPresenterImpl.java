package com.blackdreams.sumitthakur.o2clock.ui.onboarding.forgotpassword;

import android.support.v7.widget.AppCompatAutoCompleteTextView;

import com.blackdreams.sumitthakur.o2clock.util.ValidateEditText;

import static com.blackdreams.sumitthakur.o2clock.Constants.AppConstants.REGEX_NUMERIC_STRING;

/**
 * Developer: sumitthakur
 * Date: 09/07/19
 */

public class ForgotPasswordPresenterImpl implements ForgotPasswordPresenter {

    private ForgotPasswordView forgotPasswordView;
    private ForgotPasswordInteractor forgotPasswordInteractor;

    ForgotPasswordPresenterImpl(ForgotPasswordView forgotPasswordView) {
        this.forgotPasswordView = forgotPasswordView;
        forgotPasswordInteractor = new ForgotPasswordInteractorImpl(forgotPasswordView);
    }

    @Override
    public void onContinue(final AppCompatAutoCompleteTextView emailOrPhone) {
        if (checkEmailNumber(emailOrPhone)){
            forgotPasswordInteractor.onContinueBtnPressed(emailOrPhone.getText().toString().trim(),true);
        }

    }

    /**
     * Check weather login by email or Username
     *
     * @return boolean
     */
    private boolean checkEmailNumber(final AppCompatAutoCompleteTextView acEmail) {
        if (!acEmail.getText().toString().trim().matches(REGEX_NUMERIC_STRING)) {

            return ValidateEditText.checkEmail(acEmail);
        }
        return ValidateEditText.checkPhoneNumber(acEmail);
    }

}
