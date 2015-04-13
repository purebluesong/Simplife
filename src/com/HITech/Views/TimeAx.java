package com.HITech.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2015/2/4.
 */
public class TimeAx extends View {
    private Point pointLeftUp;
    private Point pointRightDown;
    private Paint paint = new Paint();
    private Point pointCircleCenter;
    private float radius;

    public TimeAx(Context context){
        super(context);
    }

    public TimeAx(Context context,Point pointLeftUp,Point pointRightDown){
        super(context);
        this.pointLeftUp=pointLeftUp;
        this.pointRightDown=pointRightDown;
        pointCircleCenter.x=(pointLeftUp.x+pointRightDown.y)/2;
        pointCircleCenter.y=(pointLeftUp.y+pointRightDown.y)/2;
    }

    public TimeAx(Context context,AttributeSet attributeSet){
        super(context,attributeSet);
    }



    public TimeAx(Context context,AttributeSet attributeSet,Point pointLeftUp,Point pointRightDown){
        super(context,attributeSet);
        this.pointLeftUp=pointLeftUp;
        this.pointRightDown=pointRightDown;
        pointCircleCenter.x=(pointLeftUp.x+pointRightDown.y)/2;
        pointCircleCenter.y=(pointLeftUp.y+pointRightDown.y)/2;
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(pointCircleCenter.x,pointCircleCenter.y,radius,paint);
        paint.setColor(Color.GREEN);
        canvas.drawText("Already for time scroll",pointCircleCenter.x+20,pointCircleCenter.y+10,paint);
    }

}
