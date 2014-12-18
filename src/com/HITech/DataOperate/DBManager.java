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
    public String Tablename ;
    public DBManager(Context context) {
        helper = new DBHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
        Tablename = "NOTIMEEVENTTABLE";
    }

    public DBManager(Context context,int table){
        helper = new DBHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
        switch (table%3+1){
            case 1:
                Tablename="NOTIMEEVENTTABLE";
                break;
            default:
                break;
        }

    }

    /**
     * add noTimeEventses
     * @param noTimeEventses
     */
    public void Insert(List<NoTimeEvents> noTimeEventses) {
        db.beginTransaction();  //开始事务
        try {
            for (NoTimeEvents noTimeEvent : noTimeEventses) {
                db.execSQL("INSERT INTO "+Tablename+" VALUES(null, ?, ?)", new Object[]{noTimeEvent.Name, noTimeEvent.Contents});
            }
            db.setTransactionSuccessful();  //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
    }
    /**
     * add noTimeEventses
     * @param noTimeEventses
     */
    public void Insert(NoTimeEvents noTimeEvent){
        db.beginTransaction();
        try {
            db.execSQL("INSERT INTO "+Tablename+" VALUES(null, ?, ?)", new Object[]{noTimeEvent.Name, noTimeEvent.Contents});
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

    /**
     * update noTimeEvent's name
     * @param noTimeEvent
     */
    public void updatenoTimeEventById(NoTimeEvents noTimeEvent) {
        ContentValues cv = new ContentValues();
        cv.put("contents", noTimeEvent.Contents);
        db.update(Tablename, cv, "_id = ?",new String[]{String.valueOf(noTimeEvent._id)} );
    }

    /**
     * delete old noTimeEvent
     * @param noTimeEvent
     */
    public void deleteOldnoTimeEventById(NoTimeEvents noTimeEvent) {
        db.delete(Tablename, "_id = ?", new String[]{String.valueOf(noTimeEvent._id)});
    }

    /**
     * query all noTimeEvents, return list
     * @return List<noTimeEvent>
     */
    public List<NoTimeEvents> query() {
        ArrayList<NoTimeEvents> noTimeEvents = new ArrayList<NoTimeEvents>();
        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            NoTimeEvents noTimeEvent = new NoTimeEvents();
            noTimeEvent._id = c.getInt(c.getColumnIndex("_id"));
            noTimeEvent.Name = c.getString(c.getColumnIndex("name"));
            noTimeEvent.Contents = c.getString(c.getColumnIndex("contents"));
            noTimeEvents.add(noTimeEvent);
        }
        c.close();
        return noTimeEvents;
    }

    /**
     * query all noTimeEvents, return cursor
     * @return  Cursor
     */
    public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT * FROM "+Tablename, null);
        return c;
    }

    /**
     * close database
     */
    public void closeDB() {
        db.close();
    }
}
