package com.example.siddhant.movie;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ABC on 28-06-2016.
 */
public class imageAdapter extends BaseAdapter {
    Context mycontext;
    ArrayList<MovieItem>myarr;
    public imageAdapter(Context context, ArrayList<MovieItem> objects) {

        myarr=objects;
        mycontext=context;
    }

    @Override
    public int getCount() {
        return myarr.size();
    }

    @Override
    public Object getItem(int position) {
        return myarr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        if(v==null)
        {
            v= LayoutInflater.from(mycontext).inflate(R.layout.image_layout,parent,false);
        }
        ImageView image=(ImageView)v.findViewById(R.id.image1);

        image.setImageBitmap(myarr.get(position).getB());


        return v;
    }
}
