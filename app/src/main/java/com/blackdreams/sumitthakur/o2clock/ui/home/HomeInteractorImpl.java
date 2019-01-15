package com.blackdreams.sumitthakur.o2clock.ui.home;

import com.blackdreams.sumitthakur.o2clock.R;

import logoutpb.Logout;
import logoutpb.LogoutServiceGrpc;

import static com.blackdreams.sumitthakur.o2clock.MyApplication.getAppContext;
import static com.blackdreams.sumitthakur.o2clock.MyApplication.getChannel;

/**
 * Created by sumitthakur on 01/01/19.
 */

public class HomeInteractorImpl implements HomeInteractor {
    private HomeView homeView;

     HomeInteractorImpl(final HomeView homeView) {
        this.homeView = homeView;
    }

    @Override
    public void onLogoutClick(final String accessToken) {
        LogoutServiceGrpc.LogoutServiceBlockingStub logoutService = LogoutServiceGrpc.newBlockingStub(getChannel());
        Logout.LogoutRequest req = Logout.LogoutRequest.newBuilder()
                .setAccessToken(accessToken).build();
        try {
            Logout.LogoutResponse resp = logoutService.logoutUserService(req);
            if(resp.getCode()==200){
                homeView.onLogoutSuccess();
            }else{
                homeView.onLogoutFailure(resp.getMessage(),getAppContext().getString(R.string.error_title));
            }
        }catch (Exception e){
            homeView.onLogoutFailure(e.getMessage(),getAppContext().getString(R.string.error_title));
        }

    }
}
