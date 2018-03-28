package com.yashish.library.solveerror.models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lenovo on 28-03-2018.
 */

public interface APIService {
    @GET("2.2/search/advance?page=2&pagesize=1&order=desc&sort=relevance&accepted=True")
    Call<StackAnswer> getAnswer(@Query("title") String title, @Query("site") String site);
}
