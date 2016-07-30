package com.android.jsonpostreqwork.interfaces;

import com.android.jsonpostreqwork.Model.TokenRequest;
import com.android.jsonpostreqwork.Model.TokenResponse;

import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Kamran ALi on 7/31/2016.
 */
public interface ApiTok {
    @POST("Place/getplacesbycatid")
    Call<TokenResponse> getTokenAccess(@Body TokenRequest tokenRequest);

    @POST("Place/getplacesbycatid")
    Call<JSONObject> getTokenString(@Body HashMap<String , String> tokenRequest);
}
