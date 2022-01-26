package com.example.trainingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pull(View view) {

        Intent intent = new Intent(MainActivity.this, PullActivity.class);
        startActivity(intent);
    }

    public void push(View view) {
        Intent intent = new Intent(MainActivity.this, PushActivity.class);
        startActivity(intent);
    }

    public void abs(View view) {
        Intent intent = new Intent(MainActivity.this, AbsActivity.class);
        startActivity(intent);
    }

    public void legs(View view) {
        Intent intent = new Intent(MainActivity.this, LegsActivity.class);
        startActivity(intent);
    }

    public void run(View view) {
        Intent intent = new Intent(MainActivity.this, RunActivity.class);
        startActivity(intent);
    }

    public void pullData(View view) {
        Intent intent = new Intent(MainActivity.this, PullDataActivity.class);
        startActivity(intent);
    }

    public void pushData(View view) {
        Intent intent = new Intent(MainActivity.this, PushDataActivity.class);
        startActivity(intent);
    }

    public void runData(View view) {
        Intent intent = new Intent(MainActivity.this, RunDataActivity.class);
        startActivity(intent);
    }

    public void legsData(View view) {
        Intent intent = new Intent(MainActivity.this, LegsDataActivity.class);
        startActivity(intent);
    }

    public void absData(View view) {
        Intent intent = new Intent(MainActivity.this, AbsDataActivity.class);
        startActivity(intent);
    }
}