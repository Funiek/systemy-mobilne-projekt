package com.example.trainingapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trainingapp.db.AppDatabase;
import com.example.trainingapp.db.Run;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.text.DecimalFormat;

import lombok.SneakyThrows;

public class RunDataActivity extends AppCompatActivity {

    ImageView imageView;
    Button cameraOpen;

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_data);

        imageView = findViewById((R.id.image_view));
        cameraOpen = findViewById(R.id.photoData);

        if(ContextCompat.checkSelfPermission(RunDataActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(RunDataActivity.this, new String[]{ Manifest.permission.CAMERA}, 100);
        }


        cameraOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 101);
            }
        });

        TextView odlegloscOldData = findViewById(R.id.odlegloscOldData);
        TextInputEditText odlegloscNewData = findViewById(R.id.odlegloscNewData);

        TextView czasOldData = findViewById(R.id.czasOldData);
        TextInputEditText czasNewData = findViewById(R.id.czasNewData);

        TextView weatherData = findViewById(R.id.weatherData);

        final String API_KEY = "50ef1767f7736a824dae096f8a51da90";

        AsyncTask<Void, Void, String> ip = new RetriveIPTask().execute();

        String latLongUrl = "http://ip-api.com/json/"+ip.get()+"?fields=lat,lon,query";

        AsyncTask<String, Void, JSONObject> latLong = new RetriveJSONTask().execute(latLongUrl);

        JSONObject jsonLatLong = latLong.get();

        String jsonWeatherDataUrl = "http://api.openweathermap.org/data/2.5/weather?lat="+jsonLatLong.get("lat")+"&lon="+jsonLatLong.get("lon")+"&appid="+API_KEY;

        AsyncTask<String, Void, JSONObject> jsonWeather = new RetriveJSONTask().execute(jsonWeatherDataUrl);

        JSONObject jsonWeatherData = jsonWeather.get();

        double currentTempDouble = (double) Math.round((Double.parseDouble(jsonWeatherData.getJSONObject("main").get("temp").toString())-273.15) * 100) / 100;

        JSONObject temp = new JSONObject(jsonWeatherData.getJSONArray("weather").get(0).toString());
        String weather = temp.get("main").toString();

        if(weather.contains("Rain") || currentTempDouble < 0) weatherData.setText("Nie");
        else weatherData.setText("Tak");

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        Run runData = db.runDao().getLastRun();

        //czy jest jakiś rekord na tabeli
        if(runData==null) {
            odlegloscOldData.setText("Brak danych");
            czasOldData.setText("Brak danych");
        }
        else {
            odlegloscOldData.setText(runData.odleglosc);
            czasOldData.setText(runData.czas);
        }

        Button acceptRunData = (Button) findViewById(R.id.acceptRunData);

        Button weatherDetails = (Button) findViewById(R.id.weatherDetails);

        weatherDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivityWeather();
            }
        });

        acceptRunData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(odlegloscNewData.getText().toString(),czasNewData.getText().toString());
                switchActivity();
            }
        });

    }

    @Override
    protected void  onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(captureImage);
        }
    }

    private void saveData(String odleglosc, String czas) {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        Run run = new Run();

        run.odleglosc=odleglosc;
        run.czas=czas;

        db.runDao().insertRun(run);
    }

    private void switchActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void switchActivityWeather() {
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
    }
}