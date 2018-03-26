package com.example.lenovo.notify2;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Lenovo on 14-07-2016.
 */
//@Table(name="NotificationContents")
public class NotificationContent /*extends Model*/ {
   // @Column(name= "name")
    String Notifiaction_Name ;

    //@Column(name= "name")
    String Notification_Place;
    //@Column(name= "latitude")
    String Notification_Latitude;
    //@Column(name= "longitude")
    String Notification_Longitude;
    //@Column(name= "time")
    String Alarm_Initial_Time ;
    //@Column(name= "date")
    String Alarm_Date ;
    //@Column(name= "alarm_ID")
    int Notification_Alarm_ID;
    //@Column(name= "color_ID")
    int Item_color_ID ;

    public NotificationContent(String notifiaction_Name, String notification_Place, String notification_Latitude, String notification_Longitude, String alarm_Initial_Time, String alarm_Date, int notification_Alarm_ID, int item_color_ID) {
        Notifiaction_Name = notifiaction_Name;
        Notification_Place = notification_Place;
        Notification_Latitude = notification_Latitude;
        Notification_Longitude = notification_Longitude;
        Alarm_Initial_Time = alarm_Initial_Time;
        Alarm_Date = alarm_Date;
        Notification_Alarm_ID = notification_Alarm_ID;
        Item_color_ID = item_color_ID;
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

    public String getNotification_Latitude() {
        return Notification_Latitude;
    }

    public void setNotification_Latitude(String notification_Latitude) {
        Notification_Latitude = notification_Latitude;
    }

    public String getNotification_Longitude() {
        return Notification_Longitude;
    }

    public void setNotification_Longitude(String notification_Longitude) {
        Notification_Longitude = notification_Longitude;
    }

    public String getAlarm_Initial_Time() {
        return Alarm_Initial_Time;
    }

    public void setAlarm_Initial_Time(String alarm_Initial_Time) {
        Alarm_Initial_Time = alarm_Initial_Time;
    }

    public String getAlarm_Date() {
        return Alarm_Date;
    }

    public void setAlarm_Date(String alarm_Date) {
        Alarm_Date = alarm_Date;
    }

    public int getNotification_Alarm_ID() {
        return Notification_Alarm_ID;
    }

    public void setNotification_Alarm_ID(int notification_Alarm_ID) {
        Notification_Alarm_ID = notification_Alarm_ID;
    }

    public int getItem_color_ID() {
        return Item_color_ID;
    }

    public void setItem_color_ID(int item_color_ID) {
        Item_color_ID = item_color_ID;
    }
}
