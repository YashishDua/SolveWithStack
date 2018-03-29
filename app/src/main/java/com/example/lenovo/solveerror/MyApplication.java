package com.example.lenovo.solveerror;

import android.app.Application;

import com.yashish.library.solvewithstack.SolveWithStack;

/**
 * Created by Lenovo on 26-03-2018.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SolveWithStack.apply(getApplicationContext());

    }
}
