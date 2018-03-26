package com.example.lenovo.notify2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    public AlarmReceiver() {
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
       /* PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context(context.ALARM_SERVICE);

        alarmManager.cancel(sender);*/
        String ID = intent.getStringExtra("ID");
        Log.i("----","Alarm Repeating !...");

        Log.i("----","Sending Local Broadcast...");
        Intent i = new Intent("LocalNotify");
        i.putExtra("ID",ID);
        context.sendBroadcast(i);


    }
}
