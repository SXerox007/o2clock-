package com.blackdreams.sumitthakur.o2clock.network;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * The API interface for your application
 */
public interface ApiInterface {
    String GET_YOUTUBE_VIDEOS = "/youtube/v3/search";


    /**
     * @param language language
     * @param map      map
     * @return response
     */
//    @FormUrlEncoded
//    @POST(SOCIAL_LOGIN)
//    Call<ResponseSocialLogin> socialLogin(@Header(LANGUAGE_CONTENT) String language, @FieldMap HashMap<String, String> map);


}
