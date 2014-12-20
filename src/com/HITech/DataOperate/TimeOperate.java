package com.HITech.DataOperate;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2014/12/20.
 */
public class TimeOperate {
    private Date date;
    private Calendar calendar;
    private DateFormat dateFormat;
    private Calendar calendarTemp;

    public TimeOperate(){
        date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
    }

    public Date getNowDate(){               return date;                        }
    public String GetNowTime(){             return dateFormat.format(date);     }
    public int getYear(){                   return calendar.YEAR;               }
    public int getMonth(){                  return calendar.MONTH;              }
    public int getDayInMonth(){             return calendar.DAY_OF_MONTH;       }
    public int getToday(){                  return calendar.DAY_OF_YEAR;        }
    public int getHoursInToday(){           return calendar.HOUR_OF_DAY;        }
    public int getMinuteInHours(){          return calendar.MINUTE;             }

    public boolean isToday(long time){
        calendarTemp.setTimeInMillis(time);
        if (calendarTemp.YEAR==calendar.YEAR && calendarTemp.DAY_OF_YEAR==calendar.DAY_OF_YEAR){
            calendarTemp=null;
            return true;
        }else    return false;
    }

    public boolean isNow(long time){
        return time/60000==date.getTime()/60000;
    }

    public Date ConvertLongToDate(long time){
        Date date1=new Date();
        date1.setTime(time);
        return date1;
    }

    public long ConvertDateToInt(Date date1){
        long time;
        time=date1.getTime();
        return time;
    }

    public void RefreshTime(){
        date.setTime(System.currentTimeMillis());
        calendar.setTime(date);
    }
}
