package com.ankit.localnotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    public static final String APP_ACTION = "com.ankit.localnotification";
    private final String MEDIA_CHANNEL_ID = "com.ankit.localnotification.order.placed.channel";
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();

        // The id of the channel.
        String id = MEDIA_CHANNEL_ID;
        // The user-visible name of the channel.
        CharSequence name = "Order Place";
        // The user-visible description of the channel.
        String description = "Order Place";
        int importance;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            importance = NotificationManager.IMPORTANCE_LOW;
        } else {
            importance = NotificationManager.IMPORTANCE_DEFAULT;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(id, name, importance);
            // Configure the notification channel.
            mChannel.setDescription(description);
            mChannel.setShowBadge(false);
            mChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(mChannel);
        }
    }

    public void onTap1Data(View view) {
        Intent intent = new Intent(MainActivity.this, NotificationDataActivity.class);
        Bundle extras = new Bundle();
        extras.putString("id", "12345");
        intent.setAction(APP_ACTION);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtras(extras);
        // PENDING INTENT REQUESTCODE MUST BE UNIQUE
        buildNotification(PendingIntent.getActivity(MainActivity.this, 1, intent, Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ? PendingIntent.FLAG_MUTABLE : PendingIntent.FLAG_ONE_SHOT),
                "Local Notification",
                "1 data");
    }

    public void onTap2Data(View view) {
        Intent intent = new Intent(MainActivity.this, NotificationDataActivity.class);
        Bundle extras = new Bundle();
        extras.putString("id", "1234567890");
        extras.putString("name", "John Doe");
        intent.setAction(APP_ACTION);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtras(extras);
        // PENDING INTENT REQUESTCODE MUST BE UNIQUE
        buildNotification(PendingIntent.getActivity(MainActivity.this, 2, intent, Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ? PendingIntent.FLAG_MUTABLE : PendingIntent.FLAG_ONE_SHOT),
                "Local Notification",
                "2 data");
    }

    private void buildNotification(PendingIntent pendingIntent, String title, String subTitle) {
        // Create a new Notification
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this, MEDIA_CHANNEL_ID)
                .setTicker(getResources().getString(R.string.app_name))
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(subTitle)
                .setPriority(Notification.PRIORITY_HIGH)
                .setAutoCancel(true);

        if (pendingIntent != null) {
            notificationBuilder.setContentIntent(pendingIntent);
        }

        notificationManager.notify(12345, notificationBuilder.build());
    }
}