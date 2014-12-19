package com.HITech.DataOperate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2014/11/22.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "simplifeDB.db";
    private static final int DATABASE_VERSION = 1;
    //为了统一变量名设置这些静态字符串
    public static String QuickTableName         = "QUICKEVENTTABLE";
    public static String NoTimeTableName        = "NOTIMEEVENTTABLE";
    public static String PlanTableName          = "PLANEVENTTABLE";
    public static String sId                    = "_id";
    public static String sName                  = "name";
    public static String sComplete              = "complete";
    public static String sContents              = "contents";
    public static String sStartTime             = "starttime";
    public static String sEndTime               = "endtime";
    public static String sClassify              = "classify";
    public static String sFrequent              = "frequent";
    public static String sImportant             = "important";
    public static String sIsGoodPlan            = "isgoodplan";

    public DBHelper(Context context) {
        //CursorFactory设置为null,使用默认值
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //数据库第一次被创建时onCreate会被调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS "+
                NoTimeTableName +
                "("+
                sId+"         INTEGER       PRIMARY KEY AUTOINCREMENT, "+
                sComplete+"   INTEGER, " +
                sContents+"   VARCHAR(50)   NOT NULL, "+
                sEndTime+"    INTEGER, "+
                sName+"       VARCHAR(10), "+
                sStartTime+"  INTEGER" +
                ")"
        );
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS "+
                QuickTableName+
                "("+
                sId+"           INTEGER     PRIMARY KEY AUTOINCREMENT, "+
                sClassify+"     INTEGER, " +
                sComplete+"     INTEGER, " +
                sContents+"     VARCHAR(200)    NOT NULL, " +
                sEndTime+"      INTEGER, " +
                sImportant+"    INTEGER" +
                ")"
        );
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS "+
                PlanTableName +
                "("+
                sId+"           INTEGER     PRIMARY KEY AUTOINCREMENT," +
                sClassify+"     INTEGER, " +
                sComplete+"     INTEGER, " +
                sContents+"     VARCHAR(200)    NOT NULL, " +
                sEndTime+"      INTEGER, " +
                sFrequent+"     INTEGER, " +
                sImportant+"    INTEGER, " +
                sIsGoodPlan+"   INTEGER, " +
                sStartTime+"    INTEGER"+
                ")"
        );
    }

    //如果DATABASE_VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE "+ NoTimeTableName +" ADD COLUMN other STRING");
        db.execSQL("ALTER TABLE "+ QuickTableName  +" ADD COLUMN other STRING");
        db.execSQL("ALTER TABLE "+ PlanTableName   +" ADD COLUMN other STRING");
    }

}
