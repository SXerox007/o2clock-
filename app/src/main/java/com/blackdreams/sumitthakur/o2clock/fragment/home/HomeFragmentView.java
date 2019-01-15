package com.blackdreams.sumitthakur.o2clock.fragment.home;

import com.blackdreams.sumitthakur.o2clock.base.BaseView;

/**
 * Created by sumitthakur on 03/01/19.
 */

public interface HomeFragmentView extends BaseView {

    /**
     * on login success
     */
    void onVerifyUserSuccess();

    /**
     * on login error
     */
    void onVerifyUserError(final String message,final String title);
}
