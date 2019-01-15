package com.blackdreams.sumitthakur.o2clock.fragment.register;

/**
 * Created by sumitthakur on 30/12/18.
 */

public interface  RegisterFragmentInteractor {

    /**
     *
     * @param firstName first name
     * @param lastName last name
     * @param userName user name
     * @param phoneNumber phone number
     * @param countryCode country code
     * @param email email
     * @param password password
     * @param lat lat
     * @param lan lan
     * @param address address
     */
    void onRegisterClicked(final String firstName,final String lastName,final String userName,
                           final String phoneNumber,final String countryCode,
                           final String email,final String password,
                           final double lat,final double lan, final String address);
}
