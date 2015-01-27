package com.HITech.DataOperate;

/**
 * Created by Administrator on 2014/12/18.
 */
public class QuickTimeEvent {
    public int _id;
    public int endtime;
    public int classify;
    public int important;
    public String contents;
    public boolean completed;

    public QuickTimeEvent(){}

    public QuickTimeEvent(int endtime,int classify,int important,String contents){
        this.classify   =   classify;
        this.endtime    =   endtime;
        this.important  =   important;
        this.contents   =   contents;
        this.completed  =   false;
    }
}
