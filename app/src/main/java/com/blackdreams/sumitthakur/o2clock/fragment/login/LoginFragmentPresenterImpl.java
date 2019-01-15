package com.blackdreams.sumitthakur.o2clock.fragment.login;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;

import com.blackdreams.sumitthakur.o2clock.R;
import com.blackdreams.sumitthakur.o2clock.util.ValidateEditText;

import static com.blackdreams.sumitthakur.o2clock.Constants.AppConstants.REGEX_NUMERIC_STRING;
import static com.blackdreams.sumitthakur.o2clock.MyApplication.getAppContext;

/**
 * Created by sumitthakur on 01/01/19.
 */

public class LoginFragmentPresenterImpl implements LoginFragmentPresenter {

    private LoginFragmentView loginFragmentView;
    private LoginFragmentInteractor loginFragmentInteractor;

     LoginFragmentPresenterImpl(LoginFragmentView loginFragmentView) {
        this.loginFragmentView = loginFragmentView;
        loginFragmentInteractor = new LoginFragmentInteractorImpl(loginFragmentView);
    }

    @Override
    public void login(final AppCompatAutoCompleteTextView email, final AppCompatEditText etPassword) {
        if (checkEmailNumber(email)
                && ValidateEditText.checkPassword(etPassword, false)) {
            loginFragmentInteractor.onLoginClick(email.getText().toString().trim(),etPassword.getText().toString().trim());

        }
//        else{
//            loginFragmentView.showErrorDialog(getAppContext().getString(R.string.error_invalid_email),getAppContext().getString(R.string.err_email_phone_incorrect));
//        }
    }

    @Override
    public void showHidePassword(final AppCompatImageView ivPasswordShowHide, final AppCompatEditText etPassword) {
        if (ivPasswordShowHide.getDrawable().getConstantState()
                == (ContextCompat.getDrawable(getAppContext(), R.drawable.ic_action_visible_off).getConstantState())) {
            // show password
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            ivPasswordShowHide.setImageResource(R.drawable.ic_action_visible);
            Log.e("Error", "active");
        } else {
            // hide password
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            ivPasswordShowHide.setImageResource(R.drawable.ic_action_visible_off);
            //
            Log.e("Error", "inactive");
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
