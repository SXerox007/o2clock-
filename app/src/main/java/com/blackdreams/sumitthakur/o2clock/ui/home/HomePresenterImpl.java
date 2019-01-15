package com.blackdreams.sumitthakur.o2clock.ui.home;

import com.blackdreams.sumitthakur.o2clock.db.CommonData;

/**
 * Created by sumitthakur on 01/01/19.
 */

public class HomePresenterImpl implements HomePresenter {
    private HomeView homeView;
    private HomeInteractor homeInteractor;

    HomePresenterImpl(final HomeView homeView) {
         this.homeView = homeView;
         homeInteractor = new HomeInteractorImpl(homeView);
    }

    @Override
    public void logout() {
        homeInteractor.onLogoutClick(CommonData.getAccessToken());
        CommonData.cleardb();
    }
}
