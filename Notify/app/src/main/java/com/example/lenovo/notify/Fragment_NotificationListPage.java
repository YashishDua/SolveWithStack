package com.example.lenovo.notify;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Lenovo on 14-07-2016.
 */
public class Fragment_NotificationListPage extends Fragment {

    ArrayList<NotificationContent> data;
    Fragment_NotificationListPageListner mListener;
    ListView mNotificationListView;
    Notification_Listview_Adapter adapter;

    public interface Fragment_NotificationListPageListner {
        void NotificationClicked(NotificationContent object);
    }

    public void setBatchFragmentListener(Fragment_NotificationListPageListner listener) {
        mListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notificationlistpage, container, false);
        mNotificationListView = (ListView)v.findViewById(R.id.listview_Notifications);

        data = new ArrayList<>();
        data.add(new NotificationContent("name" , "place"));

        adapter = new Notification_Listview_Adapter(getActivity() , data);
        mNotificationListView.setAdapter(adapter);

        return v;

    }

    public void setData(NotificationContent object){
        data.add(object);
        adapter.notifyDataSetChanged();
    }
}
