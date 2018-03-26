package com.yashish.library.solveerror;

import android.util.Log;

/**
 * Created by Lenovo on 27-03-2018.
 */

public class SolveBug {

    private static Thread.UncaughtExceptionHandler androidDefaultUEH;
    private static Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable ex) {
            Log.e("SolveError",  ex + "");
            androidDefaultUEH.uncaughtException(thread, ex);
        }
    };


    public static void apply() {
        androidDefaultUEH = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(handler);
    }


}
