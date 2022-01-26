package com.example.trainingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.trainingapp.db.AppDatabase;
import com.example.trainingapp.db.Pull;
import com.example.trainingapp.db.Push;
import com.google.android.material.textfield.TextInputEditText;

public class PushDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_data);

        TextView lawkaPlaskaOldData = findViewById(R.id.lawkaPlaskaOldData);
        TextInputEditText lawkaPlaskaNewData = findViewById(R.id.lawkaPlaskaNewData);

        TextView lawkaSkosnaOldData = findViewById(R.id.lawkaSkosnaOldData);
        TextInputEditText lawkaSkosnaNewData = findViewById(R.id.lawkaSkosnaNewData);

        TextView tricepsLinkiOldData = findViewById(R.id.tricepsLinkiOldData);
        TextInputEditText tricepsLinkiNewData = findViewById(R.id.tricepsLinkiNewData);

        TextView klataLinkiOldData = findViewById(R.id.klataLinkiOldData);
        TextInputEditText klataLinkiNewData = findViewById(R.id.klataLinkiNewData);

        TextView wznosyBokiOburaczOldData = findViewById(R.id.wznosyBokiOburaczOldData);
        TextInputEditText wznosyBokiOburaczNewData = findViewById(R.id.wznosyBokiOburaczNewData);

        TextView kapturyOldData = findViewById(R.id.kapturyOldData);
        TextInputEditText kapturyNewData = findViewById(R.id.kapturyNewData);

        TextView arnoldkiOldData = findViewById(R.id.arnoldkiOldData);
        TextInputEditText arnoldkiNewData = findViewById(R.id.arnoldkiNewData);

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        Push pushData = db.pushDao().getLastPush();

        //czy jest jakiś rekord na tabeli
        if(pushData==null) {
            lawkaPlaskaOldData.setText("Brak danych");
            lawkaSkosnaOldData.setText("Brak danych");
            tricepsLinkiOldData.setText("Brak danych");
            klataLinkiOldData.setText("Brak danych");
            wznosyBokiOburaczOldData.setText("Brak danych");
            kapturyOldData.setText("Brak danych");
            arnoldkiOldData.setText("Brak danych");
        }
        else {
            lawkaPlaskaOldData.setText(pushData.lawkaPlaska);
            lawkaSkosnaOldData.setText(pushData.lawkaSkosna);
            tricepsLinkiOldData.setText(pushData.tricepsLinki);
            klataLinkiOldData.setText(pushData.klataLinki);
            wznosyBokiOburaczOldData.setText(pushData.wznosyBokiOburacz);
            kapturyOldData.setText(pushData.kaptury);
            arnoldkiOldData.setText(pushData.arnoldki);
        }

        Button acceptPushData = (Button) findViewById(R.id.acceptPushData);

        acceptPushData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(lawkaPlaskaNewData.getText().toString(),lawkaSkosnaNewData.getText().toString(),tricepsLinkiNewData.getText().toString(),klataLinkiNewData.getText().toString(),wznosyBokiOburaczNewData.getText().toString(),kapturyNewData.getText().toString(),arnoldkiNewData.getText().toString());
                switchActivity();
            }
        });
    }

    private void saveData(String lawkaPlaska, String lawkaSkosna, String tricepsLinki, String klataLinki, String wznosyBokiOburacz, String kaptury, String arnoldki) {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        Push push = new Push();

        push.lawkaPlaska = lawkaPlaska;
        push.lawkaSkosna = lawkaSkosna;
        push.tricepsLinki = tricepsLinki;
        push.klataLinki = klataLinki;
        push.wznosyBokiOburacz = wznosyBokiOburacz;
        push.kaptury = kaptury;
        push.arnoldki = arnoldki;

        db.pushDao().insertPush(push);
    }

    private void switchActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}