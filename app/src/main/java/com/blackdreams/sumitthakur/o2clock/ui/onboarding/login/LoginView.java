package com.blackdreams.sumitthakur.o2clock.ui.onboarding.login;

/**
 * Created by sumitthakur on 26/07/18.
 */

public interface LoginView {

    void errMessageEmailPhoneIncorrect();

    void errorPassword();

    void onLoginSucess();

    void errorEmptyEmailPhone();

    void errorEmptyPassword();
}
