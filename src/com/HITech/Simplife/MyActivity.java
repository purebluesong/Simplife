package com.HITech.Simplife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.HITech.DataOperate.DatabaseTest;
import com.HITech.GlobalResource;
import com.HITech.Views.TimeAx;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    Button button1;
    Button button2;
    private DatabaseTest databaseTest;
    private TextView mTextView;
    private TimeAx timeAx;
//    private ADDActivity addActivity;

    private Mybuttonlistener mButtonListener;
    private int mCurrentX=0,mCurrentY=0;//TextView左上角的像素位置
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mButtonListener = new Mybuttonlistener();
        GlobalResource.ScreenWidth=getScreenWidth();

    //    button1=(Button)findViewById(R.id.main_button1);
        button2=(Button)findViewById(R.id.main_button2);
        timeAx = new TimeAx(this);

      //  button1.setOnClickListener(mButtonListener);
        button2.setOnClickListener(mButtonListener);
        mTextView = (TextView)findViewById(R.id.textView);
        databaseTest = new DatabaseTest(this);
        mTextView.setText(databaseTest.Answer());

    }
    @Override
    public void onStart(){
        super.onStart();
        mTextView.setText(databaseTest.AddAnswer());
    }
    class Mybuttonlistener implements OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
             //
                case R.id.main_button2:
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), ADDActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    }

    public int getScreenWidth(){
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;     // 屏幕宽度（像素）
    }
}
