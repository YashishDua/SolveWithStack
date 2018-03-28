package com.yashish.library.solveerror.network;

import com.yashish.library.solveerror.Constants;
import com.yashish.library.solveerror.models.StackAnswer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lenovo on 28-03-2018.
 */

public interface APIService {

    @GET(Constants.ADVANCED_SEARCH)
    Call<StackAnswer> getAnswer(@Query("title") String title);
}
