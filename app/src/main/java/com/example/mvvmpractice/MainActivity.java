package com.example.mvvmpractice;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmpractice.adapter.PagingAdapter;
import com.example.mvvmpractice.adapter.PagingLoadStateAdapter;
import com.example.mvvmpractice.adapter.RecyclerAdapter;
import com.example.mvvmpractice.databinding.ActivityMainBinding;
import com.example.mvvmpractice.viewmodel.MainViewModel;
import com.example.mvvmpractice.viewmodel.SecondViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    PagingAdapter pagingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.mvvmpractice.databinding.ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        RecyclerView mRecyclerView = binding.rvMain;

        pagingAdapter = new PagingAdapter(this);
        initRecyclerView(mRecyclerView);
        initViewModel();
    }

    private void initViewModel() {
        SecondViewModel viewModel = new ViewModelProvider(this).get(SecondViewModel.class);
        viewModel.pagingDataLiveData.observe(this, itemPagingData ->
                        pagingAdapter.submitData(this.getLifecycle(), itemPagingData));
    }

    private void initRecyclerView(RecyclerView recyclerView) {
        if (recyclerView.getLayoutManager() == null)
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (recyclerView.getAdapter() == null)
            recyclerView.setAdapter(pagingAdapter.withLoadStateFooter(new PagingLoadStateAdapter(view -> pagingAdapter.retry())));
    }
}