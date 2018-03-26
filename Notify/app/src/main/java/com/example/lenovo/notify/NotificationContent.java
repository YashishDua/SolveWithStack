package com.example.lenovo.notify;

/**
 * Created by Lenovo on 14-07-2016.
 */
public class NotificationContent {

    String Notifiaction_Name ;
    String Notification_Place;

    public NotificationContent(String notifiaction_Name, String notification_Place) {
        Notifiaction_Name = notifiaction_Name;
        Notification_Place = notification_Place;
    }

    public String getNotifiaction_Name() {
        return Notifiaction_Name;
    }

    public void setNotifiaction_Name(String notifiaction_Name) {
        Notifiaction_Name = notifiaction_Name;
    }

    public String getNotification_Place() {
        return Notification_Place;
    }

    public void setNotification_Place(String notification_Place) {
        Notification_Place = notification_Place;
    }
}
