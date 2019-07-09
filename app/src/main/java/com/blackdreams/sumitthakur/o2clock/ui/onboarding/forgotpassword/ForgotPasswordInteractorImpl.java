package com.blackdreams.sumitthakur.o2clock.ui.onboarding.forgotpassword;

import com.blackdreams.sumitthakur.o2clock.R;

import forgotpasswordpb.ForgotPasswordServiceGrpc;
import forgotpasswordpb.Forgotpassword;

import static com.blackdreams.sumitthakur.o2clock.MyApplication.getAppContext;
import static com.blackdreams.sumitthakur.o2clock.MyApplication.getChannel;

/**
 * Developer: sumitthakur
 * Date: 09/07/19
 */

public class ForgotPasswordInteractorImpl implements ForgotPasswordInteractor {

    private ForgotPasswordView forgotPasswordView;

     ForgotPasswordInteractorImpl(final ForgotPasswordView forgotPasswordView) {
         this.forgotPasswordView = forgotPasswordView;
    }

    @Override
    public void onContinueBtnPressed(final String emailOrPhone,final boolean isEmail) {
        ForgotPasswordServiceGrpc.ForgotPasswordServiceBlockingStub
                forgotPasswordServiceBlockingStub = ForgotPasswordServiceGrpc.newBlockingStub(getChannel());
        Forgotpassword.ForgotPasswordRequest req;
        if(isEmail) {
             req = Forgotpassword.ForgotPasswordRequest.newBuilder()
                    .setEmail(emailOrPhone)
                    .build();
        }else{
             req = Forgotpassword.ForgotPasswordRequest.newBuilder()
                    .setPhone(emailOrPhone)
                    .build();
        }

        try {
            Forgotpassword.ForgotPasswordResponse res = forgotPasswordServiceBlockingStub.forgotPassowrdUserService(req);
            if (res.getCode() == 200) {
                forgotPasswordView.onSuccess();
            } else {
                forgotPasswordView.onFailure(getAppContext().getString(R.string.error_title), res.getMessage());
            }
        }catch (Exception e){
            forgotPasswordView.onFailure(getAppContext().getString(R.string.error_title),e.getMessage());
        }
    }
}
