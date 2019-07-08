package com.blackdreams.sumitthakur.o2clock.ui.onboarding.forgotpassword;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.blackdreams.sumitthakur.o2clock.R;
import com.blackdreams.sumitthakur.o2clock.base.BaseActivity;
import com.blackdreams.sumitthakur.o2clock.util.Util;

public class ForgotPasswordActivity extends BaseActivity implements View.OnClickListener {

    private AppCompatEditText etEmailOrPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
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
                break;
            default:
        }
    }
}
