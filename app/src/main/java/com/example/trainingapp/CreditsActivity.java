package com.example.trainingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        openDialog();
    }

    public void openDialog() {
        CreditsDialog creditsDialog = new CreditsDialog();
        creditsDialog.show(getSupportFragmentManager(),"CreditsDialog");
    }
}