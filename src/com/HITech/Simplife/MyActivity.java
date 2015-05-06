package com.HITech.Simplife;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import com.HITech.DataOperate.DatabaseTest;
import com.HITech.GlobalResource;
import com.HITech.Views.TimeAx;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private Button button2;
    private DatabaseTest databaseTest;
    private TextView mTextView;
    private TimeAx timeAx;
    private TabHost tabHost;
    private ADDActivity addActivity;
    private RadioGroup radioGroup;

    private Mybuttonlistener buttonListener;
    private int CurrentX=0,CurrentY=0;//TextView左上角的像素位置
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initialScreen();
        timeAx = new TimeAx(this);
        buttonListener = new Mybuttonlistener();
        mTextView.setText("music music music music music music m ");
        regeisterTouchListener();
    }
    @Override
    public void onStart(){
        super.onStart();
    }
    class Mybuttonlistener implements OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {

            }
        }

    }

    private void initialScreen(){
        GlobalResource.ScreenWidth=getScreenWidth();
        button2=(Button)findViewById(R.id.main_button2);
        tabHost = (TabHost)findViewById(R.id.main_tabHost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("tab1").setContent(R.id.main_tab_button1));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("tab2").setContent(R.id.main_tab_button2));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("tab3").setContent(R.id.main_tab_button3));
        radioGroup = (RadioGroup)findViewById(R.id.main_tab_radiogroup);
        mTextView = (TextView)findViewById(R.id.textView);
    }

    private void regeisterTouchListener(){
     //   button2.setOnClickListener(buttonListener);
    }

    public int getScreenWidth(){
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;     // 屏幕宽度（像素）
    }
}
