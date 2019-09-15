package com.blackdreams.sumitthakur.o2clock.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.blackdreams.sumitthakur.o2clock.Constants.AppConstants;
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
     * Method to check for empty {@link EditText}s
     *
     * @param editText
     * @return
     */
    public static boolean isEmpty(EditText editText) {

        return isEmpty(editText, AppConstants.EMPTY_STRING);
    }


    public static boolean isEmpty(EditText editText, String message) {

        if (get(editText).isEmpty()) {
            setErrorOn(editText, message);
            return true;
        }

        return false;
    }

    /**
     * Method to set errors on the fields
     *
     * @param editText
     * @param message
     */
    public static void setErrorOn(EditText editText, String message) {

        editText.requestFocus();

        if (message.trim().equals(AppConstants.EMPTY_STRING))
            editText.setError("Field Can't be set empty");
        else
            editText.setError(message);
    }

    /**
     * Validates the character sequence with url format
     *
     * @param url
     * @return true, if the string entered by user is syntactically correct as
     * url, false otherwise
     */
    public static boolean isUrlValid(String url) {

        // Check whether the Email is valid
        if (url == null) return false;

        return Patterns.WEB_URL.matcher(url).matches();
    }


        /**
         * Method to extract the Text from TextView
         *
         * @param editText
         * @return
         */
    public static String get(EditText editText) {

        if (editText == null)
            return "";

        return editText.getText().toString().trim();
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



    /**
     * Method used to hide keyboard if outside touched.
     *
     * @param activity
     */

    public static void showSoftKeyboard(Activity activity) {

        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showSoftKeyboard(Activity activity, View view) {

        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
