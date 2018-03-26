package com.example.lenovo.todo;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditTaskFragment extends Fragment {

    EditText Default_task_name ;
    EditText Default_task_date ;
    Button save_button ;
    Button delete_button ;
    int a;
    EditTaskFragmentInterface mListener;

    public EditTaskFragment() {
        // Required empty public constructor
    }
    public interface EditTaskFragmentInterface{
        void setResults(Bundle b);
    }


    public void setTODOTaskFragmentListener(EditTaskFragmentInterface listener) {
        mListener = listener;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v =  inflater.inflate(R.layout.fragment_edit_task, container, false);
        Default_task_name = (EditText) v.findViewById(R.id.editText_taskname_edit_task_Activity);
        Default_task_date = (EditText) v.findViewById(R.id.editText_taskdate_edit_task_Activity);
        save_button = (Button) v.findViewById(R.id.save_button_edit_task_Activity);
        delete_button = (Button) v.findViewById(R.id.delete_button_edit_task_Activity);

       // Intent i = new Intent();
        final Bundle b = getArguments();
        if(b!=null){
            a = b.getInt("Default_position",-1);
        }
        Default_task_name.setText(b.getString("Default_task_name"));
        Default_task_date.setText(b.getString("Default_task_date"));
        //final String Default_position = getIntent().getStringExtra("Default_position");


        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b.putString("New_task_name",Default_task_name.getText().toString());
                b.putString("New_task_date",Default_task_date.getText().toString());
                b.putInt("Saved_position",a);
                b.putInt("Request_code",2);
                b.putInt("Result_code",1);
                mListener.setResults(b);

            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.putInt("Delete_position",a);
                b.putInt("Request_code",2);

                b.putInt("Result_code",2);
                mListener.setResults(b);


                // setResult(2,getIntent());
               // finish();
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

}
