package com.example.trainingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.trainingapp.db.AppDatabase;
import com.example.trainingapp.db.Push;
import com.example.trainingapp.db.Run;
import com.google.android.material.textfield.TextInputEditText;

public class RunDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_data);

        TextView odlegloscOldData = findViewById(R.id.odlegloscOldData);
        TextInputEditText odlegloscNewData = findViewById(R.id.odlegloscNewData);

        TextView czasOldData = findViewById(R.id.czasOldData);
        TextInputEditText czasNewData = findViewById(R.id.czasNewData);

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

        acceptRunData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(odlegloscNewData.getText().toString(),czasNewData.getText().toString());
                switchActivity();
            }
        });
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
}