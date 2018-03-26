package com.example.lenovo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       /* View v = LayoutInflater.from(this).inflate(R.layout.align_toolbar_listview ,  , false);

        toolbar.set*/

        Toolbar right_toolbar = (Toolbar)findViewById(R.id.toolbar2);

        View v = getLayoutInflater().inflate(R.layout.align_toolbar_listview , null);
        right_toolbar.addView(v);

    }
}
