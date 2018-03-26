package com.example.lenovo.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class edit_task extends AppCompatActivity /*implements*/ /*EditTaskFragment.EditTaskFragmentInterface*/ {

    EditText Default_task_name ;
    EditText Default_task_date ;
    Button save_button ;
    Button delete_button ;
    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
      //  EditTaskFragment ef = (EditTaskFragment) getFragmentManager().findFragmentById(R.id.fragment_todolist);
       // ef.setTODOTaskFragmentListener(this);
       /* Default_task_name = (EditText) findViewById(R.id.editText_taskname_edit_task_Activity);
        Default_task_date = (EditText) findViewById(R.id.editText_taskdate_edit_task_Activity);
        save_button = (Button) findViewById(R.id.save_button_edit_task_Activity);
        delete_button = (Button) findViewById(R.id.delete_button_edit_task_Activity);
        Default_task_name.setText(getIntent().getStringExtra("Default_task_name"));
        Default_task_date.setText(getIntent().getStringExtra("Default_task_date"));
        //final String Default_position = getIntent().getStringExtra("Default_position");

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = getIntent().getIntExtra("Default_position",0);
                Intent i = getIntent();
                i.putExtra("New_task_name",Default_task_name.getText().toString());
                i.putExtra("New_task_date",Default_task_date.getText().toString());
                i.putExtra("Saved_position",a);
                setResult(1,i);
                finish();
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = getIntent().getIntExtra("Default_position",0);
                getIntent().putExtra("Delete_position",a);
                setResult(2,getIntent());
                finish();
            }
        });*/
    }
}
