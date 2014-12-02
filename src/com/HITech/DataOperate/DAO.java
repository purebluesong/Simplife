package com.HITech.DataOperate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by Administrator on 2014/12/2.
 */
public class DAO {
    private SQLiteDatabase sqLiteDatabase;
    private DataOp dataop;
    private String[] allColumns={
            DataOp.COLUMN_ID,DataOp.COLUMN_COMMENT};
    public DAO(Context context ){
//        sqLiteDatabase =new SQLiteDatabase(context);
          dataop = new DataOp(context);
    }
    public void open()throws SQLException{
        sqLiteDatabase=dataop.getWritableDatabase();
    }
    public void close(){
        dataop.close();
    }
    public Comment createComment(String comment){
        ContentValues values = new ContentValues();
        values.put(DataOp.COLUMN_COMMENT,comment);
        long insertid= sqLiteDatabase.insert(DataOp.TABLE_COMMENTS,null,values);
        Cursor cursor = sqLiteDatabase.query(DataOp.TABLE_COMMENTS,allColumns,DataOp.COLUMN_ID+"="+insertid,null,null,null,null);
        cursor.moveToFirst();
        Comment newComment=cursorToComment(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteComment(Comment comment){
        long id = comment.getId();
        sqLiteDatabase.delete(DataOp.TABLE_COMMENTS,DataOp.COLUMN_ID+"="+id,null);
    }

    private Comment cursorToComment(Cursor cursor){
        Comment commen = new Comment();
        commen.setId(cursor.getLong(0));
        commen.setComment(cursor.getString(1));
        return commen;
    }
}
