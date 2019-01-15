package com.blackdreams.sumitthakur.o2clock.ui.splash;

import com.blackdreams.sumitthakur.o2clock.R;

import accesstokenpb.AccessTokenServiceGrpc;
import accesstokenpb.Accesstoken;

import static com.blackdreams.sumitthakur.o2clock.MyApplication.getAppContext;
import static com.blackdreams.sumitthakur.o2clock.MyApplication.getChannel;

/**
 * Created by sumitthakur on 31/12/18.
 */

public class SplashInteractorImpl implements SplashInteractor{

    private SplashView splashView;

    public SplashInteractorImpl(SplashView splashView){
        this.splashView = splashView;
    }

    @Override
    public void checkAccessToken(String accessToken) {
        AccessTokenServiceGrpc.AccessTokenServiceBlockingStub accessTokenService = AccessTokenServiceGrpc.newBlockingStub(getChannel());
        Accesstoken.AccessTokenRequest req = Accesstoken.AccessTokenRequest.newBuilder()
                .setAccessToken(accessToken).build();
        try {
            Accesstoken.AccessTokenResponse resp = accessTokenService.checkAccessTokenService(req);
            if(resp.getCode()==200){
                splashView.onSuccess();
            }else{
                splashView.onFailure();
            }
        }catch (Exception e){
            splashView.onFailure();
        }


    }
}
