package com.HITech.DataOperate;

/**
 * Created by Administrator on 2014/12/2.
 */
public class Comment {
    private long id;
    private String comment;
    public Comment(){}
    public void setId(long id){
        this.id=id;
    }

    public long getId(){
        return id;
    }

    public void setComment(String s){
        this.comment=s;
    }

    public String getComment(){
        return comment;
    }
    @Override
    public String toString(){
        return comment;
    }
}
