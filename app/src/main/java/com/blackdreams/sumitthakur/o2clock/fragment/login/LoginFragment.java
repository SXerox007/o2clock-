package com.blackdreams.sumitthakur.o2clock.fragment.login;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blackdreams.sumitthakur.o2clock.R;
import com.blackdreams.sumitthakur.o2clock.base.BaseFragment;
import com.blackdreams.sumitthakur.o2clock.ui.home.HomeActivity;
import com.blackdreams.sumitthakur.o2clock.ui.onboarding.login.LoginPresenter;
import com.blackdreams.sumitthakur.o2clock.util.Util;
import com.blackdreams.sumitthakur.o2clock.util.ValidateEditText;
import com.blackdreams.sumitthakur.o2clock.util.dialog.CustomAlertDialog;

import io.paperdb.Paper;

import static com.blackdreams.sumitthakur.o2clock.Constants.PaperDbConstants.LOGIN_CREDENTIALS;

/**
 * Created by sumitthakur on 25/12/18.
 */

public class LoginFragment extends BaseFragment implements LoginFragmentView {

    private static final String USER_ID = "id";
    private static final String USER_PASS = "pass";
    private TextView tvForgotPassword;
    private AppCompatButton btnLogin;
    private AppCompatCheckBox checkBox;
    private AppCompatEditText etPassword;
    private RelativeLayout relativeLayout;
    private AppCompatAutoCompleteTextView acEmail;
    private Context context;
    private AppCompatImageView ivPasswordShowHide;
    private LoginFragmentPresenter loginFragmentPresenter;
    private Dialog mDialog;

    /**
     * Creating Instance of Fragment
     *
     * @return Instance of Fragment
     */
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        context = getContext();
        loginFragmentPresenter = new LoginFragmentPresenterImpl(this);
        init(view);
        return view;
    }

    /**
     * initialization
     *
     * @param view
     */
    private void init(final View view) {
        ivPasswordShowHide = view.findViewById(R.id.iv_eye_active_inactive);
        relativeLayout = view.findViewById(R.id.rl_eye_visible);
        tvForgotPassword =  view.findViewById(R.id.tvForgot_password);
        btnLogin = view.findViewById(R.id.btnLogin);
        acEmail =  view.findViewById(R.id.etEmail);
        etPassword =view.findViewById(R.id.etPassword);
        checkBox =  view.findViewById(R.id.cb_rememberme);
        attachSuggestionToEmail(acEmail);
        Util.setOnClickListener(this,btnLogin,tvForgotPassword,relativeLayout);
    }


    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                loginFragmentPresenter.login(acEmail,etPassword);
                break;
            case R.id.tvForgot_password:
                forgotPassword();
                break;
            case R.id.rl_eye_visible:
                loginFragmentPresenter.showHidePassword(ivPasswordShowHide,etPassword);
                break;
            default:
                break;
        }
    }

    private void attachSuggestionToEmail(final AppCompatAutoCompleteTextView textView) {
        final String[] id = {Paper.book(LOGIN_CREDENTIALS).read(USER_ID, "")};
        if (id.length > 0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, id);
            textView.setAdapter(adapter);
            textView.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
                @Override
                public void onDismiss() {
                    etPassword.setText(Paper.book(LOGIN_CREDENTIALS).read(USER_PASS, ""));
                    etPassword.invalidate();
                }
            });
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * method to save user credential when check Remember_Me
     */
    private void saveLoginInfo() {
        String email = acEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        Paper.book(LOGIN_CREDENTIALS).write(USER_ID, email);
        Paper.book(LOGIN_CREDENTIALS).write(USER_PASS, password);
    }

    /**
     * forgot password
     */
   // TODO
    private void forgotPassword() {
       // startActivity(new Intent(getActivity(), ForgotPasswordActivity.class));
    }



    @Override
    public void showToast(String text) {

    }

    @Override
    public void showSnackBar(String text, View view) {

    }

    @Override
    public void onLoginSuccess() {
        if (checkBox.isChecked()) {
            saveLoginInfo();
        }
        Util.startFreshActivity((Activity) getContext(),HomeActivity.class,null);
    }

    @Override
    public void onLoginError(final String message, final String title) {
        showErrorDialog(message,title);
    }


    @Override
    public void showErrorDialog(String msg, String title) {
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
}
