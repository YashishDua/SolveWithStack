package com.yashish.library.solvewithstack.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.yashish.library.solvewithstack.utils.Constants;
import com.yashish.library.solvewithstack.models.StackAnswer;
import com.yashish.library.solvewithstack.network.APIService;
import com.yashish.library.solvewithstack.network.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lenovo on 28-03-2018.
 */

public class StackAPIBackgroundService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String errorCause = intent.getStringExtra(Constants.INTENT_CAUSE_PASS);
        errorCause = errorCause.replace(" ", "%20");
        APIService service = Client.getService();
        Call<StackAnswer> call = service.getAnswer(errorCause);
        call.enqueue(new Callback<StackAnswer>() {
            @Override
            public void onResponse(Call<StackAnswer> call, Response<StackAnswer> response) {

                    if (response.isSuccessful()) {
                        StackAnswer stackAnswer = response.body();
                        if(stackAnswer.getItems().size()!=0)
                        Log.e(Constants.LIBRARY_NAME, "Possible Solution " + stackAnswer.getItems().get(0).getLink());
                        return;
                    }

                    Log.e(Constants.LIBRARY_NAME, "Couldn't find right now");

            }

            @Override
            public void onFailure(Call<StackAnswer> call, Throwable t) {

            }

          });

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
