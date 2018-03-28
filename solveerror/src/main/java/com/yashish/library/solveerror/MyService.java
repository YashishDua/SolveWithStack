package com.yashish.library.solveerror;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.yashish.library.solveerror.models.APIService;
import com.yashish.library.solveerror.models.Client;
import com.yashish.library.solveerror.models.StackAnswer;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lenovo on 28-03-2018.
 */

public class MyService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("SolveError", "onHandleIntent: ");
        APIService service = Client.getService();
        Call<StackAnswer> call = service.getAnswer("Android","stackoverflow");
        call.enqueue(new Callback<StackAnswer>() {
            @Override
            public void onResponse(Call<StackAnswer> call, Response<StackAnswer> response) {
                Log.e("SolveError", "onResponse: " + response.code());
                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        Log.e("SolveError", "onResponse: YAyayayaya");
                        JSONObject jsonObject = null;

                            //jsonObject = new JSONObject(response.errorBody().toString());
                            Log.e("SolveError", "onResponse: " + response.errorBody().toString());
                        /* catch (JSONException e) {
                            Log.e("SolveError", "onResponse: " + e.getMessage());
                        }*/
                    }
                    if (response.isSuccessful()) {
                        StackAnswer stackAnswer = response.body();
                        Log.e("SolveError", "onResponse: " + stackAnswer.getItems().get(0).getLink());
                    } else
                        Log.e("SolveError", "No response !!" + response.message());
                }
            }

            @Override
            public void onFailure(Call<StackAnswer> call, Throwable t) {
                Log.e("SolveError", "onFailure: ");
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
