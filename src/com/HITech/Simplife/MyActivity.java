package com.HITech.Simplife;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.HITech.DataOperate.DBManager;
import com.HITech.DataOperate.NoTimeEvents;

import java.util.List;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    Button button1;
    Button button2;
    private TextView mTextView;
    private DBManager mDBManager;
    private int mCurrentX=0,mCurrentY=0;//TextView左上角的像素位置
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button1=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new Mybuttonlistener() );
        mTextView = (TextView)findViewById(R.id.textView);
        mDBManager = new DBManager(this);
        NoTimeEvents noTimeEvents = new NoTimeEvents(12,"Test","contestsssstest");
        mDBManager.Insert(noTimeEvents);
        List<NoTimeEvents> noTimeEventses = mDBManager.query();
        for(NoTimeEvents noTimeEvents1 :noTimeEventses){
            mTextView.setText(noTimeEvents1.Name+"\t"+noTimeEvents1.Contents+"\n");
        }

    }
    private void button1click(View view){
//            button1.setBackgroundColor(Color.BLUE);
        Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show();

//            button2.setVisibility(View.GONE);

    }
    class Mybuttonlistener implements OnClickListener {
        @Override
        public void onClick(View v) {
            button2.setBackgroundColor(0x66ccff);
//            Toast.makeText( "Button FUCKed!", Toast.LENGTH_SHORT).show();
        }
    }


}
