package com.example.lenovo.notify2;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lenovo on 22-07-2016.
 */
public class Notification_RCV_Adapter extends RecyclerView.Adapter<Notification_RCV_Adapter.OurHolder> {
    Context mContext;
    ArrayList<NotificationContent> mdata;


    public Notification_RCV_Adapter(Context context , ArrayList<NotificationContent> data) {
        mContext = context;
        mdata = data;
    }

    public class OurHolder extends RecyclerView.ViewHolder{

        CardView card ;
        TextView name , location ;

        public OurHolder(View itemView) {
            super(itemView);
            card = (CardView)itemView.findViewById(R.id.cardview_item);
            name = (TextView)itemView.findViewById(R.id.textview_notification_name);
            location = (TextView)itemView.findViewById(R.id.textview_notification_place);
        }
    }

    @Override
    public Notification_RCV_Adapter.OurHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.listview_notifications_item,parent,false);

        return new OurHolder(v);
    }

    @Override
    public void onBindViewHolder(Notification_RCV_Adapter.OurHolder holder, int position) {
           NotificationContent c = mdata.get(position);
        holder.card.setBackgroundResource(R.color.colorSensorChange2);
        if(c.getItem_color_ID()==Constants.ColorID1){
            holder.card.setBackgroundResource(R.color.colorSensorChange2);
        }else if(c.getItem_color_ID()==Constants.ColorID2)
        {
            holder.card.setBackgroundResource(R.color.colorSensorChange1);
        }

        holder.name.setText(c.getNotifiaction_Name());
        holder.location.setText(c.getNotification_Place());


    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }
}
