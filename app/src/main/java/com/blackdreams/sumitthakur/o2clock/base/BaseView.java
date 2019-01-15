package com.blackdreams.sumitthakur.o2clock.base;

import android.view.View;

/**
 * Created by sumitthakur on 25/07/18.
 */

public interface BaseView {

    /**
     * @param text text
     */
    void showToast(final String text);


    void showSnackBar(final String text, final View view);
}
