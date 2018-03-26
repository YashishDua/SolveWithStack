package com.example.lenovo.gcmdemo;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class GCMRegistrationService extends IntentService {

    public GCMRegistrationService() {
        super("GCMRegistrationService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        String token = getSharedPreferences("token",MODE_PRIVATE).getString("gcm",null);

        InstanceID id  = InstanceID.getInstance(this);

        try {
            token = id.getToken("147822381651", GoogleCloudMessaging.INSTANCE_ID_SCOPE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("----",token);

        if(token!=null){
            SharedPreferences.Editor sp = getSharedPreferences("token",MODE_PRIVATE).edit();
            sp.putString("token",token);
            sp.commit();
            subscribetoTopics(token);

        }

    }

    public void subscribetoTopics(String token){
        GcmPubSub pubSub = GcmPubSub.getInstance(this);
        try {
            pubSub.subscribe(token , "/topics/" + "global",null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
