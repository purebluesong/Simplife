package com.HITech.DataOperate;

import android.content.Context;

import java.util.List;

/**
 * Created by Administrator on 2014/12/19.
 * @version 1
 * @author zhangRp
 *  As a test class to database and as a example for database operate
 */
public class DatabaseTest {
    private DBManager mDBManager;
    private NoTimeEvent noTimeEvent;
    private QuickTimeEvent quickTimeEvent;
    private PlanTimeEvent  planTimeEvent;
    private String answer;
    private List<NoTimeEvent> noTimeEvents;
    private List<QuickTimeEvent> quickTimeEvents;
    private List<PlanTimeEvent> planTimeEvents;

    public DatabaseTest(Context context){
        mDBManager = new DBManager(context);
        //增加操作
        noTimeEvent = new NoTimeEvent("NTE","NTEtest",1010);
        quickTimeEvent = new QuickTimeEvent(1,2,3,"QTETEST");
        planTimeEvent = new PlanTimeEvent(5,6,7,7,"PTETest",8);

        mDBManager.InsertToNTE(noTimeEvent);
        mDBManager.InsertToQTE(quickTimeEvent);
        mDBManager.InsertToPTE(planTimeEvent);

        noTimeEvent = new NoTimeEvent("NTE2","NTEtest2",1010);
        quickTimeEvent = new QuickTimeEvent(1,2,3,"QTETEST2");
        planTimeEvent = new PlanTimeEvent(5,6,7,7,"PTETest2",8);

        mDBManager.InsertToNTE(noTimeEvent);
        mDBManager.InsertToQTE(quickTimeEvent);
        mDBManager.InsertToPTE(planTimeEvent);

        answer ="";
        answer+="QueryAll:\n";
        answer+=AddAnswer();
        answer+="this Update Test,Dont panic";

        //java 里面貌似想要做些修改操作也只能用这种方式了，暂时想不出更好的方法
        noTimeEvent = noTimeEvents.get(0);
        quickTimeEvent = quickTimeEvents.get(0);
        planTimeEvent = planTimeEvents.get(0);

        noTimeEvent.Completed=true;
        noTimeEvent.Contents="This filed has been changed";
        quickTimeEvent.completed=true;
        quickTimeEvent.contents="This filed has been changed";
        planTimeEvent.completed=true;
        planTimeEvent.contents="This filed has been changed";

        mDBManager.updateNTEById(noTimeEvent);
        mDBManager.updateQTEById(quickTimeEvent);
        mDBManager.updatePTEById(planTimeEvent);

        answer+=AddAnswer();
        //删除操作
        answer+="This is delete test:\n";
        noTimeEvent = noTimeEvents.get(0);
        quickTimeEvent = quickTimeEvents.get(0);
        planTimeEvent = planTimeEvents.get(0);

        mDBManager.deleteNTEById(noTimeEvent);
        mDBManager.deleteQTEById(quickTimeEvent);
        mDBManager.deletePTEById(planTimeEvent);

        answer+=AddAnswer();
    }

    public String AddAnswer(){
        String Queryanswer = "";
        noTimeEvents = mDBManager.queryNTE();
        quickTimeEvents     = mDBManager.queryQTE();
        planTimeEvents      = mDBManager.queryPTE();
        for(NoTimeEvent noTimeEvents1 : noTimeEvents){
            Queryanswer+=noTimeEvents1._id+"\t"+
                    noTimeEvents1.Name+"\t"+
                    noTimeEvents1.StartTime +"\t"+
                    noTimeEvents1.Completed+"\t"+
                    noTimeEvents1.Contents+"\t"+
                    noTimeEvents1.EndTime +"\n";
        }
        for(QuickTimeEvent quickTimeEvent1 : quickTimeEvents){
            Queryanswer+=quickTimeEvent1._id+"\t"+
                    quickTimeEvent1.contents+"\t"+
                    quickTimeEvent1.completed+"\t"+
                    quickTimeEvent1.classify+"\t"+
                    quickTimeEvent1.endtime+"\t"+
                    quickTimeEvent1.important+"\n";
        }
        for(PlanTimeEvent planTimeEvent1 :planTimeEvents){
            Queryanswer+=planTimeEvent1._id+"\t"+
                    planTimeEvent1.isplangood+"\t"+
                    planTimeEvent1.frequent+"\t"+
                    planTimeEvent1.contents+"\t"+
                    planTimeEvent1.completed+"\t"+
                    planTimeEvent1.classify+"\t"+
                    planTimeEvent1.starttime+"\t"+
                    planTimeEvent1.endtime+"\t"+
                    planTimeEvent1.important+"\n";
        }
        return Queryanswer;
    }
    public String Answer(){
        return answer;
    }
}
