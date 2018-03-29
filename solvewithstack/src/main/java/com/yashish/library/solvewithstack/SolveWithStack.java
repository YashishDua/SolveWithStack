package com.yashish.library.solvewithstack;

import android.content.Context;
import android.content.Intent;

import com.yashish.library.solvewithstack.services.MyService;

/**
 * Created by Lenovo on 27-03-2018.
 */

public class SolveWithStack {

    private static Context context;

    private static Thread.UncaughtExceptionHandler androidDefaultUEH;
    private static Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(final Thread thread, final Throwable ex) {

            String errorCause = ex.getMessage().substring(ex.getMessage().indexOf(":") + 2);
            errorCause = errorCause.substring(0,errorCause.indexOf(":"));
            Intent intent = new Intent(context, MyService.class);
            intent.putExtra(Constants.INTENT_CAUSE_PASS,errorCause);
            context.startService(intent);
            androidDefaultUEH.uncaughtException(thread, ex);
        }
    };


    public static void apply(Context c) {
        context = c;
        androidDefaultUEH = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(handler);
    }


}
