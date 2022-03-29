package com.example.mvvmpractice.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import com.example.mvvmpractice.model.Image;
import com.example.mvvmpractice.paging.MyPagingSource;
import com.example.mvvmpractice.retrofit.MyService;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.CoroutineScope;

@HiltViewModel
public class SecondViewModel extends ViewModel {
    //viewmodel with paging implementation

    MyService myService;
    public LiveData<PagingData<Image>> pagingDataLiveData;
    @Inject
    SecondViewModel(MyService myService) {
        this.myService = myService;
        init();
    }

    private void init() {
        Pager<Integer, Image> pager = new Pager<>(new PagingConfig(1),
                () -> new MyPagingSource(myService));
        pagingDataLiveData = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), this);
    }
}
