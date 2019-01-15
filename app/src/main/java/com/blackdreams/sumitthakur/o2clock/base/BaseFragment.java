package com.blackdreams.sumitthakur.o2clock.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackdreams.sumitthakur.o2clock.Constants.ApiConstants;
import com.blackdreams.sumitthakur.o2clock.Constants.AppConstants;


public abstract class BaseFragment extends Fragment implements AppConstants, ApiConstants, View.OnClickListener {
    @Override
    public void onClick(final View v) {
    }

    /**
     *
     * @param view View Created
     * @param savedInstanceState savedInstanceState
     */
    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     *
     * @param inflater View to be inflate into the Fragment
     * @param container container of views
     * @param savedInstanceState savedInstanceState
     * @return view
     */
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
