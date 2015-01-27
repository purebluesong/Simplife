package com.HITech.DataOperate;

/**
 * Created by Administrator on 2014/12/18.
 */
public class PlanTimeEvent {
    public int _id;
    public int endtime;
    public int starttime;
    public int classify;
    public int important;
    public int frequent;
    public int isplangood;
    public String contents;
    public boolean completed;

    public PlanTimeEvent(){}

    public PlanTimeEvent(int starttime,int endtime,int classify,int important,String contents,int frequent){
        this.classify   =   classify;
        this.endtime    =   endtime;
        this.important  =   important;
        this.contents   =   contents;
        this.starttime  =   starttime;
        this.frequent   =   frequent;
        this.endtime    =   -1;
        this.completed  =   false;
    }
}
