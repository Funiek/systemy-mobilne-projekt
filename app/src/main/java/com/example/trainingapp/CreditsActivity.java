package com.example.trainingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        openDialog();
        createNotificationChannel();

        Button reminderButton = findViewById(R.id.reminderButton);

        reminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreditsActivity.this,"Reminder Set", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(CreditsActivity.this,ReminderBroadcast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(CreditsActivity.this,0,intent,0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                long time = System.currentTimeMillis();

                alarmManager.set(AlarmManager.RTC_WAKEUP,time,pendingIntent);
            }
        });
    }

    public void openDialog() {
        CreditsDialog creditsDialog = new CreditsDialog();
        creditsDialog.show(getSupportFragmentManager(),"CreditsDialog");
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "TrainlyReminderChannel";
            String desc = "Channel for Trainly Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyTrainly",name,importance);
            channel.setDescription(desc);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}