package com.yashish.library.solveerror.models;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Lenovo on 28-03-2018.
 */

public interface APIService {
    
    @GET("2.2/search/advanced?page=2&pagesize=1&order=desc&" +
            "sort=relevance&accepted=True&title=Android&site=stackoverflow")
    Call<StackAnswer> getAnswer(/*@Query("title") String title, @Query("site") String site*/);
}
