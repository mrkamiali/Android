package com.android.jsonrequestproj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.jsonrequestproj.DataModel.Weather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22Pakistan%2C%20Karachi%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
    private TextView cityTextView, dateTextView, tempTextView, condTextView;
    private Button refButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityTextView = (TextView) findViewById(R.id.textViewCity);
        dateTextView = (TextView) findViewById(R.id.textViewTime);
        tempTextView = (TextView) findViewById(R.id.textViewTemp);
        condTextView = (TextView) findViewById(R.id.textViewcond);
        refButton = (Button) findViewById(R.id.buttonRefresh);
        //Google Retrofit and implement it ?smjao
        //Retrofit library hai networking ki app network ki request ker sakty ho android to internet
        //isko implement kero or practice k liye ek url dunga us per post request me data send kerna
        //return me tumhy server se response milega usko log me ya textview me show kerwa dena...
        //yar response main server ki beti ka hath nhi milyga mery lie ???
        //kia kehna chah raha hai..?
        //bai post karunga theek ,,, wo response ma text  ya kch b dega main chahta hn k server se
        //response main uski beti mil jy ??? is it possible
        //ye kerlo phir server se b baat kerlengy uski beti k liye..
        //ok
        refButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeatherAPi.Factory.getInstance().getWeather().enqueue(new Callback<Weather>() {
                    @Override
                    public void onResponse(Call<Weather> call, Response<Weather> response) {
                        cityTextView.setText(response.body().getQuery().getResults().getChannel().getLocation().getCity());
                        dateTextView.setText(response.body().getQuery().getResults().getChannel().getLastBuildDate());
                        tempTextView.setText(response.body().getQuery().getResults().getChannel().getItem().getCondition().getTemp());
                        condTextView.setText(response.body().getQuery().getResults().getChannel().getItem().getCondition().getText());
                        Log.d("","Location "+response.body().getQuery().getResults().getChannel().getItem().getCondition().getTemp());
                    }

                    //
                    @Override
                    public void onFailure(Call<Weather> call, Throwable t) {
                        Log.d("Failed",""+t.getMessage());
                    }
                });
            }
        });
    }
    public void refresh(View view){


    }
}
