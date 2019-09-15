package com.blackdreams.sumitthakur.o2clock.ui.splash;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

import com.blackdreams.sumitthakur.o2clock.R;
import com.blackdreams.sumitthakur.o2clock.base.BaseActivity;
import com.blackdreams.sumitthakur.o2clock.ui.home.HomeActivity;
import com.blackdreams.sumitthakur.o2clock.ui.onboarding.tutorial.TutorialActivity;
import com.blackdreams.sumitthakur.o2clock.util.Util;
import com.blackdreams.sumitthakur.o2clock.util.dialog.CustomAlertDialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class SplashActivity extends BaseActivity implements SplashView {

    private static final int DELAY = 500;
    private Dialog mDialog;
    private SplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSplashPresenter = new SplashPresenterImpl(this);
        init();
    }

    /**
     * for initilization
     */
    private void init() {
        if (!Util.isNetworkAvailable(SplashActivity.this)) {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }
            mDialog = new CustomAlertDialog.Builder(SplashActivity.this)
                    .setMessage(R.string.error_internet_not_connected)
                    .setPositiveButton(R.string.text_retry, new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                        @Override
                        public void onClick() {
                            init();
                        }
                    })
                    .setNegativeButton(getString(R.string.text_exit), new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                        @Override
                        public void onClick() {
                            finish();
                        }
                    })
                    .show();
            return;
        }
        if (!checkPlayServices()) {
            return;
        }
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //open activity
                goToStartActivity();
            }
        }, DELAY);
    }


    /**
     * start a fresh activity
     */
    private void goToStartActivity() {
        mSplashPresenter.systemCheck(false);
    }


    /**
     * @return true if google play service present & updated false if not presented or not updated
     */
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                int REQ_CODE_PLAY_SERVICES_RESOLUTION = 16061;
                apiAvailability.getErrorDialog(this, resultCode, REQ_CODE_PLAY_SERVICES_RESOLUTION)
                        .show();
            } else {
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                }
                mDialog = new CustomAlertDialog.Builder(SplashActivity.this)
                        .setMessage(R.string.error_device_not_supported)
                        .setPositiveButton(R.string.text_ok, new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                            @Override
                            public void onClick() {
                                finish();
                            }
                        })
                        .show();
            }
            return false;
        }
        return true;
    }

    @Override
    public void onSuccess() {
        Util.startFreshActivity(this,HomeActivity.class,null);
    }

    @Override
    public void onFailure() {
        Util.startFreshActivity(this, TutorialActivity.class, null);
    }

    @Override
    public void showSystemFailureDialog(final String message, final String title, final boolean isRootedAccess) {
        mDialog = new CustomAlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.text_ok),
                        new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                            @Override
                            public void onClick() {
                                mDialog.dismiss();
                                mSplashPresenter.systemCheck(isRootedAccess);
                            }
                        }).show();
    }
}
