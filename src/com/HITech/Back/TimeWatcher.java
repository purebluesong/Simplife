package com.HITech.Back;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.HITech.DataOperate.TimeOperate;

import java.util.Date;

/**
 * Created by Administrator on 2014/12/21.
 */
public class TimeWatcher {
    /**
     * todo:complete all class
     * */
    private TimeOperate timeOperate  = new TimeOperate();
    private Date date = new Date();
    private AlarmManager alarmManager;
    public TimeWatcher(){
//            alarmManager = new AlarmManager(this);
    }

    public boolean RegisterAOnceAlarmClock(long time,Context context){
        if(time<System.currentTimeMillis())return false;
        Intent intent = new Intent("SIMPLIFE_CLOCK");
        intent.putExtra("msg","FUCK IT");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);
        AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,time,pendingIntent);
        return true;
    }
}
