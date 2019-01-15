package com.blackdreams.sumitthakur.o2clock.fragment.register;

import com.blackdreams.sumitthakur.o2clock.base.BaseView;

/**
 * Created by sumitthakur on 30/12/18.
 */

public interface RegisterFragmentView extends BaseView {

    /**
     *
     * @param msg msg
     *
     */
    void showAlertBox(String msg,String title);

    /**
     * register success
     */
    void registerSuccess();
}
