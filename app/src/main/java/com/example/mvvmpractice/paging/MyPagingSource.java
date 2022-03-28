package com.example.mvvmpractice.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.ListenableFuturePagingSource;
import androidx.paging.PagingState;

import com.example.mvvmpractice.model.Image;
import com.example.mvvmpractice.model.Response;
import com.example.mvvmpractice.retrofit.MyService;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.HttpException;

public class MyPagingSource extends ListenableFuturePagingSource<Integer, Image> {

    private static final int STARTING_PAGE = 1;
    private final Executor executor = Executors.newSingleThreadExecutor();
    Integer page;

    private final MyService myService;
    public MyPagingSource(MyService myService) {
        this.myService = myService;
    }


    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, Image> pagingState) {
        Integer anchorPosition = pagingState.getAnchorPosition();
        if (anchorPosition == null) {
            return null;
        }

        LoadResult.Page<Integer, Image> anchorPage = pagingState.closestPageToPosition(anchorPosition);
        if (anchorPage == null) {
            return null;
        }

        Integer prevKey = anchorPage.getPrevKey();
        if (prevKey != null) {
            return prevKey + 1;
        }

        Integer nextKey = anchorPage.getNextKey();
        if (nextKey != null) {
            return nextKey - 1;
        }
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<LoadResult<Integer, Image>> loadFuture(@NonNull LoadParams<Integer> loadParams) {
        page = (loadParams.getKey() == null) ? STARTING_PAGE: loadParams.getKey();
        ListenableFuture<LoadResult<Integer, Image>> pageFuture =
                Futures.transform(myService.getImages("26326233-b339550cf5ad7250297894904",
                        "pagani",
                        "photo",
                        true,
                        page,
                        3), this::toLoadResult, executor);

        ListenableFuture<LoadResult<Integer, Image>> partialLoadResultFuture =
                Futures.catching(pageFuture, HttpException.class, LoadResult.Error::new, executor);

        return Futures.catching(partialLoadResultFuture,
                IOException.class, LoadResult.Error::new, executor);
    }

    private LoadResult<Integer, Image> toLoadResult(@NonNull Response response) {

        Integer prevPage = page > 1 ? page-1 : null;
        Integer nextPage = response.getHits().size() > 0 ? page + 1 : null;

        return new LoadResult.Page<>(response.getHits(), prevPage, nextPage);
    }
}
