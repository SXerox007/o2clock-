package com.blackdreams.sumitthakur.o2clock.fragment.home;

import com.blackdreams.sumitthakur.o2clock.base.BaseInteractor;

import java.io.File;

/**
 * Created by sumitthakur on 03/01/19.
 */

public interface HomeFragmentInteractor extends BaseInteractor {


    /**
     * user on camera tap
     * @param file file
     * @param accessToken accessToken
     */
    void onCameraTap(final File file,final String accessToken);
}
