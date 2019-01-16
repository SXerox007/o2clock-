package com.blackdreams.sumitthakur.o2clock.fragment.home.drawer.chat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackdreams.sumitthakur.o2clock.R;
import com.blackdreams.sumitthakur.o2clock.base.BaseFragment;

/**
 * Created by sumitthakur on 10/01/19.
 */

public class ChatFragment extends BaseFragment implements View.OnClickListener {

    private View view;

    /**
     * Creating Instance of Fragment
     *
     * @return Instance of Fragment
     */
    public static Fragment newInstance() {
        return new ChatFragment();
    }



    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chat_fragment, container, false);
        init(view);
        return view;
    }


    /**
     *  initilize
     * @param view view
     */
    private void init(View view) {

    }
}
