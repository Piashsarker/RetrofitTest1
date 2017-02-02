package com.ptlearnpoint.www.retrofittest1;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by PT on 1/18/2017.
 */

public interface ApiService {

    @GET("movies.json")
    Call<ArrayList<Example>> getMovie();
}
