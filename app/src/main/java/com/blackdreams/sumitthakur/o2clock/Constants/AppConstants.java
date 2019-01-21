package com.blackdreams.sumitthakur.o2clock.Constants;

/**
 * Created by sumitthakur on 26/07/18.
 */

public interface AppConstants {

    String USER_INFO = "user_info";
    String YOUR_SECURITY_KEY = "o2clock1.0.0";
    String PREF_USERNAME = "user_name";
    String PREF_PASSWORD = "PREF_PASSWORD";

    String LOG_IN = "login";
    String SIGN_UP = "signup";
    String KEY = "key";

    String TUTORIAL_BUNDLE_FIRST = "first";
    String TUTORIAL_BUNDLE_SECOND = "second";
    String TUTORIAL_BUNDLE_THIRD = "third";


    //-----------RegEx----------
    String REGEX_NUMERIC_STRING = "(^.\\d*$)";
    String REGEX_MOBILE_NUMBER = "(^[0-9]{10}$)";
    int MIN_PASSWORD_LENGTH = 8;



    //chat
    String SENDER_ID = "SENDER_ID";
    String RECIVER_ID = "RECIVER_ID";
    String SENDER_NAME = "SENDER_NAME";
    String RECIVER_NAME = "RECIVER_NAME";
    String IS_GROUP = "IS_GROUP";
    String CHAT_ID = "CHAT_ID";
}
