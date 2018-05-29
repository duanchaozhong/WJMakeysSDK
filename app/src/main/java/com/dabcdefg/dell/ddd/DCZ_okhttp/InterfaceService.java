package com.dabcdefg.dell.ddd.DCZ_okhttp;


import com.dabcdefg.dell.ddd.AuBean;
import com.dabcdefg.dell.ddd.LayoutBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface InterfaceService {

    @FormUrlEncoded
    @POST("/loginByAuthCode")
    Call<AuBean>getData(@Field("code")String code,
                        @Field("redirectUri") String redirectUri,
                        @Field("state") String state,
                        @Field("nonce") String nonce);
    @FormUrlEncoded
    @POST("/logout")
    Call<LayoutBean>layout(@Field("deviceUUID") String deviceUUID);

}
