package com.android.jsonpostreqwork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.jsonpostreqwork.Model.TokenRequest;
import com.android.jsonpostreqwork.Model.TokenResponse;
import com.android.jsonpostreqwork.interfaces.ApiTok;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button sendButton;
    private TextView mTextView;
    private ApiTok service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendButton = (Button) findViewById(R.id.buttonSend);
        mTextView = (TextView) findViewById(R.id.textView);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://alisonstech.com/tourist/index.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ApiTok.class);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TokenRequest tokenRequest = new TokenRequest();
                tokenRequest.setCity_id(64);
                tokenRequest.setId(5);
                tokenRequest.setTourist_guide_key_mob("yest_true");

                Call<TokenResponse> tokenResponseCall = service.getTokenAccess(tokenRequest);
                tokenResponseCall.enqueue(new Callback<TokenResponse>() {
                    @Override
                    public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                        Log.d("data", "" + response.body());
                    }

                    @Override
                    public void onFailure(Call<TokenResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
               /* HashMap<String, String> postData = new HashMap<String, String>();
                postData.put("city_id", "64");
                postData.put("tourist_guide_key_mob", "yest_true");
                postData.put("id", "5");

                Call<JSONObject> call = service.getTokenString(postData);
                call.enqueue(new Callback<JSONObject>() {
                    @Override
                    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                        Log.d("On Success",""+response.body());
                    }

                    @Override
                    public void onFailure(Call<JSONObject> call, Throwable t) {
                        Log.d("On Failure",""+t.getMessage());
                        t.printStackTrace();
                    }
                });*/
            }
        });
    }
}
