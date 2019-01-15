package com.blackdreams.sumitthakur.o2clock.fragment.login;

import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;


import com.blackdreams.sumitthakur.o2clock.base.BasePresenter;

/**
 * Created by sumitthakur on 01/01/19.
 */

public interface LoginFragmentPresenter extends BasePresenter {

    /**
     *
     * @param email email
     * @param etPassword password
     */
    void login(final AppCompatAutoCompleteTextView email, final AppCompatEditText etPassword);


    /**
     *
     * @param ivPasswordShowHide image view eye
     * @param etPassword password
     */
    void showHidePassword(final AppCompatImageView ivPasswordShowHide,final AppCompatEditText etPassword);


}
