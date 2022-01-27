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

import com.example.trainingapp.db.Abs;
import com.example.trainingapp.db.AppDatabase;
import com.example.trainingapp.db.Legs;
import com.google.android.material.textfield.TextInputEditText;

public class AbsDataActivity extends AppCompatActivity {

    ImageView imageView;
    Button cameraOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_data);

        imageView = findViewById((R.id.image_view));
        cameraOpen = findViewById(R.id.photoData);

        if(ContextCompat.checkSelfPermission(AbsDataActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(AbsDataActivity.this, new String[]{ Manifest.permission.CAMERA}, 100);
        }


        cameraOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 101);
            }
        });



        TextView deskaOldData = findViewById(R.id.deskaOldData);
        TextInputEditText deskaNewData = findViewById(R.id.deskaNewData);

        TextView linkaSkosOldData = findViewById(R.id.linkaSkosOldData);
        TextInputEditText linkaSkosNewData = findViewById(R.id.linkaSkosNewData);

        TextView robakOldData = findViewById(R.id.robakOldData);
        TextInputEditText robakNewData = findViewById(R.id.robakNewData);

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        Abs absData = db.absDao().getLastAbs();

        //czy jest jakiś rekord na tabeli
        if(absData==null) {
            deskaOldData.setText("Brak danych");
            linkaSkosOldData.setText("Brak danych");
            robakOldData.setText("Brak danych");
        }
        else {
            deskaOldData.setText(absData.deska);
            linkaSkosOldData.setText(absData.linkaSkos);
            robakOldData.setText(absData.robak);
        }

        Button acceptAbsData = (Button) findViewById(R.id.acceptAbsData);

        acceptAbsData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(deskaNewData.getText().toString(),linkaSkosNewData.getText().toString(),robakNewData.getText().toString());
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

    private void saveData(String deska, String linkaSkos, String robak) {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        Abs abs = new Abs();

        abs.deska = deska;
        abs.linkaSkos = linkaSkos;
        abs.robak = robak;

        db.absDao().insertAbs(abs);
    }

    private void switchActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}