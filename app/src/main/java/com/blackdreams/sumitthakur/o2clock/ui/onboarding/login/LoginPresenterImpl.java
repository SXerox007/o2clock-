package com.blackdreams.sumitthakur.o2clock.ui.onboarding.login;


import com.blackdreams.sumitthakur.o2clock.util.ValidationUtil;

/**
 * Created by sumitthakur on 26/07/18.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;

    LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
    }


    @Override
    public void onLoginClick(String emailPhone, String password) {

        if (emailPhone.isEmpty()) {
            loginView.errorEmptyEmailPhone();
            return;
        } else if (password.isEmpty()) {
            loginView.errorEmptyPassword();
            return;
        }

        if (ValidationUtil.checkEmail(emailPhone)) {
            //do nothing
        } else if (!ValidationUtil.checkPhoneNumber(emailPhone)) {
            loginView.errMessageEmailPhoneIncorrect();
            return;
        }

        if (!ValidationUtil.checkPassword(password)) {
            loginView.errorPassword();
            return;
        }

        loginView.onLoginSucess();
    }

}
