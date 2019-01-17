package com.blackdreams.sumitthakur.o2clock.fragment.home.drawer;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.SwitchCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blackdreams.sumitthakur.o2clock.R;
import com.blackdreams.sumitthakur.o2clock.base.BaseFragment;

/**
 * Created by sumitthakur on 31/12/18.
 */

public class DrawerMenuFragment extends BaseFragment {

    private static final int INDICATOR_INDEX = 1;
    private DrawerMenuClickListener mListener;
    private SwitchCompat switchDutyOn;
    private TextView tvNotificationCount;
    private ContentFrameLayout flNotification, flSchedule, flSettings, flTutorials,flHome;
    private ContentFrameLayout flSupport, flLogout;
    private ViewGroup lastClicked;
    /**
     *
     */
    public DrawerMenuFragment() {
        // Required empty public constructor
    }

    /**
     * @return DrawerMenuFragment instance
     */
    public static DrawerMenuFragment newInstance() {
        DrawerMenuFragment fragment = new DrawerMenuFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drawer_menu_fragment, container, false);
        init(view);
        return view;
    }

    private void init(final  View view) {
        flNotification =view.findViewById(R.id.fl_notification);
        flSchedule = view.findViewById(R.id.fl_schedule);
        flSettings =  view.findViewById(R.id.fl_settings);
        flTutorials =  view.findViewById(R.id.fl_tutorials);
        flSupport =  view.findViewById(R.id.fl_chat);
        flLogout =  view.findViewById(R.id.fl_logout);
        flHome = view.findViewById(R.id.fl_home_);
        tvNotificationCount = view.findViewById(R.id.tv_notification_counter);
        flNotification.setOnClickListener(this);
        flSchedule.setOnClickListener(this);
        flSettings.setOnClickListener(this);
        flTutorials.setOnClickListener(this);
        flSupport.setOnClickListener(this);
        flLogout.setOnClickListener(this);
        flHome.setOnClickListener(this);
    }


    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        if (context instanceof DrawerMenuClickListener) {
            mListener = (DrawerMenuClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement DrawerMenuClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(final View v) {
        mListener.menuClicked(v.getId());
        addIndicator(v);
    }

    private void addIndicator(final View viewGroup) {

        if (lastClicked != null) {
            lastClicked.removeViewAt(INDICATOR_INDEX);
        }

        if (viewGroup instanceof ViewGroup) {
            View view = new View(getActivity());
            view.setBackgroundResource(R.color.white);
            ContentFrameLayout.LayoutParams layoutParams = new ContentFrameLayout.LayoutParams(getActivity().getResources()
                    .getDimensionPixelSize(R.dimen.nav_menu_bar_width)
                    , getActivity().getResources()
                    .getDimensionPixelSize(R.dimen.nav_menu_bar_height));
            layoutParams.gravity = Gravity.CENTER_VERTICAL;
            ((ViewGroup) viewGroup).addView(view, INDICATOR_INDEX, layoutParams);
            lastClicked = (ViewGroup) viewGroup;
        }

    }

    /**
     *
     */
    public interface DrawerMenuClickListener {

        /**
         * @param id view id
         */
        void menuClicked(int id);
    }
}

