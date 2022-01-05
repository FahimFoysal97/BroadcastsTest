package com.example.broadcaststest.util.custombroadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

import com.example.broadcaststest.R;

public class BatteryStatusBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED))
            Toast.makeText(context, getBatteryStatus(context, intent), Toast.LENGTH_SHORT).show();
    }

    private String getBatteryStatus(Context context, Intent intent) {
        // Charging / charged?
        if (isCharged(intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1))) {
            return context.getString(R.string.battery_charged);
        } else if (isCharging(intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1))) {
            // How are we charging?
            if (isPluggedUSB(intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)))//USB Plug
                return context.getString(R.string.usb_plugged);
            else if (isPluggedAC(intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)))//AC Plug
                return context.getString(R.string.ac_plugged);
            else return context.getString(R.string.plug_unknown); //Plug Unknown
        } else {
            return context.getString(R.string.battery) + " " + intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) + "%";//Battery level
        }
    }

    private boolean isCharging(int status) {
        return status == BatteryManager.BATTERY_STATUS_CHARGING;
    }

    private boolean isCharged(int status) {
        return status == BatteryManager.BATTERY_STATUS_FULL;
    }

    private boolean isPluggedUSB(int chargePlug) {
        return chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
    }

    private boolean isPluggedAC(int chargePlug) {
        return chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
    }
}
