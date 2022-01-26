package com.example.trainingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.trainingapp.db.Abs;
import com.example.trainingapp.db.AppDatabase;
import com.example.trainingapp.db.Legs;
import com.google.android.material.textfield.TextInputEditText;

public class AbsDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_data);

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