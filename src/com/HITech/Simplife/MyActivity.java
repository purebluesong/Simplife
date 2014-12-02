package com.HITech.Simplife;

import android.app.Activity;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    Button button1;
    Button button2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button1=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new Mybuttonlistener() );
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
