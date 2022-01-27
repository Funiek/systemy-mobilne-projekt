package com.example.trainingapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trainingapp.db.AppDatabase;
import com.example.trainingapp.db.Pull;
import com.example.trainingapp.db.Push;
import com.google.android.material.textfield.TextInputEditText;

public class PushDataActivity extends AppCompatActivity {

    ImageView imageView;
    Button cameraOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_data);

        imageView = findViewById((R.id.image_view));
        cameraOpen = findViewById(R.id.photoData);

        if(ContextCompat.checkSelfPermission(PushDataActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(PushDataActivity.this, new String[]{ Manifest.permission.CAMERA}, 100);
        }


        cameraOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 101);
            }
        });

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

    @Override
    protected void  onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(captureImage);
        }
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