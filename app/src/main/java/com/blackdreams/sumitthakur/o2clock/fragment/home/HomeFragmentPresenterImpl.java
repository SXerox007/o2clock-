package com.blackdreams.sumitthakur.o2clock.fragment.home;

import com.blackdreams.sumitthakur.o2clock.R;
import com.blackdreams.sumitthakur.o2clock.db.CommonData;

import java.io.File;

import static com.blackdreams.sumitthakur.o2clock.MyApplication.getAppContext;

/**
 * Created by sumitthakur on 03/01/19.
 */

public class HomeFragmentPresenterImpl implements HomeFragmentPresenter {
    private HomeFragmentView homeFragmentView;
    private HomeFragmentInteractor homeFragmentInteractor;

     HomeFragmentPresenterImpl(HomeFragmentView homeFragmentView) {
         this.homeFragmentView = homeFragmentView;
         homeFragmentInteractor = new HomeFragmentInteractorImpl(homeFragmentView);
     }

    @Override
    public void verifyUser(final File file) {
        if (file.exists()) {
            homeFragmentInteractor.onCameraTap(file, CommonData.getAccessToken());
        }else{
            homeFragmentView.onVerifyUserError(getAppContext().getString(R.string.error_file_not_exist),getAppContext().getString(R.string.error_title));
        }
    }
}
