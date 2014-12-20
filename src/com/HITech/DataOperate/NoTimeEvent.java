package com.HITech.DataOperate;

/**
 * Created by Administrator on 2014/12/18.
 */
public class NoTimeEvent {

    public int _id;//主键
    public String Name;//一个用于简短显示的字段
    public String Contents;//实际的输入事件
    public boolean Completed;//判断任务是否完成
    public int StartTime;//记录开始任务的时间，为后面的任务分析与日志记录做准备
    public int EndTime;//记录完成时间

    public NoTimeEvent(){}

    public NoTimeEvent(String Name, String Contents, int buildtime){
        this.Name           =   Name;
        this.Contents       =   Contents;
        this.StartTime =   buildtime;
        EndTime = -1;
        Completed=false;
    }
}
