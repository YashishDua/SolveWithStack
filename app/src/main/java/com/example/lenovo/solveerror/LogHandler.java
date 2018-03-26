package com.example.lenovo.solveerror;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.Arrays;

/**
 * Created by Lenovo on 26-03-2018.
 */

public class LogHandler implements Thread.UncaughtExceptionHandler {
    private final static String TAG = LogHandler.class.getSimpleName();


    private final Activity context;

    LogHandler(Activity context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void uncaughtException(final Thread thread, final Throwable ex) {
        try {

            ex.printStackTrace();
            Log.e(TAG, ex.getLocalizedMessage());
            Log.e(TAG, ex.getMessage());
            Log.e(TAG, ex.getCause()+"");
            Log.e(TAG, Arrays.toString(ex.getStackTrace()) );
            Log.e(TAG, String.valueOf(ex.getSuppressed()));
            Log.e(TAG, String.valueOf(ex));

            context.finish();

        } catch (Exception e) {
            Log.e(TAG,  e + "");
        }

    }
}
