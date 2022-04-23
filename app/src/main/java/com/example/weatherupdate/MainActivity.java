package com.example.weatherupdate;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.weatherupdate.Retrofit.ApiClient;
import com.example.weatherupdate.Retrofit.ApiInterface;
import com.example.weatherupdate.Retrofit.Example;

public class MainActivity extends AppCompatActivity {
    EditText editCountry;
    TextView temp,description, humidity,pressure;
    Button btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editCountry = findViewById(R.id.editCountry);
        temp = findViewById(R.id.temp);
        pressure = findViewById(R.id.pressure);
        description = findViewById(R.id.description);
        humidity = findViewById(R.id.humidity);
        btnGet = findViewById(R.id.btnGet);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWeatherData(editCountry.getText().toString().trim());


            }
        });

    }

    private void getWeatherData(String name){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Example> call = apiInterface.getWeatherData(name);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                temp.setText("Temp: "+response.body().getMain().getTemp()+" °C");
                description.setText("Feels Like: "+response.body().getMain().getFeels_like()+" °C");
                humidity.setText("Humidity: "+response.body().getMain().getHumidity()+" %");
                pressure.setText("Pressure: "+response.body().getMain().getPressure()+" Pa");
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });

    }

}