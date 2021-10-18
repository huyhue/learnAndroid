package fpt.edu.pushnoficationfirebase.fcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import fpt.edu.pushnoficationfirebase.MainActivity;
import fpt.edu.pushnoficationfirebase.MyApplication;
import fpt.edu.pushnoficationfirebase.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static final String TAG = MyFirebaseMessagingService.class.getName();

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        if (notification == null){
            return;
        }
        String strTitle = notification.getTitle();
        String strMessage = notification.getBody();
        Toast.makeText(this,strTitle + " - " + strMessage,Toast.LENGTH_LONG).show();

//        Map<String, String> stringMap = remoteMessage.getData();
//        String strTitle = stringMap.get("user_name");
//        String strMessage = stringMap.get("description");

        sendNotification(strTitle, strMessage);
    }

    private void sendNotification(String strTitle, String strMessage) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setContentTitle(strTitle)
                .setContentText(strMessage)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent);

        Notification notification = notificationBuilder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notification != null){
            notificationManager.notify(1, notification);
        }
    }

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.e(TAG, token);
    }
}
