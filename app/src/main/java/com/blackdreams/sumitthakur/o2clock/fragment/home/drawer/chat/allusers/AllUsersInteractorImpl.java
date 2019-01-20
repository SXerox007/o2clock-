package com.blackdreams.sumitthakur.o2clock.fragment.home.drawer.chat.allusers;

import com.blackdreams.sumitthakur.o2clock.R;
import com.blackdreams.sumitthakur.o2clock.base.BaseInteractorImpl;

import chatpb.Chat;
import chatpb.ChatRoomGrpc;

import static com.blackdreams.sumitthakur.o2clock.MyApplication.getAppContext;
import static com.blackdreams.sumitthakur.o2clock.MyApplication.getChannel;

/**
 * Developer: sumitthakur
 * Date: 16/01/19
 */

public class AllUsersInteractorImpl extends BaseInteractorImpl implements AllUsersInteractor {

    private AllUsersView view;
     AllUsersInteractorImpl(AllUsersView view) {
        this.view = view;
    }

    @Override
    public void allUsersAndGroups(String accessToken) {
        ChatRoomGrpc.ChatRoomBlockingStub chatRoomBlockingStub = ChatRoomGrpc.newBlockingStub(getChannel());
        Chat.CommonRequest req = Chat.CommonRequest
                .newBuilder()
                .setAccessToken(accessToken)
                .build();
        try {
            Chat.UserList res = chatRoomBlockingStub.getUsersList(req);
            if(res.getCommonResponse().getCode()==200){
                view.onSuccess(res.getUsersList());
            }else{
                view.onFailure(res.getCommonResponse().getMessage(),getAppContext().getString(R.string.error_title));
            }
        }catch (Exception e){
            view.onFailure(e.getMessage(),getAppContext().getString(R.string.error_title));
        }

    }

    @Override
    public void getLogedInData(final String accessToken) {
        ChatRoomGrpc.ChatRoomBlockingStub chatRoomBlockingStub = ChatRoomGrpc.newBlockingStub(getChannel());
        Chat.CommonRequest req = Chat.CommonRequest
                .newBuilder()
                .setAccessToken(accessToken)
                .build();
        try {
            Chat.User res = chatRoomBlockingStub.getUserDetails(req);
            view.onLoginUserDataSuccess(res);
        }catch (Exception e){
            view.onFailure(e.getMessage(),getAppContext().getString(R.string.error_title));
        }
    }

    @Override
    public void setupP2PChat(final Chat.User sender, final Chat.User reciever) {
        ChatRoomGrpc.ChatRoomBlockingStub chatRoomBlockingStub = ChatRoomGrpc.newBlockingStub(getChannel());
        Chat.P2PChatRequest req = Chat.P2PChatRequest
                .newBuilder()
                .setUserInfo(sender)
                .setReciverInfo(reciever)
                .build();

        try {
            Chat.P2PChatResponse res = chatRoomBlockingStub.startP2PChat(req);
            if(res.getCommmonResponse().getCode()==200){
                view.onP2PChatConnectionStartSuccess(res.getChatId());
            }else{
                view.onFailure(res.getCommmonResponse().getMessage(),getAppContext().getString(R.string.error_title));
            }
        }catch (Exception e){
            view.onFailure(e.getMessage(),getAppContext().getString(R.string.error_title));
        }

    }
}
