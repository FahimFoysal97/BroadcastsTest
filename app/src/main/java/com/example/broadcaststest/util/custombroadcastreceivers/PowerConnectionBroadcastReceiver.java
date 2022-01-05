package com.example.broadcaststest.util.custombroadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.broadcaststest.R;

public class PowerConnectionBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED))
            Toast.makeText(context, R.string.power_connected, Toast.LENGTH_SHORT).show();
        else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED))
            Toast.makeText(context, R.string.power_disconnected, Toast.LENGTH_SHORT).show();

    }
}
