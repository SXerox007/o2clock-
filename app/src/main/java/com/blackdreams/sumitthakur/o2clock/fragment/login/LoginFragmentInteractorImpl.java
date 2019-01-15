package com.blackdreams.sumitthakur.o2clock.fragment.login;


import com.blackdreams.sumitthakur.o2clock.R;
import com.blackdreams.sumitthakur.o2clock.db.CommonData;


import loginpb.Login;
import loginpb.LoginServiceGrpc;

import static com.blackdreams.sumitthakur.o2clock.MyApplication.getAppContext;
import static com.blackdreams.sumitthakur.o2clock.MyApplication.getChannel;

/**
 * Created by sumitthakur on 01/01/19.
 */

public class LoginFragmentInteractorImpl implements LoginFragmentInteractor {
    private LoginFragmentView loginFragmentView;

     LoginFragmentInteractorImpl(final LoginFragmentView loginFragmentView) {
         this.loginFragmentView=loginFragmentView;
    }


    @Override
    public void onLoginClick(final String email, final String password) {

        LoginServiceGrpc.LoginServiceBlockingStub loginServiceBlockingStub =  LoginServiceGrpc.newBlockingStub(getChannel());
        Login.LoginRequest req =  Login.LoginRequest.newBuilder()
                .setUsernameEmail(email)
                .setPassword(password)
                .build();
        try {
            Login.LoginResponse res = loginServiceBlockingStub.loginUserService(req);
            if(res.getCode()==200) {
                CommonData.saveAccessToken(res.getAccessToken());
                loginFragmentView.onLoginSuccess();
            }else{
                loginFragmentView.onLoginError(res.getMessage(),getAppContext().getString(R.string.error_title));
            }
        }catch (Exception e){
            loginFragmentView.onLoginError(e.getMessage(),getAppContext().getString(R.string.error_title));
        }
    }
}
