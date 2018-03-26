package com.example.lenovo.solveerror;

import android.app.Application;

import com.yashish.library.solveerror.SolveBug;

/**
 * Created by Lenovo on 26-03-2018.
 */

public class MyApplication extends Application {

/*
    private Thread.UncaughtExceptionHandler androidDefaultUEH;

    private Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable ex) {
            Log.e("SolveError",  ex + "");
            androidDefaultUEH.uncaughtException(thread, ex);
        }
    };
*/

    @Override
    public void onCreate() {
        super.onCreate();
        //new LogHandler(this);
        SolveBug.apply();

        /*androidDefaultUEH = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(handler);*/
    }
}
