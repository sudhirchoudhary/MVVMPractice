package com.example.mvvmpractice.dagger;

import com.example.mvvmpractice.retrofit.JsonPlaceHolderApi;
import com.example.mvvmpractice.retrofit.MyService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.adapter.guava.GuavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    public static final String BASE_URL= "https://pixabay.com/";

    @Provides
    @Singleton
    public static MyService makeApiCall() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(GuavaCallAdapterFactory.create())
                .build()
                .create(MyService.class);
    }
}
