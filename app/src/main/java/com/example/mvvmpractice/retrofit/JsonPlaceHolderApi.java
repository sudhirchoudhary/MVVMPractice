package com.example.mvvmpractice.retrofit;

import com.example.mvvmpractice.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("api/")
    Call<Response> getImages(
            @Query("key") String key,
            @Query("q") String q,
            @Query("image_type") String imageType,
            @Query("pretty") boolean pretty,
            @Query("page") int page,
            @Query("per_page") int perPage
    );
}
