package com.blackdreams.sumitthakur.o2clock.util;

import android.content.Context;

import com.blackdreams.sumitthakur.o2clock.Constants.Keys;

/**
 * Developer: sumitthakur
 * Date: 07/08/19
 */

public class Config {
    /**
     * Method to return default app mode
     *n
     * @return
     */
    //app for release
    private static AppMode getDefaultAppMode() {
        return AppMode.DEV;
    }

    /**
     * Method tell whether the Build is release or not
     *
     * @return
     */
    public static boolean isRelease() {
        return getDefaultAppMode() != AppMode.DEV;
    }


    /**
     * Method to retrieve last saved AppMode
     *
     * @param context
     * @return
     */
    public static AppMode getCurrentAppMode(Context context) {

        AppMode appMode;

        if (isRelease())
            appMode = getDefaultAppMode();
        else
            appMode = getAppMode(context);

        setAppMode(context, appMode.name());

        return appMode;
    }

    /**
     * Method to retrieve last saved AppMode
     *
     * @param context
     * @return
     */
    public static String getServerUrl(Context context) {
        if (getCurrentAppModeName(context).equalsIgnoreCase(AppMode.MANUAL.name())) {
            return Prefs.with(context).getString(Keys.Prefs.APP_MANUAL_MODE, getDefaultAppMode().serverUrl);
        }
        return getCurrentAppMode(context).serverUrl;
    }

    /**
     * Method to retrieve last saved AppMode
     *
     * @param context
     * @return
     */
    public static String getDashboardServer(Context context) {
        return getCurrentAppMode(context).dashboardServer;
    }

    /**
     * Method to save the App Mode
     *
     * @param context
     * @param appModeName
     */
    public static void setAppMode(Context context, String appModeName) {
        Prefs.with(context).save(Keys.Prefs.APP_MODE, appModeName);
    }

    /**
     * Method to return app mode
     *
     * @return
     */
    public static AppMode getAppMode(Context context) {
        return AppMode.valueOf(getCurrentAppModeName(context));
    }

    /**
     * Method to evaluate the Currently used AppMode
     *
     * @param context
     * @return
     */
    public static String getCurrentAppModeName(Context context) {
        return Prefs.with(context).getString(Keys.Prefs.APP_MODE, getDefaultAppMode().name());
    }


    public static String getCurrentAppHost(Context context){
        return Prefs.with(context).getString(Keys.Prefs.APP_TCP_CONNECTION_HOST,"0.tcp.ap.ngrok.io");
    }

    public static int getCurrentAppPort(Context context){
        return Prefs.with(context).getInt(Keys.Prefs.APP_TCP_CONNECTION_PORT,17073);
    }


    /**
     * Various Modes to specify the Server Url
     * intended for the End User
     */
    public enum AppMode {
        APP("https://app.com:0000/", "app"),
        DEV("https://dev-app.com:4040/", "dev"),
        TEST("https://test-app.com:9090/", "test"),
        MANUAL("", "manual");
        private final String serverUrl;
        private final String dashboardServer;

        AppMode(String serverUrl, String dashboardServer) {
            this.serverUrl = serverUrl;
            this.dashboardServer = dashboardServer;
        }
    }
}
