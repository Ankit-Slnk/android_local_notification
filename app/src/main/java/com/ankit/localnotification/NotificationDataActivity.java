package com.ankit.localnotification;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationDataActivity extends AppCompatActivity {

    private static final String TAG = "NotificationData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_data);

        if (getIntent() != null) {
            if (getIntent().getExtras() != null) { // background notification handle
                if (getIntent().getExtras().get("id") != null) {
                    Log.e(TAG, getIntent().getExtras().get("id") + "");
                }
                if (getIntent().getExtras().get("name") != null) {
                    Log.e(TAG, getIntent().getExtras().get("name") + "");
                }
            } else { // foreground notification handle
                if (getIntent().getExtras().getString("id") != null) {
                    Log.e(TAG, getIntent().getExtras().getString("id"));
                }
                if (getIntent().getExtras().getString("name") != null) {
                    Log.e(TAG, getIntent().getExtras().getString("name"));
                }
            }
        }
    }
}