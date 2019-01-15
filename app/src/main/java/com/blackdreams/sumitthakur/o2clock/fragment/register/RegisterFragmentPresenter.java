package com.blackdreams.sumitthakur.o2clock.fragment.register;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.widget.EditText;

/**
 * Created by sumitthakur on 30/12/18.
 */

public interface RegisterFragmentPresenter {

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
    void register(final EditText firstName, final EditText lastName, final EditText userName,
                  final EditText phoneNumber, final String countryCode,
                  final EditText email, final EditText password,
                  final double lat, final double lan, final String address);


    /**
     *
     * @param ivPasswordShowHide image view icon
     * @param etPassword et password
     */
    void hideShowPassword(final AppCompatImageView ivPasswordShowHide, final AppCompatEditText etPassword);
}
