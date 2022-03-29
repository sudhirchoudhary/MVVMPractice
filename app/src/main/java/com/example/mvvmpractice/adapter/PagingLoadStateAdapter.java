package com.example.mvvmpractice.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.LoadState;
import androidx.paging.LoadStateAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmpractice.R;
import com.example.mvvmpractice.databinding.LoadStateItemBinding;

import org.jetbrains.annotations.NotNull;

public class PagingLoadStateAdapter extends LoadStateAdapter<PagingLoadStateAdapter.LoadStateViewHolder> {

    private final View.OnClickListener mRetryCallback;

    public PagingLoadStateAdapter(View.OnClickListener retryCallback) {
        mRetryCallback = retryCallback;
    }

    @Override
    public void onBindViewHolder(@NotNull LoadStateViewHolder loadStateViewHolder, @NotNull LoadState loadState) {
        loadStateViewHolder.bind(loadState);
    }

    @NotNull
    @Override
    public LoadStateViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, @NotNull LoadState loadState) {
        return new LoadStateViewHolder(viewGroup, mRetryCallback);
    }

    public static class LoadStateViewHolder extends RecyclerView.ViewHolder {

        LoadStateItemBinding binding;

        LoadStateViewHolder(@NonNull ViewGroup parent, @NonNull View.OnClickListener retryCallback) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.load_state_item, parent, false));
            binding = LoadStateItemBinding.bind(itemView);
            binding.retryButton.setOnClickListener(retryCallback);
        }

        public void bind(LoadState loadState) {
            if (loadState instanceof LoadState.Error) {
                LoadState.Error loadStateError = (LoadState.Error) loadState;
                binding.errorMsg.setText(loadStateError.getError().getLocalizedMessage());
            }
            binding.progressBar.setVisibility(loadState instanceof LoadState.Loading ? View.VISIBLE : View.GONE);
            binding.retryButton.setVisibility(loadState instanceof LoadState.Error ? View.VISIBLE : View.GONE);
            binding.errorMsg.setVisibility(loadState instanceof LoadState.Error ? View.VISIBLE : View.GONE);
        }

    }
}