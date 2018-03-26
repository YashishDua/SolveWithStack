package com.example.lenovo.notify2;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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
        if(v==null)
        v = LayoutInflater.from(context).inflate(R.layout.listview_notifications_item , parent ,false);
        CardView card = (CardView)v.findViewById(R.id.cardview_item) ;
//        v.setBackgroundColor(Color.parseColor(String.valueOf(R.color.colorAccent)));

        card.setBackgroundResource(R.color.colorSensorChange2);

        NotificationContent object = data.get(position);
        if(object.getItem_color_ID()==Constants.ColorID1){
            card.setBackgroundResource(R.color.colorSensorChange2);
        }else if(object.getItem_color_ID()==Constants.ColorID2)
        {
            card.setBackgroundResource(R.color.colorSensorChange1);
        }
        TextView name = (TextView)v.findViewById(R.id.textview_notification_name);
        TextView location = (TextView)v.findViewById(R.id.textview_notification_place);
        name.setText(object.getNotifiaction_Name());
        location.setText(object.getNotification_Place());

        return v;
    }
}
