package com.HITech.Back;

import android.app.AlarmManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.provider.AlarmClock;

/**
 * Created by Administrator on 2014/12/21.
 */
public class TimeService extends Service {

    private AlarmClock alarmClock;
    private AlarmManager alarmManager;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        alarmClock.getClass();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
