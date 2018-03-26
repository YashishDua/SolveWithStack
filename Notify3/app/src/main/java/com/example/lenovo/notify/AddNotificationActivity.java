package com.example.lenovo.notify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddNotificationActivity extends AppCompatActivity {

    Button getlocation_button , add_button ;
    EditText location_edittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notification);
        getlocation_button = (Button)findViewById(R.id.button);
        add_button = (Button) findViewById(R.id.submitbutton);
        location_edittext = (EditText)findViewById(R.id.edittext);


    }
}
