package fpt.edu.notificationdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String TITLE_PUSH_NOTIFICATION = "tương thích với Android 8.0 (API cấp 26)";
    public static final String CONTENT_PUSH_NOTIFICATION = "Theo mặc định, nội dung văn bản của thông báo được cắt bớt để vừa với một dòng. Nếu bạn muốn thông báo của mình dài hơn, bạn có thể bật thông báo có thể mở rộng bằng cách thêm mẫu kiểu với setStyle(). Ví dụ, đoạn mã sau tạo ra một vùng văn bản lớn hơn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn_send_nofication);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });
        Button btn2 = findViewById(R.id.btn_send_nofication_2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification2();
            }
        });
        Button btnCustom = findViewById(R.id.btn_custom_nofication);
        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCustomNotification();
            }
        });
    }

    private void sendCustomNotification() {
        Uri sound = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.nhac);

        //collapsed
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_custom_notification);
        remoteViews.setTextViewText(R.id.tv_title_custom, "Huyhue");
        remoteViews.setTextViewText(R.id.tv_message_custom, "Huyhue meesage");

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String strDate = sdf.format(new Date());
        remoteViews.setTextViewText(R.id.tv_time_custom, strDate);

        //expanded
        RemoteViews remoteViewsExpanded = new RemoteViews(getPackageName(), R.layout.layout_custom_notification_expandeed);
        remoteViewsExpanded.setTextViewText(R.id.tv_title_custom_expand, "Huyhue expand");
        remoteViewsExpanded.setTextViewText(R.id.tv_message_custom_expand, "Huyhue meesage expand");
        remoteViewsExpanded.setImageViewResource(R.id.img_custom_expand, R.drawable.ic_launcher_background);


        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_2)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setSound(sound)
                .setCustomContentView(remoteViews)
                .setCustomBigContentView(remoteViewsExpanded)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null){
            notificationManager.notify(getNotificationId(),notification);
        }
    }

    private void sendNotification() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setContentTitle(TITLE_PUSH_NOTIFICATION)
                .setContentText(CONTENT_PUSH_NOTIFICATION)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(CONTENT_PUSH_NOTIFICATION))
                .setSound(uri)
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .setLargeIcon(bitmap)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null){
            notificationManager.notify(getNotificationId(),notification);
        }
    }

    private void sendNotification2() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.anh1);
        Uri sound = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.nhac);

        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_2)
                .setContentTitle("This is the this time 2")
                .setContentText("Hello world in VietNam 2")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(bitmap)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap))
                .setSound(sound)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setColor(getResources().getColor(R.color.purple_200))
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null){
            notificationManager.notify(getNotificationId(),notification);
        }
    }

    private int getNotificationId() {
        return (int)new Date().getTime();
    }
}