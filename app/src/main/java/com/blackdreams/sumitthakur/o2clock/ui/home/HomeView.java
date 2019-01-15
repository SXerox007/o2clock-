package com.blackdreams.sumitthakur.o2clock.ui.home;

import com.blackdreams.sumitthakur.o2clock.base.BaseView;

/**
 * Created by sumitthakur on 01/01/19.
 */

public interface HomeView extends BaseView {
    /**
     * on logout success
     */
    void onLogoutSuccess();

    /**
     * on logout failure
     */
    void onLogoutFailure(final String message,final String title);
}
