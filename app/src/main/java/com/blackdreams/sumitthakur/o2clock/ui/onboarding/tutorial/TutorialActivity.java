package com.blackdreams.sumitthakur.o2clock.ui.onboarding.tutorial;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blackdreams.sumitthakur.o2clock.Constants.AppConstants;
import com.blackdreams.sumitthakur.o2clock.Constants.Keys;
import com.blackdreams.sumitthakur.o2clock.MyApplication;
import com.blackdreams.sumitthakur.o2clock.R;
import com.blackdreams.sumitthakur.o2clock.base.BaseActivity;
import com.blackdreams.sumitthakur.o2clock.fragment.tutorial.TutorialFragment;
import com.blackdreams.sumitthakur.o2clock.ui.onboarding.login.LoginActivity;
import com.blackdreams.sumitthakur.o2clock.util.Config;
import com.blackdreams.sumitthakur.o2clock.util.Prefs;
import com.blackdreams.sumitthakur.o2clock.util.Util;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;


public class TutorialActivity extends BaseActivity implements View.OnClickListener {

    private static final long ANIM_VIEWPAGER_DELAY = 5000;
    private static final long ANIM_VIEWPAGER_DELAY_USER_VIEW = 10000;
    private Runnable animateSlider;
    private android.os.Handler handler;
    private boolean stopSliding = false;
    private AppCompatButton btnRegister, btnLogin;
    private AppCompatTextView tvServerName;
    private AlertDialog changeServerDialog;




    //private TextView tvSkip;
    private ViewPager viewPager;
    private ArrayList<Fragment> arrayListFragment = new ArrayList<>();
    private PagerAdapter myPagerAdapter;
    private CircleIndicator circleIndicator;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        animateLayout();
        init();
    }


    /**
     * On SignIn/SignUp Button Pressed
     *
     * @param view view
     */
    @Override
    public void onClick(final View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        switch (view.getId()) {
            case R.id.btn_login:
                intent.putExtra(AppConstants.KEY, AppConstants.LOG_IN);
                startActivity(intent);
                break;
            case R.id.btn_register:
                intent.putExtra(AppConstants.KEY, AppConstants.SIGN_UP);
                startActivity(intent);
                break;
            case R.id.tvServerName:
                openChangeServerDialog();
                break;
            default:
                break;
        }
    }

    /**
     * multiple instances of the same fragment;
     */
    private void initPaging() {
        //add fragment
        arrayListFragment.add(TutorialFragment.newInstance(AppConstants.TUTORIAL_BUNDLE_FIRST));
        arrayListFragment.add(TutorialFragment.newInstance(AppConstants.TUTORIAL_BUNDLE_SECOND));
        arrayListFragment.add(TutorialFragment.newInstance(AppConstants.TUTORIAL_BUNDLE_THIRD));
        setSlider();
    }


    /**
     * initialised
     */
    private void init() {
        tvServerName = findViewById(R.id.tvServerName);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        tvServerName.setOnClickListener(this);
        viewPager = findViewById(R.id.view_pager_tutorial);
        circleIndicator = findViewById(R.id.dot_indicator);
        initPaging();
    }


    /**
     * Method to set splash slider
     */
    private void setSlider() {
        myPagerAdapter = new com.blackdreams.sumitthakur.o2clock.adapter.PagerAdapter(getSupportFragmentManager(), arrayListFragment);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(final View v, final MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction()) {

                    case MotionEvent.ACTION_CANCEL:
                        break;

                    case MotionEvent.ACTION_UP:
                        // calls when touch release on ViewPager
                        if (arrayListFragment != null && arrayListFragment.size() != 0) {
                            stopSliding = false;
                            runnable(arrayListFragment.size());
                            handler.postDelayed(animateSlider, ANIM_VIEWPAGER_DELAY_USER_VIEW);
                        }
                        break;

                    case MotionEvent.ACTION_MOVE:
                        // calls when ViewPager touch
                        if (handler != null && !stopSliding) {
                            stopSliding = true;
                            handler.removeCallbacks(animateSlider);
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        //mIndicator.setOnPageChangeListener(new PageChangeListener());
        circleIndicator.setViewPager(viewPager);
    }


    /**
     * Runnable.
     *
     * @param size the size
     */
    public void runnable(final int size) {
        handler = new android.os.Handler();
        animateSlider = new Runnable() {
            public void run() {
                if (!stopSliding) {
                    if (viewPager.getCurrentItem() == size - 1) {
                        viewPager.setCurrentItem(0, true);
                    } else {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                    }
                    handler.postDelayed(animateSlider, ANIM_VIEWPAGER_DELAY);

                }
            }
        };

    }


    @Override
    public void onResume() {
        runnable(arrayListFragment.size());
        handler.postDelayed(animateSlider, ANIM_VIEWPAGER_DELAY);
        super.onResume();
    }

    @Override
    public void onPause() {
        if (handler != null) {
            handler.removeCallbacks(animateSlider);
        }
        super.onPause();
    }

    /**
     * animate Layout
     */
    private void animateLayout() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RelativeLayout relativeLayout =  findViewById(R.id.rl_animate);
                @SuppressLint("ResourceType") Animation slideUp = AnimationUtils.loadAnimation(TutorialActivity.this, R.animator.slide_up);
                relativeLayout.startAnimation(slideUp);
            }
        });
    }


    /**
     * Method to change the Server
     */
    private void openChangeServerDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        Config.AppMode[] values = Config.AppMode.values();

        int appModesCount = values.length;
        final String[] appModes = new String[appModesCount];

        int currentPosition = 0;
        String currentAppModeName = Config.getCurrentAppModeName(this);

        for (int position = 0; position < appModesCount; position++) {

            String appModeName = values[position].name();

            appModes[position] = appModeName;

            if (currentAppModeName.equals(appModeName))
                currentPosition = position;
        }

        builder.setSingleChoiceItems(appModes, currentPosition, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int position) {

                String appModeName = appModes[position];

                tvServerName.setText(appModeName);
                if (appModeName.equalsIgnoreCase("MANUAL")) {
                    changeServerDialog.dismiss();
                    openManualServerDialog();
                } else {
                    Config.setAppMode(TutorialActivity.this, appModeName);
                }
                changeServerDialog.dismiss();
            }
        });

        changeServerDialog = builder.create();
        changeServerDialog.show();
    }
    /**
     * Method for manual server
     */
    private void openManualServerDialog() {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.dialog_manual_server, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptsView);
        final AppCompatEditText etScheme = promptsView.findViewById(R.id.etScheme);
        final AppCompatEditText etHost = promptsView.findViewById(R.id.etHost);
        final AppCompatEditText etPort = promptsView.findViewById(R.id.etPort);
        final AppCompatButton btnDone = promptsView.findViewById(R.id.btnDone);
        final AppCompatButton btnCancel = promptsView.findViewById(R.id.btnCancel);
        final AppCompatTextView tvManualUrl = promptsView.findViewById(R.id.tvManualUrl);
        final AppCompatTextView tvUrlError = promptsView.findViewById(R.id.tvUrlError);
        tvUrlError.setVisibility(View.GONE);
        alertDialogBuilder.setCancelable(false);
        if (!Util.isEmpty(etScheme)) {
            tvManualUrl.setText(etScheme.getText());
        }
        final AlertDialog alertDialog = alertDialogBuilder.create();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                tvServerName.setText(Config.getCurrentAppModeName(TutorialActivity.this));
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Util.isEmpty(etScheme)) {
                    Util.setErrorOn(etScheme, "");
                } else if (Util.isEmpty(etHost)) {
                    Util.setErrorOn(etHost, "");

                } else if (Util.isEmpty(etPort)) {
                    Util.setErrorOn(etPort,"");
                } else {
                    String manualUrl = etScheme.getText() + "://" + etHost.getText() + ":" + etPort.getText() + "/";
                    Prefs.with(TutorialActivity.this).save(Keys.Prefs.APP_MANUAL_MODE, manualUrl);
                    Config.setAppMode(TutorialActivity.this, Config.AppMode.MANUAL.name());
                    // for tcp connection save host and port
                    Prefs.with(TutorialActivity.this).save(Keys.Prefs.APP_TCP_CONNECTION_HOST, etHost.getText().toString());
                    Prefs.with(TutorialActivity.this).save(Keys.Prefs.APP_TCP_CONNECTION_PORT, Integer.parseInt(etPort.getText().toString()));
                    MyApplication.setChannel();
                    alertDialog.dismiss();
                }
            }
        });
        etScheme.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tvUrlError.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvManualUrl.setText(etScheme.getText() + "://" + etHost.getText() + ":" + etPort.getText() + "/");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etHost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tvUrlError.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvManualUrl.setText(etScheme.getText() + "://" + etHost.getText() + ":" + etPort.getText() + "/");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etPort.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tvUrlError.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvManualUrl.setText(etScheme.getText() + "://" + etHost.getText() + ":" + etPort.getText() + "/");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        alertDialog.show();
        Util.showSoftKeyboard(TutorialActivity.this, etHost);
    }

}
