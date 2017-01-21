package com.antariksh.wallypaper.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ahmed Talaat on 09-Apr-16.
 */
public interface ColourloversService {

    @GET("patterns/top?format=json")
    Call<List<PatternModel>> getTopRatedPatterns(@Query("resultOffset") int resultOffset, @Query("numResults") int numResults);

}
