package com.blackdreams.sumitthakur.o2clock.util;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Generic methods to perform action on soft input mode
 */
public final class KeyboardUtil {
    /**
     * Empty Constructor
     * not called
     */
    private KeyboardUtil() {
    }

    /**
     * Show keyboard.
     *
     * @param activity the activity
     * @param view     the view
     */
    public static void showKeyboard(final Activity activity, final View view) {
        if (activity != null) {
            if (view != null) {
                view.requestFocus();
            }
            final InputMethodManager imm = (InputMethodManager)
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            }
        }
    }

    /**
     * Adds automation to the editText moving to next or previous
     *
     * @param mSize    size after which you want to move to next editText
     * @param editText reference to all the edit to move next or previous location
     */

    public static void moveFrontAndBack(final int mSize, final EditText... editText) {
        for (int pos = 0; pos < (editText.length - 1); pos++) {
            moveToNextEt(editText[pos], editText[pos + 1], mSize);
        }
        for (int pos = 1; pos < editText.length; pos++) {
            moveToPreviousEt(editText[pos], editText[pos - 1]);
        }
    }

    /**
     * adds Functionality to move automatically to next field or edit text
     *
     * @param mCurrentEt current reference to EditText
     * @param mNextEt    refernce to next editText shifted to
     * @param mSize      size after which to move to the next position
     */
    public static void moveToNextEt(final EditText mCurrentEt, final EditText mNextEt, final int mSize) {
        mCurrentEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                if (mCurrentEt.getText().toString().length() == mSize) {
                    mNextEt.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(final Editable s) {

            }
        });
    }

    /**
     * adds Functionality to move automaticaly to previous field or edit text
     *
     * @param mCurrentEt current reference to EditText
     * @param mPrevEt    refernce to next edittext shifted to
     */
    public static void moveToPreviousEt(final EditText mCurrentEt, final EditText mPrevEt) {
        mCurrentEt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(final View view, final int keyCode, final KeyEvent keyEvent) {
                Log.e("onKey pressed", "==" + keyCode);
                Log.e("text", "==" + mCurrentEt.getText().toString());
                if (keyCode == KeyEvent.KEYCODE_DEL
                        && keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        && mCurrentEt.getText().toString().isEmpty()) {
                    mPrevEt.requestFocus();
                }
                return false;
            }
        });
    }

    /**
     * Hide keyboard.
     *
     * @param activity the activity
     */
    public static void hideKeyboard(final Activity activity) {
        if (activity != null) {
            final InputMethodManager imm = (InputMethodManager)
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null && activity.getCurrentFocus() != null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
                activity.getCurrentFocus().clearFocus();
            }
        }
    }

    /**
     * Hide keyboard.
     *
     * @param activity the activity
     * @param view     the view
     */
    public static void hideKeyboard(final Activity activity, final View view) {
        if (activity != null) {
            if (view != null) {
                final InputMethodManager imm = (InputMethodManager)
                        activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            } else {
                hideKeyboard(activity);
            }
        }
    }
}
