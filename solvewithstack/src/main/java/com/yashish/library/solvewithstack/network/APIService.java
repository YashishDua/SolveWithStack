package com.yashish.library.solvewithstack.network;

import com.yashish.library.solvewithstack.utils.Constants;
import com.yashish.library.solvewithstack.models.StackAnswer;

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
