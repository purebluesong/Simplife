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
    private Button button1;
    private Button button2;
    private Button tabButton1;
    private Button tabButton2;
    private DatabaseTest databaseTest;
    private TextView mTextView;
    private TimeAx timeAx;
//    private ADDActivity addActivity;

    private Mybuttonlistener buttonListener;
    private int CurrentX=0,CurrentY=0;//TextView左上角的像素位置
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initialScreen();
        timeAx = new TimeAx(this);
        buttonListener = new Mybuttonlistener();
        databaseTest = new DatabaseTest(this);
        mTextView.setText(databaseTest.Answer());
        regeisterTouchListener();
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
                case R.id.main_tab_button_1:
                    break;
                case R.id.main_button2:
                    break;
                case R.id.main_tab_button_2:
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), ADDActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }

    }

    private void initialScreen(){
        GlobalResource.ScreenWidth=getScreenWidth();
        button2=(Button)findViewById(R.id.main_button2);
        tabButton1=(Button)findViewById(R.id.main_tab_button_1);
        tabButton2=(Button)findViewById(R.id.main_tab_button_2);
        mTextView = (TextView)findViewById(R.id.textView);
    }

    private void regeisterTouchListener(){
        button2.setOnClickListener(buttonListener);
        tabButton1.setOnClickListener(buttonListener);
    }

    public int getScreenWidth(){
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;     // 屏幕宽度（像素）
    }
}
