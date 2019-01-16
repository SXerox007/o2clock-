package com.blackdreams.sumitthakur.o2clock.fragment.home.drawer.chat.chathome;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackdreams.sumitthakur.o2clock.R;
import com.blackdreams.sumitthakur.o2clock.adapter.ViewPagerAdapter;
import com.blackdreams.sumitthakur.o2clock.base.BaseFragment;
import com.blackdreams.sumitthakur.o2clock.fragment.home.drawer.chat.allchats.AllChatsFragment;

/**
 * Developer: sumitthakur
 * Date: 16/01/19
 */

public class ChatHomeFragment extends BaseFragment implements View.OnClickListener {

    private static ViewPagerAdapter adapter;
    private View view;
    private ViewPager viewpagerAppointments;
    private TabLayout tabLayoutAppointments;



    /**
     * Creating Instance of Fragment
     *
     * @return Instance of Fragment
     */
    public static Fragment newInstance() {
        return new ChatHomeFragment();
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chat_home, container, false);
        init(view);
        return view;
    }


    /**
     *  initilize
     * @param view view
     */
    private void init(View view) {
        viewpagerAppointments = view.findViewById(R.id.container);
        setupViewPager(viewpagerAppointments);

        tabLayoutAppointments = view.findViewById(R.id.tabs);
        tabLayoutAppointments.setupWithViewPager(viewpagerAppointments);

        viewpagerAppointments.setOffscreenPageLimit(3);
    }

    private void setupViewPager(ViewPager viewPager) {


        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new AllChatsFragment(), "Chats");
        adapter.addFragment(new AllChatsFragment(), "Finder");
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
    }
}


