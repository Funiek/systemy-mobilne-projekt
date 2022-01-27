package com.example.trainingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import lombok.SneakyThrows;

public class WeatherActivity extends AppCompatActivity {

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        TextView temperature = findViewById(R.id.temperatue);
        TextView temperature2 = findViewById(R.id.temperatue2);
        TextView whatWeather = findViewById(R.id.whatWeather);

        final String API_KEY = "50ef1767f7736a824dae096f8a51da90";

        NumberFormat nf = DecimalFormat.getInstance();
        nf.setMaximumFractionDigits(0);

        AsyncTask<Void, Void, String> ip = new RetriveIPTask().execute();

        String latLongUrl = "http://ip-api.com/json/"+ip.get()+"?fields=lat,lon,query";

        AsyncTask<String, Void, JSONObject> latLong = new RetriveJSONTask().execute(latLongUrl);

        JSONObject jsonLatLong = latLong.get();

        String jsonWeatherDataUrl = "http://api.openweathermap.org/data/2.5/weather?lat="+jsonLatLong.get("lat")+"&lon="+jsonLatLong.get("lon")+"&lang=pl&appid="+API_KEY;

        AsyncTask<String, Void, JSONObject> jsonWeather = new RetriveJSONTask().execute(jsonWeatherDataUrl);

        JSONObject jsonWeatherData = jsonWeather.get();

        double currentTempDouble = (double) Math.round((Double.parseDouble(jsonWeatherData.getJSONObject("main").get("temp").toString())-273.15) * 100) / 100;

        double feelsLikeTempDouble = (double) Math.round((Double.parseDouble(jsonWeatherData.getJSONObject("main").get("feels_like").toString())-273.15) * 100) / 100;

        JSONObject temp = new JSONObject(jsonWeatherData.getJSONArray("weather").get(0).toString());
        String weather = capitalize(temp.get("description").toString());

        temperature.setText(nf.format(currentTempDouble));
        temperature2.setText(nf.format(feelsLikeTempDouble));
        whatWeather.setText(weather);
    }

    private String capitalize(String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
}