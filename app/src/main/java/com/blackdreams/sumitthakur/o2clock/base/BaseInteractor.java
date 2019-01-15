package com.blackdreams.sumitthakur.o2clock.base;


import com.blackdreams.sumitthakur.o2clock.network.ApiError;

/**
 * Created by sumitthakur on 29/07/18.
 */

public interface BaseInteractor {
    interface ApiListener {
        /**
         * On success.
         *
         */
        void onSuccess();

        /**
         * On failure.
         *
         * @param apiError  the api error
         * @param throwable the throwable
         */
        void onFailure(ApiError apiError, Throwable throwable);
    }
}
