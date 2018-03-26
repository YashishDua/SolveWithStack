package com.example.lenovo.todo;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class TODOListFragment extends Fragment {

    ListView TODO_listview ;

    Button add_button;
    HashMap<Integer,TODO_Content> map;
    TODO_Content_Adapter adapter;
    TODOListFragmentInterface mListener;

    public TODOListFragment() {
        // Required empty public constructor
    }

    public interface TODOListFragmentInterface{
         void itemClicked(TODO_Content c , int position);
    }


    public void setBatchFragmentListener(TODOListFragmentInterface listener) {
        mListener = listener;
    }

    public void UpdateData(int requestCode , int resultCode , Intent data){
        if (requestCode==1){
            if (resultCode==1){
                int currentnumberofview = map.size();


                map.put(currentnumberofview , new TODO_Content(data.getStringExtra("Task_Name"),data.getStringExtra("Task_Date")));
                Log.i("TODOActivities",currentnumberofview+""+map.size());

                for(int i =0 ; i<map.size();i++){
                    for(int j=i+1 ; j<map.size();j++){
                        if((map.get(i).getTODO_Task_Date()).compareTo(map.get(j).
                                getTODO_Task_Date())>0){
                            TODO_Content t = map.get(j);
                            map.put(j,map.get(i));
                            map.put(i,t);

                        }
                    }
                }
                adapter.notifyDataSetChanged();
                Log.i("TODOActivities","Data Set Changed");

            }
        }


        else if(requestCode==2){
            if(resultCode==1){

                map.put((data.getIntExtra("Saved_position",0)),new TODO_Content(data.getStringExtra("New_task_name"),data.getStringExtra("New_task_date")));
                adapter.notifyDataSetChanged();
            }
            else if(resultCode==2){
                Log.i("TODOActivities","Delete Function entered");
                Log.i("TODOActivities",data.getIntExtra("Delete_position",0) +"Position Data Recieved to delete");
                map.remove(data.getIntExtra("Delete_position",0));
                int i=0;
                for( int a : map.keySet()){
                    HashMap<Integer,TODO_Content> tempmap = new HashMap<>();
                    tempmap.put(i++,map.get(a));
                    map.clear();
                    map.putAll(tempmap);


                }
                Log.i("TODOActivities","Map Data removed");

                Log.i("TODOActivities",map.size()+"");
                adapter.notifyDataSetChanged();
                Log.i("TODOActivities","Adapter Notified");
            }
        }
    }

    public void UpdateData_Bundle (Bundle b){

         if(b.get("requestCode")==2){
            if(b.get("resultCode")==1){

                map.put((b.getInt("Saved_position",0)),new TODO_Content(b.getString("New_task_name"),b.getString("New_task_date")));
                adapter.notifyDataSetChanged();
            }
            else if(b.get("resultCode")==2){
                Log.i("TODOActivities","Delete Function entered");
                Log.i("TODOActivities",b.getInt("Delete_position",0) +"Position Data Recieved to delete");
                map.remove(b.getInt("Delete_position",0));
                int i=0;
                for( int a : map.keySet()){
                    HashMap<Integer,TODO_Content> tempmap = new HashMap<>();
                    tempmap.put(i++,map.get(a));
                    map.clear();
                    map.putAll(tempmap);


                }
                Log.i("TODOActivities","Map Data removed");

                Log.i("TODOActivities",map.size()+"");
                adapter.notifyDataSetChanged();
                Log.i("TODOActivities","Adapter Notified");
            }
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_todolist, container, false);

        TODO_listview = (ListView) v.findViewById(R.id.listview);
        add_button = (Button) v.findViewById(R.id.button_add);
        map = new HashMap<>();
        //map.put(0, new TODO_Content("Task1","Date1"));
        //map.put(1, new TODO_Content("Task2","Date2"));

        Log.i("TODOActivities", "Before Adapter");
        adapter = new TODO_Content_Adapter(getActivity(), map);
        Log.i("TODOEActivities", "Adpater Initialised");
        TODO_listview.setAdapter(adapter);

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mListener.itemClicked((TODO_Content) parent.getAdapter().getItem(position), position);

             }


        };

       TODO_listview.setOnItemClickListener(listener);



    return v;
    }

}
