package com.example.lenovo.todo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lenovo on 23-06-2016.
 */
public class TODO_Content_Adapter extends BaseAdapter {


    HashMap<Integer,TODO_Content> map;
    Context context;
    TextView TODO_TaskName_TextView;
    TextView TODO_TaskDate_TextView;
   /* public static final int TASK_DESCRIPTION_VIEW = 0;
    public static final int TASK_HEAD_DATE_VIEW = 1;*/


    public TODO_Content_Adapter(Context context , HashMap<Integer,TODO_Content>objects) {
        Log.i("TODOActivities","Entered Adapator");
        this.context = context ;
        this.map = objects ;

    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

   /* @Override
    public int getItemViewType(int position) {

      int count = 0;
        ArrayList <TODO_Content> temparr = new ArrayList<>();
        for(String s : this.map.keySet()){
            if(position==0){
                return TASK_HEAD_DATE_VIEW;
            }

            temparr = map.get(s);
            for(TODO_Content T : temparr){
                count++;
                if(count==position)
                    return TASK_DESCRIPTION_VIEW;
            }
            count++;

       }
        return 2;
   }
*/
    @Override
    public int getCount() {
        return map.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(this.context).inflate(R.layout.todo_taskview, null);
        }
        TODO_Content CurrentTask = this.map.get(position);
        Log.i("TODOActivities","Object gets postioned at"+ position);
        Log.i("TODOActivities","Do map contain value ?"+ map.containsKey(position));

        // map is not refreshing

        TextView TODO_TaskName_TextView = (TextView) v.findViewById(R.id.textview_taskname);
        TextView TODO_TaskDate_TextView = (TextView) v.findViewById(R.id.textview_taskdate);

        TODO_TaskName_TextView.setText(CurrentTask.getTODO_Task_Name());
        
        TODO_TaskDate_TextView.setText(CurrentTask.getTODO_Task_Date());


        return v;


    }
}
