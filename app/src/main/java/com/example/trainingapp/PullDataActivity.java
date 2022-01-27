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
import com.google.android.material.textfield.TextInputEditText;

public class PullDataActivity extends AppCompatActivity {

    ImageView imageView;
    Button cameraOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_data);


        imageView = findViewById((R.id.image_view));
        cameraOpen = findViewById(R.id.photoData);

        if(ContextCompat.checkSelfPermission(PullDataActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(PullDataActivity.this, new String[]{ Manifest.permission.CAMERA}, 100);
        }


        cameraOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 101);
            }
        });


        //martwy ciag
        TextView martwyCiagOldData = (TextView) findViewById(R.id.martwyCiagOldData);
        TextInputEditText martwyCiagNewData = (TextInputEditText) findViewById(R.id.martwyCiagNewData);

        //negatyw
        TextView negatywOldData = (TextView) findViewById(R.id.negatywOldData);
        TextInputEditText negatywNewData = (TextInputEditText) findViewById(R.id.negatywNewData);

        //sciaganieDrazka
        TextView sciaganieDrazkaOldData = (TextView) findViewById(R.id.sciaganieDrazkaOldData);
        TextInputEditText sciaganieDrazkaNewData = (TextInputEditText) findViewById(R.id.sciaganieDrazkaNewData);

        //ohp
        TextView ohpOldData = (TextView) findViewById(R.id.ohpOldData);
        TextInputEditText ohpNewData = (TextInputEditText) findViewById(R.id.ohpNewData);

        //modlitewnik
        TextView modlitewnikOldData = (TextView) findViewById(R.id.modlitewnikOldData);
        TextInputEditText modlitewnikNewData = (TextInputEditText) findViewById(R.id.modlitewnikNewData);

        //wioslowanie
        TextView wioslowanieOldData = (TextView) findViewById(R.id.wioslowanieOldData);
        TextInputEditText wioslowanieNewData = (TextInputEditText) findViewById(R.id.wioslowanieNewData);

        //mlotki
        TextView mlotkiOldData = (TextView) findViewById(R.id.mlotkiOldData);
        TextInputEditText mlotkiNewData = (TextInputEditText) findViewById(R.id.mlotkiNewData);

        //przedramiona
        TextView przedramionaOldData = (TextView) findViewById(R.id.przedramionaOldData);
        TextInputEditText przedramionaNewData = (TextInputEditText) findViewById(R.id.przedramionaNewData);

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        Pull pullData = db.pullDao().getLastPull();

        //czy jest jakiś rekord na tabeli
        if(pullData==null) {
            martwyCiagOldData.setText("Brak danych");
            negatywOldData.setText("Brak danych");
            sciaganieDrazkaOldData.setText("Brak danych");
            ohpOldData.setText("Brak danych");
            wioslowanieOldData.setText("Brak danych");
            mlotkiOldData.setText("Brak danych");
            przedramionaOldData.setText("Brak danych");
            modlitewnikOldData.setText("Brak danych");
        }
        else {
            martwyCiagOldData.setText(pullData.martwy);
            negatywOldData.setText(pullData.negatyw);
            sciaganieDrazkaOldData.setText(pullData.sciaganieDrazkaGora);
            ohpOldData.setText(pullData.ohpHantelkami);
            wioslowanieOldData.setText(pullData.wioslowanie);
            mlotkiOldData.setText(pullData.mlotki);
            przedramionaOldData.setText(pullData.przedramiona);
            modlitewnikOldData.setText(pullData.modlitewnik);
        }

        Button acceptPullData = (Button) findViewById(R.id.acceptPullData);

        acceptPullData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(martwyCiagNewData.getText().toString(),negatywNewData.getText().toString(),sciaganieDrazkaNewData.getText().toString(),ohpNewData.getText().toString(),wioslowanieNewData.getText().toString(),mlotkiNewData.getText().toString(),przedramionaNewData.getText().toString(),modlitewnikNewData.getText().toString());
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

    private void saveData(String martwyCiag, String negatyw, String sciaganieDrazka, String ohp, String wioslowanie, String mlotki, String przedramiona, String modlitewnik) {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        Pull pull = new Pull();

        pull.modlitewnik = modlitewnik;
        pull.martwy = martwyCiag;
        pull.negatyw = negatyw;
        pull.sciaganieDrazkaGora = sciaganieDrazka;
        pull.ohpHantelkami = ohp;
        pull.wioslowanie = wioslowanie;
        pull.mlotki = mlotki;
        pull.przedramiona = przedramiona;

        db.pullDao().insertPull(pull);
    }

    private void switchActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}