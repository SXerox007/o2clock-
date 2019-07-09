package com.blackdreams.sumitthakur.o2clock.ui.onboarding.forgotpassword;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.blackdreams.sumitthakur.o2clock.R;
import com.blackdreams.sumitthakur.o2clock.base.BaseActivity;
import com.blackdreams.sumitthakur.o2clock.util.Util;
import com.blackdreams.sumitthakur.o2clock.util.dialog.CustomAlertDialog;

public class ForgotPasswordActivity extends BaseActivity implements View.OnClickListener,ForgotPasswordView {

    private AppCompatAutoCompleteTextView etEmailOrPhone;
    private Dialog mDialog;
    private ForgotPasswordPresenter forgotPasswordPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        forgotPasswordPresenter = new ForgotPasswordPresenterImpl(this);
        init();
    }

    /**
     * init
     */
    private void init() {
        ((AppCompatTextView)findViewById(R.id.toolbar_title)).setText(getString(R.string.forgot_password));
        findViewById(R.id.ivBack).setVisibility(View.VISIBLE);
        etEmailOrPhone = findViewById(R.id.etEmailOrPhone);
        Util.setOnClickListener(this, findViewById(R.id.ivBack), findViewById(R.id.btnContinue));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressed();
                break;
            case R.id.btnContinue:
                forgotPasswordPresenter.onContinue(etEmailOrPhone);
                break;
            default:
        }
    }

    @Override
    public void onSuccess() {
        showDialog(getString(R.string.txt_message_sent_email_or_phone),getString(R.string.txt_success));
    }

    @Override
    public void onFailure(final String title,final String message) {
        showDialog(message,title);
    }

    /**
     *
     * @param msg message
     * @param title title
     */
    private void showDialog(final String msg, final String title){
        mDialog = new CustomAlertDialog.Builder(this)
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

}
