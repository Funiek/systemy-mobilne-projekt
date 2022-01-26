package com.example.trainingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.trainingapp.db.AppDatabase;
import com.example.trainingapp.db.Legs;
import com.example.trainingapp.db.Push;
import com.google.android.material.textfield.TextInputEditText;

public class LegsDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legs_data);

        TextView przysiadOldData = findViewById(R.id.przysiadOldData);
        TextInputEditText przysiadNewData = findViewById(R.id.przysiadNewData);

        TextView uginanieNogMaszynaOldData = findViewById(R.id.uginanieNogMaszynaOldData);
        TextInputEditText uginanieNogMaszynaNewData = findViewById(R.id.uginanieNogMaszynaNewData);

        TextView wznosyNogOldData = findViewById(R.id.wznosyNogOldData);
        TextInputEditText wznosyNogNewData = findViewById(R.id.wznosyNogNewData);

        TextView wykrokOldData = findViewById(R.id.wykrokOldData);
        TextInputEditText wykrokNewData = findViewById(R.id.wykrokNewData);

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        Legs legsData = db.legsDao().getLastLegs();

        //czy jest jakiś rekord na tabeli
        if(legsData==null) {
            przysiadOldData.setText("Brak danych");
            uginanieNogMaszynaOldData.setText("Brak danych");
            wznosyNogOldData.setText("Brak danych");
            wykrokOldData.setText("Brak danych");
        }
        else {
            przysiadOldData.setText(legsData.przysiad);
            uginanieNogMaszynaOldData.setText(legsData.uginanieNogMaszyna);
            wznosyNogOldData.setText(legsData.wznosyNog);
            wykrokOldData.setText(legsData.wykrok);
        }

        Button acceptLegsData = (Button) findViewById(R.id.acceptLegsData);

        acceptLegsData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(przysiadNewData.getText().toString(),uginanieNogMaszynaNewData.getText().toString(),wznosyNogNewData.getText().toString(),wykrokNewData.getText().toString());
                switchActivity();
            }
        });
    }

    private void saveData(String przysiad, String uginanieNogMaszyna, String wznosyNog, String wykrok) {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        Legs legs = new Legs();

        legs.przysiad = przysiad;
        legs.uginanieNogMaszyna = uginanieNogMaszyna;
        legs.wznosyNog = wznosyNog;
        legs.wykrok = wykrok;

        db.legsDao().insertLegs(legs);
    }

    private void switchActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}