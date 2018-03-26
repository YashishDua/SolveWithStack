package com.example.lenovo.todo;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements TODOListFragment.TODOListFragmentInterface , EditTaskFragment.EditTaskFragmentInterface{

    Button add_button;
    TODOListFragment tf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add_button = (Button) findViewById(R.id.button_add);
        tf  = (TODOListFragment) getFragmentManager().findFragmentById(R.id.fragment_todolist);
        tf.setBatchFragmentListener(this);


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TODOActivities", "Just Clicked Add");
                Intent i = new Intent();
                i.setClass(MainActivity.this, to_add_task.class);
                startActivityForResult(i, 1);
                Log.i("TODOActivities", "Jumped to next activity");

            }
        });
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(tf!=null) {
            tf.UpdateData(requestCode, resultCode, data);
        }

     /*   */
    }

    @Override
    public void itemClicked(TODO_Content c , int position) {
        FrameLayout fl = (FrameLayout) findViewById(R.id.TODOEditTaskFragmentContainer);
        if(fl==null){
            Intent i = new Intent();
            i.setClass(MainActivity.this, edit_task.class);
            i.putExtra("Default_task_name", c.getTODO_Task_Name());
            i.putExtra("Default_task_date", c.getTODO_Task_Date());
            Log.i("TODOActivities",position+"Position Data Entered to be deleted");
            i.putExtra("Default_position", position);
            i.putExtra("FrameLayout","null");
            startActivityForResult(i, 2);
        }
        else
        {
            EditTaskFragment sf = new EditTaskFragment();
            Bundle b1 = new Bundle();
            b1.putString("Default_task_name",c.getTODO_Task_Name());
            b1.putString("Default_task_date", c.getTODO_Task_Date());

            b1.putInt("Default_position", position);
            sf.setArguments(b1);
            getFragmentManager().beginTransaction().replace(R.id.TODOEditTaskFragmentContainer, sf).commit();
        }



    }
    public void setResults(Bundle b){
        tf.UpdateData_Bundle(b);
    }
}
