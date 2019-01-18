package com.blackdreams.sumitthakur.o2clock.fragment.home.drawer.chat.allusers;

import com.blackdreams.sumitthakur.o2clock.db.CommonData;

import chatpb.Chat;

/**
 * Developer: sumitthakur
 * Date: 16/01/19
 */

public class AllUsersPresenterImpl implements AllUsersPresenter {

    private AllUsersView view;
    private AllUsersInteractor interactor;


    public AllUsersPresenterImpl(final AllUsersView view) {
        this.view = view;
        interactor = new AllUsersInteractorImpl(view);
    }


    @Override
    public void getAllUsersAndGroups() {
        interactor.allUsersAndGroups(CommonData.getAccessToken());
    }

    @Override
    public void getLoginUserData() {
        interactor.getLogedInData(CommonData.getAccessToken());

    }

    @Override
    public void startChat(final Chat.User sender, final Chat.User reciever) {
        interactor.setupP2PChat(sender,reciever);
    }
}
