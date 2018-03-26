package com.example.lenovo.notify2;

import android.app.ActionBar;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AlaramOpen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alaram_open);
        String MESSAGE = getIntent().getStringExtra("MESSAGE");
        TextView text = (TextView)findViewById(R.id.textview_alarmopen);
        text.setText(MESSAGE);

 //       ActionBar bar = getActionBar();
     //   ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FF4081"));
// //       bar.setBackgroundDrawable(colorDrawable);


    }
}
