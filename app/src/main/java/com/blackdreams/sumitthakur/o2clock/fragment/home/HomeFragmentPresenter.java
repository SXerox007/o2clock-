package com.blackdreams.sumitthakur.o2clock.fragment.home;

import com.blackdreams.sumitthakur.o2clock.base.BasePresenter;

import java.io.File;

/**
 * Created by sumitthakur on 03/01/19.
 */

public interface HomeFragmentPresenter extends BasePresenter {

    /**
     *  verify user tap
     * @param file file
     */
    void verifyUser(File file);
}
