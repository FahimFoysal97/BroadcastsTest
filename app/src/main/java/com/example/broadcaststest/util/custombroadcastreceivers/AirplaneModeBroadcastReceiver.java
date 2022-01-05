package com.example.broadcaststest.util.custombroadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

import com.example.broadcaststest.R;

public class AirplaneModeBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
            if (isAirplaneModeOn(context))
                Toast.makeText(context, R.string.airplanemode_is_on, Toast.LENGTH_SHORT).show();
            else Toast.makeText(context, R.string.airplanemode_is_off, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isAirplaneModeOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
    }
}
