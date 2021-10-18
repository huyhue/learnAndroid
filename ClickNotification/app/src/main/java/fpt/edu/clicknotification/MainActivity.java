package fpt.edu.clicknotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn_send);
        Button btnList = findViewById(R.id.btn_go_to_list);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListProductActivity.class);
                startActivity(intent);
            }
        });
    }

    private void sendNotification() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        // Create an Intent for the activity you want to start
        Intent resultIntent = new Intent(this, DetailActivity.class);
// Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
// Get the PendingIntent containing the entire back stack
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent notifyIntent = new Intent(this, DetailActivity.class);
// Set the Activity to start in a new, empty task
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
// Create the PendingIntent
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                this, getNotificationId(), notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );

        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setContentTitle("TITLE_PUSH_NOTIFICATION")
                .setContentText("CONTENT_PUSH_NOTIFICATION")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setSound(uri)
                .setContentIntent(notifyPendingIntent)
                .setAutoCancel(true)
                .setLargeIcon(bitmap)
                .build();

        NotificationManagerCompat notificationCompat = NotificationManagerCompat.from(this);
        notificationCompat.notify(getNotificationId(), notification);
    }
    private int getNotificationId() {
        return (int)new Date().getTime();
    }
}