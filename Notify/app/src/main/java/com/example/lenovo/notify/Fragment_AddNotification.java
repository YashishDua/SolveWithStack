package com.example.lenovo.notify;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Lenovo on 14-07-2016.
 */
public class Fragment_AddNotification extends Fragment {

    NotificationContent object;

    Fragment_AddNotificationListener mListener;
    public interface Fragment_AddNotificationListener {
        void NotificationAdded(NotificationContent object);
    }

    public void setFragment_AddNotificationListener(Fragment_AddNotificationListener listener) {
        mListener = listener;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_addnotification , container , false);
        EditText notification_message = (EditText)v.findViewById(R.id.edittext_notification_message);
        EditText notification_location = (EditText)v.findViewById(R.id.edittext_notification_location_name);

        object = new NotificationContent(String.valueOf(notification_message.getText()) ,String.valueOf( notification_location.getText()));
        return v;
    }
}
