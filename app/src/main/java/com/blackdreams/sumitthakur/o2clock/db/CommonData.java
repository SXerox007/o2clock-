package com.blackdreams.sumitthakur.o2clock.db;

import com.blackdreams.sumitthakur.o2clock.Constants.PaperDbConstants;

import io.paperdb.Paper;

/**
 * Created by sumitthakur on 25/07/18.
 */

//Paper db class
public class CommonData implements PaperDbConstants {


    private CommonData() {
    }

    //======================================== Flavour type ============================================
    /**
     * returns type of universal App flavor(dev,test,client) ,if Build Variant is universal
     *
     * @return Universal App Flavor Type (default value, DEV)
     */
    public static String getFlavorType() {
        return Paper.book().read(UNIVERSAL_TYPE, DEV);
    }

    /**
     * sets type of universal App flavor(dev,test,client) ,if Build Variant is universal
     *
     * @param flavorType , which type of flavor (DEV,QA,LIVE) in case of Universal App build variant is selected
     */
    public static void setFlavorType(final String flavorType) {
        Paper.book().write(UNIVERSAL_TYPE, flavorType);
    }


//======================================== Access Token ============================================

    /**
     * Save access token
     *
     * @param accessToken user access token
     */
    public static void saveAccessToken(final String accessToken) {
        Paper.book().write(PAPER_ACCESS_TOKEN, accessToken);
    }

    /**
     * Gets access token.
     *
     * @return the access token
     */
    public static String getAccessToken() {
        if (exist(PAPER_ACCESS_TOKEN)) {
            return Paper.book().read(PAPER_ACCESS_TOKEN);
        }else{
            return "";
        }
    }



    //======================================== Clear db ============================================

    public static void cleardb(){
        Paper.book().delete(PAPER_ACCESS_TOKEN);
    }



    //======================================== Key check ============================================

    /**
     * @param key key
     * @return value
     */
    public static boolean exist(final String key) {
        return Paper.book().contains(key);
    }


}
