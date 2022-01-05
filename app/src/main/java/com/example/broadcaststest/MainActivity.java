package com.example.broadcaststest;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.broadcaststest.databinding.ActivityMainBinding;
import com.example.broadcaststest.util.custombroadcastreceivers.AirplaneModeBroadcastReceiver;
import com.example.broadcaststest.util.custombroadcastreceivers.BatteryStatusBroadcastReceiver;
import com.example.broadcaststest.util.custombroadcastreceivers.Constants;
import com.example.broadcaststest.util.custombroadcastreceivers.CustomBroadcastReceiver;
import com.example.broadcaststest.util.custombroadcastreceivers.PowerConnectionBroadcastReceiver;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private BatteryStatusBroadcastReceiver batteryStatusBroadcastReceiver;
    private AirplaneModeBroadcastReceiver airplaneModeBroadcastReceiver;
    private PowerConnectionBroadcastReceiver powerConnectionBroadcastReceiver;
    private CustomBroadcastReceiver customBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUI();
    }

    private void initUI() {
        binding.materialButton1.setOnClickListener(v -> sendBroadcast1());
        binding.materialButton2.setOnClickListener(v -> sendBroadcast2());
        binding.materialButton3.setOnClickListener(v -> sendBroadcast3());
    }

    //Normal broadcast
    private void sendBroadcast1() {
        Intent intent = new Intent(Constants.ACTION_SEND_TEXT1);
        intent.putExtra(Constants.BROADCASTTEST_TEXT_1, getString(R.string.received_text_1));
        sendBroadcast(intent);
    }

    //Broadcast with setPackage() method
    private void sendBroadcast2() {
        Intent intent = new Intent(Constants.ACTION_SEND_TEXT2);
        intent.putExtra(Constants.BROADCASTTEST_TEXT_2, getString(R.string.received_text_2));
        intent.setPackage(Constants.PACKAGE);
        sendBroadcast(intent);
    }

    //Broadcast with LocalBroadcastManager
    private void sendBroadcast3() {
        Intent intent = new Intent(Constants.ACTION_SEND_TEXT3);
        intent.putExtra(Constants.BROADCASTTEST_TEXT_3, getString(R.string.received_text_3));
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }


    @Override
    protected void onStart() {
        super.onStart();
        registerAllReceiver();
    }

    //Register all broadcast receivers
    private void registerAllReceiver() {
        initAirplaneBroadcastReceiver();
        initBatteryStatusBroadcastReceiver();
        initPowerConnectivityBroadcastReceiver();
        initCustomBroadcastReceiver();
        initCustomBroadcastReceiverForLocalBroadcast();
    }

    //Normal broadcast receiver
    private void initCustomBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter(Constants.ACTION_SEND_TEXT1);
        intentFilter.addAction(Constants.ACTION_SEND_TEXT2);
        customBroadcastReceiver = new CustomBroadcastReceiver();
        registerReceiver(customBroadcastReceiver, intentFilter);
    }

    //Broadcast receiver with LocalBroadcastManager
    private void initCustomBroadcastReceiverForLocalBroadcast() {
        IntentFilter intentFilter = new IntentFilter(Constants.ACTION_SEND_TEXT3);
        customBroadcastReceiver = new CustomBroadcastReceiver();
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(customBroadcastReceiver, intentFilter);
    }

    //Dynamic broadcast receiver for power connectivity
    private void initPowerConnectivityBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        powerConnectionBroadcastReceiver = new PowerConnectionBroadcastReceiver();
        registerReceiver(powerConnectionBroadcastReceiver, intentFilter);
    }

    //Dynamic broadcast receiver for battery status
    private void initBatteryStatusBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        batteryStatusBroadcastReceiver = new BatteryStatusBroadcastReceiver();
        registerReceiver(batteryStatusBroadcastReceiver, intentFilter);
    }

    //Dynamic broadcast receiver for airplane mode
    private void initAirplaneBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        airplaneModeBroadcastReceiver = new AirplaneModeBroadcastReceiver();
        registerReceiver(airplaneModeBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterAllReceiver();
    }

    //Unregister all broadcast receivers
    private void unregisterAllReceiver() {
        unregisterReceiver(airplaneModeBroadcastReceiver);
        unregisterReceiver(batteryStatusBroadcastReceiver);
        unregisterReceiver(powerConnectionBroadcastReceiver);
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(customBroadcastReceiver);
    }
}