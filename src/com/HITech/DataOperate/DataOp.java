package com.HITech.DataOperate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2014/11/22.
 */
public class DataOp extends SQLiteOpenHelper {
    SQLiteDatabase sqLiteDatabase;
    public static final String TABLE_COMMENTS="comments";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COMMENT = "comment";
    private static final String DATABASE_NAME="";
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_CREATE="create table "+TABLE_COMMENTS+"("+COLUMN_ID+" integer primary key autoincrement,"+COLUMN_COMMENT+" text not null";
    public DataOp(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int oldversion,int newversion){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_COMMENTS);
        onCreate(sqLiteDatabase);
    }

}
