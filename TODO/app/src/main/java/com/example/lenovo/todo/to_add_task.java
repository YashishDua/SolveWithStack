package com.example.lenovo.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class to_add_task extends AppCompatActivity {

    EditText TASK_NAME;
    EditText TASK_DATE;
    Button add_button ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_add_task);
        TASK_NAME = (EditText)findViewById(R.id.editText);
        TASK_DATE = (EditText)findViewById(R.id.editText2);
        add_button = (Button) findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("Task_Name",TASK_NAME.getText().toString());
                i.putExtra("Task_Date",TASK_DATE.getText().toString());
                setResult(1,i);
                finish();
            }
        });
     }
}
