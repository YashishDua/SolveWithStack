package com.example.lenovo.latlongtest;

import com.google.android.gms.appdatasearch.GetRecentContextCall;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lenovo on 14-07-2016.
 */
public class Service {

    private static API_Interface service;

    public static API_Interface getService() {

        if (service == null) {

            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(45, TimeUnit.SECONDS)

                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request r = chain.request();
                            r = r.newBuilder().addHeader("key", "AIzaSyAxeQVw4cQkWUMKQUiBomDH4IS1o0qh8Pk")
                                    .build();
                            return chain.proceed(r);
                        }
                    })
                    .build();

            Retrofit r = new Retrofit.Builder().baseUrl(
                    "https://maps.googleapis.com/maps/api/distancematrix/json").
                    addConverterFactory(GsonConverterFactory.create(
                            new GsonBuilder().create())).client(client)
                    .build();
            service = r.create(API_Interface.class);
        }
        return service;
    }

    }

