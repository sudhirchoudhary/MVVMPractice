package com.example.mvvmpractice.retrofit;

import com.example.mvvmpractice.model.Response;
import com.google.common.util.concurrent.ListenableFuture;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyService {
    @GET("api/")
    ListenableFuture<Response> getImages(
            @Query("key") String key,
            @Query("q") String q,
            @Query("image_type") String imageType,
            @Query("pretty") boolean pretty,
            @Query("page") int page,
            @Query("per_page") int perPage
    );
}
