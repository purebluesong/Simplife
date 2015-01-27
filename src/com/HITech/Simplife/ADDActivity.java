package com.HITech.Simplife;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.HITech.DataOperate.DBManager;
import com.HITech.DataOperate.QuickTimeEvent;

/**
 * Created by Administrator on 2015/1/9.
 */
public class ADDActivity extends Activity {
    private Button btnAdd;
    private Button btnCancel;
    private EditText editTextContents;
    private EditText editTextEndtime;
    private EditText editTextClassify;
    private EditText editTextImportant;
    private DBManager dbManager;
    private AddButtonListener addButtonListener ;

    public ADDActivity(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        btnAdd              =(Button)findViewById(R.id.btnAddAdd);
        btnCancel           =(Button)findViewById(R.id.btnCancelAdd);
        editTextContents    =(EditText)findViewById(R.id.editContentsAdd);
        editTextEndtime     =(EditText)findViewById(R.id.editEndtimeAdd);
        editTextClassify    =(EditText)findViewById(R.id.editClassifyAdd);
        editTextImportant   =(EditText)findViewById(R.id.editImportant);
        addButtonListener = new AddButtonListener();
        btnAdd.setOnClickListener(addButtonListener);
        btnCancel.setOnClickListener(addButtonListener);
        dbManager           = new DBManager(this);
    }


    private class AddButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnAddAdd:
                    if(isInputRight()){
                        addQuickEvent();
                    }else{
                        Toast.makeText(getApplicationContext(), editTextEndtime.getText().toString(),Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btnCancelAdd:
                    setContentView(R.layout.main);
                    break;
                default:
            }

        }
    }

    private boolean isInputRight(){
        boolean answer = false;
        try {
            if (editTextContents.getText().length() > 0 && editTextContents.getText().length() < 51 &&
                    Byte.parseByte(editTextImportant.getText().toString())>0 && Byte.parseByte(editTextImportant.getText().toString())<5
                    && Byte.parseByte(editTextClassify.getText().toString())>0 && Byte.parseByte(editTextClassify.getText().toString())<30)
                answer = true;
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(),
                    Toast.LENGTH_SHORT).show();
        }
        return answer;
    }

    private boolean addQuickEvent(){
        QuickTimeEvent quickTimeEvent = new QuickTimeEvent(
                Integer.parseInt(editTextEndtime.getText().toString()),
                Integer.parseInt(editTextClassify.getText().toString()),
                Integer.parseInt(editTextImportant.getText().toString()),
                editTextContents.getText().toString()
        );
        return dbManager.InsertToQTE(quickTimeEvent);
    }
}
