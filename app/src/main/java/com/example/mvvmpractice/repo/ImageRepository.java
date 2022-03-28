package com.example.mvvmpractice.repo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmpractice.model.Image;
import com.example.mvvmpractice.retrofit.JsonPlaceHolderApi;
import com.example.mvvmpractice.model.Response;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;

public class ImageRepository {
    /*
    if dont use paging library then this Repository will do network call
    **/


    private final MutableLiveData<ArrayList<Image>> dataSet = new MutableLiveData<>();
    private final JsonPlaceHolderApi jsonPlaceHolderApi;

    //@Inject
    public ImageRepository(JsonPlaceHolderApi jsonPlaceHolderApi){
        this.jsonPlaceHolderApi = jsonPlaceHolderApi;
    }

    public MutableLiveData<ArrayList<Image>> loadImages(int pageNumber) {
        Call<Response> call = jsonPlaceHolderApi.getImages("26326233-b339550cf5ad7250297894904",
                "pagani",
                "photo",
                true,
                pageNumber,
                3);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                if(response.isSuccessful()) {
                    Response responsex = response.body();
                    if(responsex != null) {
                        ArrayList<Image> finalList = (ArrayList<Image>) responsex.getHits();
                        if(finalList != null) {
                            dataSet.setValue(finalList);
                        }
                    }
                } else {
                    Log.e("Home", "not successFull" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                Log.e("Home", "message" + t.getMessage());
            }
        });
        return dataSet;
    }
}
