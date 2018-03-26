package com.example.lenovo.notify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 14-07-2016.
 */
public class Notification_Listview_Adapter extends ArrayAdapter<NotificationContent> {

    Context context;
    ArrayList<NotificationContent> data ;
    public Notification_Listview_Adapter(Context context, ArrayList<NotificationContent> objects) {
        super(context, 0, objects);
        this.context = context;
        data = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        v = LayoutInflater.from(context).inflate(R.layout.listview_notifications_item , parent ,false);
        TextView name = (TextView)v.findViewById(R.id.textview_notification_name);
        TextView location = (TextView)v.findViewById(R.id.textview_notification_place);
        name.setText(data.get(position).getNotifiaction_Name());
        location.setText(data.get(position).getNotification_Place());

        return v;
    }
}
