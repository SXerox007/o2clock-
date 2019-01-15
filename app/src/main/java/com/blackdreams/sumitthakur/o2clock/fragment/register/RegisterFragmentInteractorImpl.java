package com.blackdreams.sumitthakur.o2clock.fragment.register;


import com.blackdreams.sumitthakur.o2clock.R;
import com.blackdreams.sumitthakur.o2clock.db.CommonData;


import regsiterpb.Register;
import regsiterpb.RegisterServiceGrpc;

import static com.blackdreams.sumitthakur.o2clock.MyApplication.getAppContext;
import static com.blackdreams.sumitthakur.o2clock.MyApplication.getChannel;

/**
 * Created by sumitthakur on 30/12/18.
 */

public class RegisterFragmentInteractorImpl implements RegisterFragmentInteractor {
    private RegisterFragmentView mRegisterFragmentView;

    public RegisterFragmentInteractorImpl(RegisterFragmentView mRegisterFragmentView) {
        this.mRegisterFragmentView = mRegisterFragmentView;
    }

    @Override
    public void onRegisterClicked(String firstName, String lastName, String userName,
                                  String phoneNumber, String countryCode,
                                  String email, String password, double lat,
                                  double lan, String address) {

        RegisterServiceGrpc.RegisterServiceBlockingStub registerClient = RegisterServiceGrpc.newBlockingStub(getChannel());
        Register.Location location = Register.Location.newBuilder()
                .setAddress(address)
                .setLan(lan)
                .setLat(lat)
                .build();
        Register.RegisterUserRequest req = Register.RegisterUserRequest.newBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserName(userName)
                .setPhone(phoneNumber)
                .setCountryCode(countryCode)
                .setEmail(email)
                .setPassword(password)
                .setLocation(location)
                .build();

        try {
            Register.RegisterUserResponse res = registerClient.registerUserService(req);
            CommonData.saveAccessToken(res.getAccessToken());
            if(res.getCode()==200) {
                mRegisterFragmentView.registerSuccess();
            }else{
                mRegisterFragmentView.showAlertBox(res.getMessage(),getAppContext().getString(R.string.error_title));
            }
        }catch (Exception e){
            mRegisterFragmentView.showAlertBox(e.getMessage(),getAppContext().getString(R.string.error_title));
        }
    }
}
