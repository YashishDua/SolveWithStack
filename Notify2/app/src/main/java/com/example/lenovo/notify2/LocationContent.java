package com.example.lenovo.notify2;

import java.util.ArrayList;

/**
 * Created by Lenovo on 21-07-2016.
 */
public class LocationContent {

        String Location_Name ;
        String Location_latlon ;

        public LocationContent(String location_Name, String location_latlon) {
            Location_Name = location_Name;
            Location_latlon = location_latlon;
        }

        public String getLocation_Name() {
            return Location_Name;
        }

        public void setLocation_Name(String location_Name) {
            Location_Name = location_Name;
        }

        public String getLocation_latlon() {
            return Location_latlon;
        }

        public void setLocation_latlon(String location_latlon) {
            Location_latlon = location_latlon;
        }
    }
