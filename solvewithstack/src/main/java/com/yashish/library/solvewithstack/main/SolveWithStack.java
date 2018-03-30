package com.yashish.library.solvewithstack.main;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.yashish.library.solvewithstack.services.StackAPIBackgroundService;
import com.yashish.library.solvewithstack.utils.Constants;

/**
 * Created by Lenovo on 27-03-2018.
 */

public class SolveWithStack {

    private Context mContext;

    private Thread.UncaughtExceptionHandler androidDefaultUEH;
    private Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(final Thread thread, final Throwable ex) {

            String errorCause = ex.getMessage().substring(ex.getMessage().indexOf(":") + 2);
            errorCause = errorCause.substring(0,errorCause.indexOf(":"));
            Log.e(Constants.LIBRARY_NAME,"Filtered Error: " + errorCause);
            Intent intent = new Intent(mContext, StackAPIBackgroundService.class);
            intent.putExtra(Constants.INTENT_CAUSE_PASS,errorCause);
            mContext.startService(intent);
            androidDefaultUEH.uncaughtException(thread, ex);

        }
    };

    public static void apply(Context c){
        SolveWithStack solveWithStack = new SolveWithStack();
        solveWithStack.implement(c);
    }

    private void implement(Context c) {
        this.mContext = c;
        androidDefaultUEH = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(handler);
    }


}
