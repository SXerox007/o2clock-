package com.blackdreams.sumitthakur.o2clock.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.blackdreams.sumitthakur.o2clock.R;

/**
 * Created by sumitthakur on 25/07/18.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private BaseActivity baseActivity;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivity = this;

    }


    //show tost
    @Override
    public void showToast(final String text) {
        Toast.makeText(baseActivity, text, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showSnackBar(final String text, final View view) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    /**
     * @param toolBar toolbar
     */
    public void setToolBar(final Toolbar toolBar) {
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle("");
    }


}
