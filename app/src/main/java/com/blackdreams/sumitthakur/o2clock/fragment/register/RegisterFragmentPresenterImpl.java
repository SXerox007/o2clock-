package com.blackdreams.sumitthakur.o2clock.fragment.register;

import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.widget.EditText;

import com.blackdreams.sumitthakur.o2clock.R;
import com.blackdreams.sumitthakur.o2clock.util.Util;
import com.blackdreams.sumitthakur.o2clock.util.ValidateEditText;

import static com.blackdreams.sumitthakur.o2clock.MyApplication.getAppContext;


/**
 * Created by sumitthakur on 30/12/18.
 */

public class RegisterFragmentPresenterImpl implements RegisterFragmentPresenter {
    private RegisterFragmentView mRegisterFragmentView;
    private RegisterFragmentInteractor  registerFragmentInteractor;

    RegisterFragmentPresenterImpl(@NonNull final RegisterFragmentView registerFragmentView){
        mRegisterFragmentView = registerFragmentView;
        registerFragmentInteractor =  new RegisterFragmentInteractorImpl(mRegisterFragmentView);
    }



    /**
     * @param phone phone
     * @return boolean value
     */
    private boolean isValidMobile(final String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    @Override
    public void register(EditText firstName, EditText lastName, EditText userName, EditText phoneNumber,
                         String countryCode, EditText email, EditText password, double lat, double lan, String address) {
        if (ValidateEditText.checkName(firstName, true)
                && ValidateEditText.checkName(firstName, false)
                && ValidateEditText.checkEmail(email)
                && ValidateEditText.checkPassword(password, false)
                && ValidateEditText.genericEmpty(userName, Util.getStringRes(R.string.error_name_field_empty))) {
            if (isValidMobile(phoneNumber.getText().toString().trim())) {
                //if (isLocationSet) {
                // signup();
                // } else {
                //   new AlertDialog.Builder(getActivity()).setMessage(R.string.error_location_not_selected).show();
                registerFragmentInteractor.onRegisterClicked(firstName.getText().toString().trim(),
                        lastName.getText().toString().trim(),
                        userName.getText().toString().trim(),phoneNumber.getText().toString().trim(),
                        countryCode,email.getText().toString().trim(),password.getText().toString().trim(),
                        lat,lan,address);

                //}


            } else {
                mRegisterFragmentView.showAlertBox(getAppContext().getString(R.string.error_invalid_phone_number),getAppContext().getString(R.string.error_title));
            }
        }
    }

    @Override
    public void hideShowPassword(AppCompatImageView ivPasswordShowHide, AppCompatEditText etPassword) {
        if (ivPasswordShowHide.getDrawable().getConstantState()
                == (ContextCompat.getDrawable(getAppContext(), R.drawable.ic_action_visible_off).getConstantState())) {
            // show password
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            ivPasswordShowHide.setImageResource(R.drawable.ic_action_visible);
            Log.e("Error", "active");
        } else {
            // hide password
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            ivPasswordShowHide.setImageResource(R.drawable.ic_action_visible_off);
            //
            Log.e("Error", "inactive");
        }
    }
}
