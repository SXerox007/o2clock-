package com.blackdreams.sumitthakur.o2clock.ui.splash;

import com.blackdreams.sumitthakur.o2clock.R;
import com.blackdreams.sumitthakur.o2clock.db.CommonData;
import com.blackdreams.sumitthakur.o2clock.util.RootUtil;
import static com.blackdreams.sumitthakur.o2clock.MyApplication.getAppContext;

/**
 * Created by sumitthakur on 31/12/18.
 */

public class SplashPresenterImpl implements SplashPresenter{
    private SplashView splashView;
    private SplashInteractor splashInteractor;

    SplashPresenterImpl(SplashView splashView) {
        this.splashView=splashView;
        splashInteractor = new SplashInteractorImpl(this.splashView);
    }

    @Override
    public void systemCheck(boolean isAccessRooted) {
        if(!isAccessRooted){
        if (RootUtil.isDeviceRooted()) {
            splashView.showSystemFailureDialog(getAppContext().getString(R.string.msg_device_rooted)
                    ,getAppContext().getString(R.string.msg_warning),true);
        }else
            accessTokenVerify();

        }else {
            accessTokenVerify();
        }
    }

    private void accessTokenVerify(){
        splashInteractor.checkAccessToken(CommonData.getAccessToken());
    }
}
