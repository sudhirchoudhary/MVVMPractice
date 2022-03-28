package com.example.mvvmpractice.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.mvvmpractice.repo.ImageRepository;
import com.example.mvvmpractice.model.Image;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

//@HiltViewModel
public class MainViewModel extends ViewModel {

    //if we dont use paging then this viewModel will be used

    private final ImageRepository mRepo;
    private final MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();
    private int pageNumber = 0;
    public LiveData<ArrayList<Image>> finalList;
    public final ArrayList<Image> currentList = new ArrayList<>();

    //@Inject
    public MainViewModel(ImageRepository imageRepository) {
        mRepo = imageRepository;
        finalList = Transformations.map(mRepo.loadImages(pageNumber), this::getImagesList);
        loadMoreImages();
    }

    private ArrayList<Image> getImagesList(ArrayList<Image> input) {
        currentList.addAll(input);
        return currentList;
    }

    public void loadMoreImages() {
        pageNumber += 1;
        mIsUpdating.setValue(true);
        mRepo.loadImages(pageNumber);
    }


    public LiveData<Boolean> getIsUpdating() {
        return mIsUpdating;
    }

    public void hasBeenUploaded() {
        mIsUpdating.setValue(false);
    }

}
