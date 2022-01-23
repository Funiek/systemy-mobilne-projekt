package com.example.trainingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {


    Animation up, down;

    ImageView logoText;

    ImageView logoLogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView logoText = findViewById(R.id.applogoText);
        up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.up);
        logoText.setAnimation(up);

        ImageView logoLogo = findViewById(R.id.applogoLogo);
        down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.down);
        logoLogo.setAnimation(down);


//        TextView logoLogo = findViewById(R.id.appname);
//        down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.down);
//        logoLogo.setAnimation(down);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 3500);

    }
}