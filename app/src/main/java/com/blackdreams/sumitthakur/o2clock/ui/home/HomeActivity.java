package com.blackdreams.sumitthakur.o2clock.ui.home;


import android.app.Dialog;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blackdreams.sumitthakur.o2clock.R;
import com.blackdreams.sumitthakur.o2clock.base.BaseActivity;
import com.blackdreams.sumitthakur.o2clock.fragment.home.HomeFragment;
import com.blackdreams.sumitthakur.o2clock.fragment.home.drawer.DrawerMenuFragment;


import com.blackdreams.sumitthakur.o2clock.fragment.home.drawer.chat.chathome.ChatHomeFragment;
import com.blackdreams.sumitthakur.o2clock.ui.onboarding.tutorial.TutorialActivity;
import com.blackdreams.sumitthakur.o2clock.util.Util;
import com.blackdreams.sumitthakur.o2clock.util.dialog.CustomAlertDialog;
import com.google.android.gms.common.api.GoogleApiClient;




public class HomeActivity extends BaseActivity implements
        DrawerMenuFragment.DrawerMenuClickListener,HomeView {

    private DrawerLayout drawer;
    private TextView tvTitle;
    private FrameLayout flMain;
    private TabLayout tabLayoutHome;
    private GoogleApiClient mGoogleApiClient;
    private HomePresenter homePresenter;
    private Dialog mDialog;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homePresenter = new HomePresenterImpl(this);
        init();
    }

    /**
     * initialization
     */
    private void init() {
        tvTitle = findViewById(R.id.toolbar_title);
        tabLayoutHome = findViewById(R.id.home_tablayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setToolBar(toolbar);
        tvTitle.setText(R.string.tab_home);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(final View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(final View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        replaceFragment(HomeFragment.newInstance(), R.id.fl_home);
        // replaceFragment(HomeFragment.newInstance(" ", " "));
//        if (Paper.book().read(SOCIAL_LOGIN_BY, " ").equals(GOOGLE)) {
//            setGoogleApiClient();
//        }

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    /**
     * logout the user
     */
    private void logout() {
        homePresenter.logout();
    }


    private void setGoogleApiClient() {
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(KEY_REQUEST_TOKEN_GOOGLE)
//                .requestEmail()
//                .build();
//        mGoogleApiClient = new GoogleApiClient.Builder(MyApplication.getAppContext())
//                .addOnConnectionFailedListener(this)
//                .enableAutoManage(this, this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
    }

    @Override
    public void menuClicked(final int id) {

        switch (id) {
            case R.id.fl_home_:
                replaceFragment(HomeFragment.newInstance(), R.id.fl_home);
                break;
            case R.id.fl_notification:
                break;
            case R.id.fl_schedule:
                break;
            case R.id.fl_settings:
                break;
            case R.id.fl_tutorials:
                break;
            case R.id.fl_chat:
                replaceFragment(ChatHomeFragment.newInstance(), R.id.fl_home);
                break;
            case R.id.fl_logout:
                logout();
                break;
            default:
                break;
        }
        drawer.closeDrawer(Gravity.START);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onLogoutSuccess() {
        Util.startFreshActivity(this,TutorialActivity.class,null);
    }

    @Override
    public void onLogoutFailure(String msg, String title) {
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


    /**
     * @param fragment   fragment
     * @param replaceTry replace
     */
    private void replaceFragment(final Fragment fragment, final int replaceTry) {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(replaceTry, fragment);
        fragmentTransaction.commit();
    }
}
