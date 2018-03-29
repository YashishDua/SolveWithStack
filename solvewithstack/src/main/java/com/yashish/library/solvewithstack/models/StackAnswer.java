package com.yashish.library.solvewithstack.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Lenovo on 28-03-2018.
 */

public class StackAnswer {

    @SerializedName("items")
    private ArrayList<Items> items;

    public ArrayList<Items> getItems() {
        return items;
    }

    public class Items {
        @SerializedName("link")
        String link;

        public String getLink() {
            return link;
        }
    }
}
