package com.example.broadcaststest.util.custombroadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Constants.ACTION_SEND_TEXT1)) {
            String text = intent.getStringExtra(Constants.BROADCASTTEST_TEXT_1);
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        }
        if (intent.getAction().equals(Constants.ACTION_SEND_TEXT2)) {
            String text = intent.getStringExtra(Constants.BROADCASTTEST_TEXT_2);
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        }
        if (intent.getAction().equals(Constants.ACTION_SEND_TEXT3)) {
            String text = intent.getStringExtra(Constants.BROADCASTTEST_TEXT_3);
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        }
    }
}
