package com.HITech.DataOperate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/12/18.
 */
public class DBManager {

    private DBHelper helper;
    private SQLiteDatabase db;

    private static String sSelectAllInDB = "SELECT * FROM ";
    private static String sWhereInDB     = " WHERE ";

    public DBManager(Context context) {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }
     /**
     * add noTimeEventses
     * @param noTimeEvent
     * @return boolean //false means insert failed ,true means success
     */
    public boolean InsertToNTE(NoTimeEvent noTimeEvent){//NTE 意思是No Time Event
        if(IsNTEhave(noTimeEvent))return false;
        //这些是插入操作
        ContentValues contentValues = new ContentValues();
        contentValues.put(helper.sComplete, noTimeEvent.Completed);
        contentValues.put(helper.sContents, noTimeEvent.Contents);
        contentValues.put(helper.sEndTime,  noTimeEvent.EndTime);
        contentValues.put(helper.sName,     noTimeEvent.Name);
        contentValues.put(helper.sStartTime,noTimeEvent.StartTime);
        db.insert(helper.NoTimeTableName, helper.sName,contentValues);
        return true;
    }
    /**
    * add QuickTimeEvent
    * @param quickTimeEvent
    * @return boolean //false means insert failed ,true means success
    */
    public void InsertToQTE(QuickTimeEvent quickTimeEvent){//QTE:quick time event
        if(IsQTEhave(quickTimeEvent))return;

        ContentValues contentValues = new ContentValues();
        contentValues.put(helper.sClassify, quickTimeEvent.classify);
        contentValues.put(helper.sComplete, quickTimeEvent.completed);
        contentValues.put(helper.sContents, quickTimeEvent.contents);
        contentValues.put(helper.sEndTime,  quickTimeEvent.endtime);
        contentValues.put(helper.sImportant,quickTimeEvent.important);
        db.insert(helper.QuickTableName, helper.sContents,contentValues);
    }
    /**
     * add QuickTimeEvent
     * @param planTimeEvent
     * @return boolean //false means insert failed ,true means success
     */
    public void InsertToPTE(PlanTimeEvent planTimeEvent){//PTE:plan time event
        if (IsPTEhave(planTimeEvent))return;

        ContentValues contentValues = new ContentValues();
        contentValues.put(helper.sClassify, planTimeEvent.classify);
        contentValues.put(helper.sComplete, planTimeEvent.completed);
        contentValues.put(helper.sContents, planTimeEvent.contents);
        contentValues.put(helper.sEndTime,  planTimeEvent.endtime);
        contentValues.put(helper.sFrequent, planTimeEvent.frequent);
        contentValues.put(helper.sImportant,planTimeEvent.important);
        contentValues.put(helper.sIsGoodPlan,planTimeEvent.isplangood);
        contentValues.put(helper.sStartTime,planTimeEvent.starttime);
        db.insert(helper.PlanTableName, helper.sContents,contentValues);
    }
    /**
     * update noTimeEvent's name
     * @param noTimeEvent
     */
    public void updateNTEById(NoTimeEvent noTimeEvent) {
        ContentValues cv = new ContentValues();
        cv.put(helper.sName,        noTimeEvent.Name);
        cv.put(helper.sContents,    noTimeEvent.Contents);
        cv.put(helper.sComplete,    ConvertBoolToInt(noTimeEvent.Completed));
        cv.put(helper.sStartTime,   noTimeEvent.StartTime);
        cv.put(helper.sEndTime,     noTimeEvent.EndTime);
        db.update(helper.NoTimeTableName, cv, helper.sId+" = ?", new String[]{String.valueOf(noTimeEvent._id)});
    }
    /**
     * update quicktimeevent
     * @param quickTimeEvent
    * */
    public void updateQTEById(QuickTimeEvent quickTimeEvent){
        ContentValues cv = new ContentValues();
        cv.put(helper.sContents,    quickTimeEvent.contents);
        cv.put(helper.sClassify,    quickTimeEvent.classify);
        cv.put(helper.sImportant,   quickTimeEvent.important);
        cv.put(helper.sComplete,    ConvertBoolToInt(quickTimeEvent.completed));
        cv.put(helper.sEndTime,     quickTimeEvent.endtime);
        db.update(helper.QuickTableName, cv, helper.sId+" = ?", new String[]{String.valueOf(quickTimeEvent._id)});
    }
    /**
     * update plantimeevent
     * @param planTimeEvent
     * */
    public void updatePTEById(PlanTimeEvent planTimeEvent){
        ContentValues cv = new ContentValues();
        cv.put(helper.sStartTime,   planTimeEvent.starttime);
        cv.put(helper.sEndTime,     planTimeEvent.endtime);
        cv.put(helper.sContents,    planTimeEvent.contents);
        cv.put(helper.sClassify,    planTimeEvent.classify);
        cv.put(helper.sImportant,   planTimeEvent.important);
        cv.put(helper.sIsGoodPlan,  planTimeEvent.isplangood);
        cv.put(helper.sFrequent,    planTimeEvent.frequent);
        cv.put(helper.sComplete,    ConvertBoolToInt(planTimeEvent.completed));
        db.update(helper.PlanTableName, cv, helper.sId+" = ?", new String[]{String.valueOf(planTimeEvent._id)});
    }
    /**
     * delete old noTimeEvent
     * @param noTimeEvent
     */
    public void deleteNTEById(NoTimeEvent noTimeEvent) {
        db.delete(helper.NoTimeTableName, "_id = ?", new String[]{String.valueOf(noTimeEvent._id)});
    }
    /**
     * delete qte
     * @param quickTimeEvent
     * */
    public void deleteQTEById(QuickTimeEvent quickTimeEvent){
        db.delete(helper.QuickTableName, "_id = ?", new String[]{String.valueOf(quickTimeEvent._id)});
    }
    /**
     * delete PTE
     * @param planTimeEvent
     * */
    public void deletePTEById(PlanTimeEvent planTimeEvent){
        db.delete(helper.PlanTableName,"_id = ?",new String[]{String.valueOf(planTimeEvent._id)});
    }
    /**
     * query all noTimeEvents, return list
     * @return List<noTimeEvent>
     */
    public List<NoTimeEvent> queryNTE() {
        ArrayList<NoTimeEvent> noTimeEvents = new ArrayList<NoTimeEvent>();
        Cursor c = queryTheNTECursor();
        while (c.moveToNext()) {
            NoTimeEvent noTimeEvent = new NoTimeEvent();
            noTimeEvent._id =               c.getInt(c.getColumnIndex(helper.sId));
            noTimeEvent.Name =              c.getString(c.getColumnIndex(helper.sName));
            noTimeEvent.Contents =          c.getString(c.getColumnIndex(helper.sContents));
            noTimeEvent.Completed =         ConvertIntToBool(c.getInt(c.getColumnIndex(helper.sComplete)));
            noTimeEvent.StartTime =         c.getInt(c.getColumnIndex(helper.sStartTime));
            noTimeEvent.EndTime =           c.getInt(c.getColumnIndex(helper.sEndTime));
            noTimeEvents.add(noTimeEvent);
        }
        c.close();
        return noTimeEvents;
    }
    /**
     * query all quick time event ,return list
     * @return List<quicktimeevent>
     * */
    public List<QuickTimeEvent> queryQTE(){
        ArrayList<QuickTimeEvent> quickTimeEvents = new ArrayList<QuickTimeEvent>();
        Cursor cursor = queryTheQTECursor();
        while (cursor.moveToNext()){
            QuickTimeEvent quickTimeEvent = new QuickTimeEvent();
            quickTimeEvent._id      =   cursor.getInt(cursor.getColumnIndex(helper.sId));
            quickTimeEvent.endtime  =   cursor.getInt(cursor.getColumnIndex(helper.sEndTime));
            quickTimeEvent.important=   cursor.getInt(cursor.getColumnIndex(helper.sImportant));
            quickTimeEvent.classify =   cursor.getInt(cursor.getColumnIndex(helper.sClassify));
            quickTimeEvent.contents =   cursor.getString(cursor.getColumnIndex(helper.sContents));
            quickTimeEvent.completed=   ConvertIntToBool(cursor.getInt(cursor.getColumnIndex(helper.sComplete)));
            quickTimeEvents.add(quickTimeEvent);
        }
        return quickTimeEvents;
    }
    /**
     * query all Plan Time Event
     * @return List<PlanTimeEvent>
     * */
    public List<PlanTimeEvent> queryPTE(){
        ArrayList<PlanTimeEvent> planTimeEvents = new ArrayList<PlanTimeEvent>();
        Cursor cursor = queryThePTECursor();
        while(cursor.moveToNext()){
            PlanTimeEvent planTimeEvent = new PlanTimeEvent();
            planTimeEvent._id       =   cursor.getInt(cursor.getColumnIndex(helper.sId));
            planTimeEvent.starttime =   cursor.getInt(cursor.getColumnIndex(helper.sStartTime));
            planTimeEvent.endtime   =   cursor.getInt(cursor.getColumnIndex(helper.sEndTime));
            planTimeEvent.important =   cursor.getInt(cursor.getColumnIndex(helper.sImportant));
            planTimeEvent.classify  =   cursor.getInt(cursor.getColumnIndex(helper.sClassify));
            planTimeEvent.contents  =   cursor.getString(cursor.getColumnIndex(helper.sContents));
            planTimeEvent.completed =   ConvertIntToBool(cursor.getInt(cursor.getColumnIndex(helper.sComplete)));
            planTimeEvent.isplangood=   cursor.getInt(cursor.getInt(cursor.getColumnIndex(helper.sIsGoodPlan)));
            planTimeEvents.add(planTimeEvent);
        }
        return planTimeEvents;
    }

    /**
     * query all noTimeEvents, return cursor
     * @return  Cursor
     */
    public Cursor queryTheNTECursor() {
        Cursor c = db.rawQuery("SELECT * FROM "+helper.NoTimeTableName,null);
        return c;
    }
    /**
     * query all quicktimeEvents,return cursor
     * @return  Cursor
     * */
    public Cursor queryTheQTECursor(){
        Cursor c = db.rawQuery("SELECT * FROM "+helper.QuickTableName,null);
        return c;
    }
    /**
     * query all plantimeevents return cursor
     * @return Cursor
     * */
    public Cursor queryThePTECursor(){
        Cursor c = db.rawQuery("SELECT * FROM "+helper.PlanTableName,null);
        return c;
    }
    /**
     * close database
     */
    public void closeDB() {
        db.close();
    }
    //以下是工具系列
    private boolean ConvertIntToBool(int num){
        if (0==num)return false;
        else return true;
    }

    private int ConvertBoolToInt(boolean bool){
        if(bool)return 1;
        else return 0;
    }

    private boolean IsNTEhave(NoTimeEvent noTimeEvent){
        boolean flag=false;
        Cursor c = db.rawQuery(sSelectAllInDB+helper.NoTimeTableName+sWhereInDB+helper.sContents+"=? and "+helper.sStartTime+"=?",
                new String[]{noTimeEvent.Contents,String.valueOf(noTimeEvent.StartTime)});
        flag=c.moveToFirst();
        return flag;
    }

    private boolean IsQTEhave(QuickTimeEvent quickTimeEvent){
        boolean flag=false;
        Cursor cursor = db.rawQuery(sSelectAllInDB+helper.QuickTableName+sWhereInDB+helper.sContents+"=? and "+helper.sEndTime+"=?",
                new String[]{quickTimeEvent.contents,String.valueOf(quickTimeEvent.endtime)}
                );
        flag=cursor.moveToFirst();
        return flag;
    }

    private boolean IsPTEhave(PlanTimeEvent planTimeEvent){
        boolean flag=false;
        Cursor cursor = db.rawQuery(sSelectAllInDB+helper.PlanTableName+sWhereInDB+helper.sContents+"=? and "+helper.sStartTime+"=?",
                new String[]{planTimeEvent.contents,String.valueOf(planTimeEvent.starttime)}
                );
        flag=cursor.moveToFirst();
        return flag;
    }
}
