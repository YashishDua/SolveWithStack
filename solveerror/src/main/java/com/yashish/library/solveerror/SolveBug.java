package com.yashish.library.solveerror;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Lenovo on 27-03-2018.
 */

public class SolveBug {

    static Context context;

    private static Thread.UncaughtExceptionHandler androidDefaultUEH;
    private static Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(final Thread thread, final Throwable ex) {

            Log.e("SolveError", "uncaughtException: ");
            Intent intent = new Intent(context, MyService.class);
            context.startService(intent);
            Log.e("SolveError", "uncaughtException: Started service");
            androidDefaultUEH.uncaughtException(thread, ex);
        }
    };


    public static void apply(Context c) {
        context = c;
        androidDefaultUEH = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(handler);
    }


}
