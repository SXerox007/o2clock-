package com.blackdreams.sumitthakur.o2clock.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.blackdreams.sumitthakur.o2clock.MyApplication;

/**
 * Created by sumitthakur on 25/07/18.
 */

public class Util {

    /**
     * startFreshActivity Activity with some data
     *
     * @param fromContext current activity
     * @param toClass     the intended activity
     * @param extras      data
     */
    public static void startActivity(final Activity fromContext, final Class<?> toClass, final Bundle extras) {
        Intent intention = new Intent(fromContext, toClass);
        if (extras != null) {
            intention.putExtras(extras);
        }
        fromContext.startActivity(intention);
    }


    /**
     * Exits the current activity
     *
     * @param fromContext the activity to be finished
     */
    public static void exit(final Activity fromContext) {

        fromContext.finish();
    }


    /**
     * startFreshActivity Activity with some data
     *
     * @param fromContext current activity
     * @param toClass     the intended activity
     * @param extras      the data to be tunneled towards the intended activity
     */
    public static void startFreshActivity(final Activity fromContext, final Class<?> toClass, final Bundle extras) {
        Intent intention = new Intent(fromContext, toClass);
        if (extras != null) {
            intention.putExtras(extras);
        }
        fromContext.startActivity(intention);
        ActivityCompat.finishAffinity(fromContext);
    }


    /**
     * Method to set same OnClickListener on multiple views
     *
     * @param listener listener
     * @param views    views
     */
    public static void setOnClickListener(final View.OnClickListener listener, final View... views) {

        for (View view : views) {
            view.setOnClickListener(listener);
        }
    }



    /**
     * Method to check if internet is connected
     *
     * @param context context of calling class
     * @return true if connected to any network else return false
     */
    public static boolean isNetworkAvailable(final Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) (context.getApplicationContext()).getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



    /**
     * @param resId String res Id
     * @return String resource
     */
    public static String getStringRes(final int resId) {
        return MyApplication.getAppContext().getString(resId);
    }


}
