package com.HITech.Simplife;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.HITech.DataOperate.DatabaseTest;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    Button button1;
    Button button2;
    private DatabaseTest databaseTest;
    private TextView mTextView;
    private int mCurrentX=0,mCurrentY=0;//TextView左上角的像素位置
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button1=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new Mybuttonlistener() );
        mTextView = (TextView)findViewById(R.id.textView);
        databaseTest = new DatabaseTest(this);
        mTextView.setText(databaseTest.Answer());
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
