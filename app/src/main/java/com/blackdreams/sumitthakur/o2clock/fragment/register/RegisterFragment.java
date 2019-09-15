package com.blackdreams.sumitthakur.o2clock.fragment.register;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.text.InputFilter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blackdreams.sumitthakur.o2clock.R;
import com.blackdreams.sumitthakur.o2clock.base.BaseFragment;
import com.blackdreams.sumitthakur.o2clock.ui.home.HomeActivity;
import com.blackdreams.sumitthakur.o2clock.util.EditTextUtil;
import com.blackdreams.sumitthakur.o2clock.util.Util;
import com.blackdreams.sumitthakur.o2clock.util.dialog.CustomAlertDialog;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import static android.app.Activity.RESULT_OK;


/**
 * Created by sumitthakur on 26/12/18.
 */

public class RegisterFragment  extends BaseFragment implements RegisterFragmentView{

    private static final int MAX_NUMBER_LENGTH = 15;
    private static final String TAG = "SignUpFragment";
    private static final int PLACE_PICKER_REQUEST = 999;
    private AppCompatEditText etName;
    private AppCompatEditText etMobileNumber;
    private AppCompatEditText etPassword;
    private AppCompatEditText etEmail;
    private AppCompatEditText etUserName;
    private AppCompatEditText etLastName;
    private TextView tvCountryCode, tvTermsAndConditions;
    private AppCompatImageView ivPasswordShowHide;
    private RelativeLayout relativeLayout;
    private ImageView ivPlacePicker;
    private boolean isLocationSet;
    private LatLng mLocation;
    private RegisterFragmentPresenter mRegisterFragmentPresenter;
    private String address;
    private Dialog mDialog;

    /**
     * Creating Instance of Fragment
     *
     * @return Instance of Fragment
     */
    public static Fragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment, container, false);
        init(view);
        mRegisterFragmentPresenter = new RegisterFragmentPresenterImpl(this);
        return view;
    }

    private void init(final View view) {
        address = "";
        tvTermsAndConditions = view.findViewById(R.id.tv_terms_and_conditions);
        ivPasswordShowHide =  view.findViewById(R.id.iv_eye_active_in_active);
        relativeLayout =  view.findViewById(R.id.rl_eye_visible_signup);
        AppCompatEditText etPasswordConfirm = view.findViewById(R.id.etPassword2);
        etName = view.findViewById(R.id.et_name);
        ivPlacePicker =  view.findViewById(R.id.iv_add_location);
        etLastName =  view.findViewById(R.id.et_last_name);
        etUserName =  view.findViewById(R.id.et_user_name);
        etMobileNumber =  view.findViewById(R.id.et_phone_number);
        etMobileNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_NUMBER_LENGTH)});
        etEmail =  view.findViewById(R.id.etEmail);
        etPassword =  view.findViewById(R.id.etPassword);
        AppCompatButton btnContinue = view.findViewById(R.id.btn_continue);
        tvCountryCode = view.findViewById(R.id.tv_country_code);
        Util.setOnClickListener(this,ivPlacePicker,tvCountryCode, btnContinue,relativeLayout,tvTermsAndConditions);
        EditTextUtil.addPasswordPatternWatcher(etPassword);
        EditTextUtil.addTextMatchWatcher(etPasswordConfirm, etPassword);
    }


    @Override
    public void onClick(final View v) {
        removeSpace(etMobileNumber);
        switch (v.getId()) {
            case R.id.btn_continue:
               // if(isLocationSet) {
                    mRegisterFragmentPresenter.register(etName, etLastName, etUserName, etMobileNumber,
                            "+91", etEmail, etPassword, 0,0, address);
                //}else{
                  //  new AlertDialog.Builder(getActivity()).setMessage(R.string.error_location_not_selected).show();
                //}
                break;
            case R.id.tv_country_code:
                countryCodePicker(tvCountryCode);
                break;
            case R.id.rl_eye_visible_signup:
                mRegisterFragmentPresenter.hideShowPassword(ivPasswordShowHide,etPassword);
                break;
            case R.id.tv_terms_and_conditions:
                termsAndConditions();
                break;
            case R.id.iv_add_location:
                openPlacePicker();
                break;
            default:
                break;
        }
    }

    /**
     * remove all spaces in Number
     *
     * @param textView TV
     */
    private void removeSpace(final TextView textView) {
        textView.setText(textView.getText().toString().replace(" ", ""));
        textView.invalidate();
    }

    /**
     */
    private void openPlacePicker() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            Log.d(TAG, "GooglePlayServicesRepairableException exception");
        }
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PLACE_PICKER_REQUEST) {
                Place place = PlacePicker.getPlace(getContext(), data);
                address = (String) place.getAddress();
                mLocation = place.getLatLng();
                isLocationSet = true;
                ivPlacePicker.setImageResource(R.drawable.ic_done_circle);
            }
        }
    }

    /**
     * terms and Conditions
     */
    //TODO
    private void termsAndConditions() {
       // startActivity(new Intent(getActivity(), TermsConditions.class));
    }


    /**
     * country code Picker
     */
    private void countryCodePicker(final TextView textViewCompat) {
        // dialog title
//        final CountryPicker picker = CountryPicker.newInstance("Select Country");
//        picker.setListener(new CountryPickerListener() {
//            @Override
//            public void onSelectCountry(final String name,
//                                        final String code,
//                                        final String dialCode,
//                                        final int flagDrawableResID) {
//                textViewCompat.setText(dialCode);
//                picker.dismiss();
//
//            }
//        });
//        picker.show(getFragmentManager(), "COUNTRY_PICKER");
    }


    @Override
    public void showToast(String text) {

    }

    @Override
    public void showSnackBar(String text, View view) {

    }

    @Override
    public void showAlertBox(String msg,String title) {
        mDialog = new CustomAlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(false)
               .setNegativeButton(getString(R.string.text_ok),
                       new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                           @Override
                           public void onClick() {
                               mDialog.dismiss();
                           }
                       })
                .show();
    }

    @Override
    public void registerSuccess() {
        //move to Home Activity
        Util.startFreshActivity((Activity) getContext(),HomeActivity.class,null);

    }
}
