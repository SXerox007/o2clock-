package com.blackdreams.sumitthakur.o2clock.fragment.home.drawer.chat.allusers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackdreams.sumitthakur.o2clock.R;
import com.blackdreams.sumitthakur.o2clock.adapter.AllUsersAdapter;
import com.blackdreams.sumitthakur.o2clock.base.BaseFragment;

/**
 * Developer: sumitthakur
 * Date: 16/01/19
 */

public class AllUsers extends BaseFragment implements View.OnClickListener {


    /**
     * Creating Instance of Fragment
     *
     * @return Instance of Fragment
     */
    public static Fragment newInstance() {
        return new AllUsers();
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_users, container, false);
        init(view);
        return view;
    }

    /**
     *  initilize
     * @param view view
     */
    private void init(View view) {
        AllUsersAdapter adapter = new AllUsersAdapter();
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView rvGroupAndUsers = view.findViewById(R.id.rvGroupAndUsers);
        rvGroupAndUsers.setLayoutManager(mLinearLayoutManager);
        rvGroupAndUsers.setAdapter(adapter);
        adapter.init();
    }

}
