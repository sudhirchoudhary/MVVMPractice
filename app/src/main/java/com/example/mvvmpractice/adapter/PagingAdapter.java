package com.example.mvvmpractice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvvmpractice.R;
import com.example.mvvmpractice.databinding.ItemLayoutBinding;
import com.example.mvvmpractice.model.Image;

public class PagingAdapter extends PagingDataAdapter<Image, PagingAdapter.ViewHolder> {
    private final Context context;

    public PagingAdapter(Context context) {
        super(diff);
        this.context = context;
    }

    @NonNull
    @Override
    public PagingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PagingAdapter.ViewHolder(DataBindingUtil.inflate(LayoutInflater
                .from(parent.getContext()), R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PagingAdapter.ViewHolder holder, int position) {
        Image image = getItem(position);
        (holder).itemLayoutBinding.setImageBind(image);

        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(context)
                .setDefaultRequestOptions(defaultOptions)
                .load(image.getLargeImageURL())
                .into((holder).itemLayoutBinding.imageView);

    }
    static DiffUtil.ItemCallback<Image> diff = new
            DiffUtil.ItemCallback<Image>() {
                @Override
                public boolean areItemsTheSame(@NonNull Image oldItem, @NonNull Image newItem) {
                    return oldItem.getLargeImageURL().equals(newItem.getLargeImageURL());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Image oldItem, @NonNull Image newItem) {
                    return oldItem.getLargeImageURL().equals(newItem.getLargeImageURL());
                }
            };

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        ItemLayoutBinding itemLayoutBinding;
        public ViewHolder( ItemLayoutBinding itemLayoutBinding) {
            super(itemLayoutBinding.getRoot());
            this.itemLayoutBinding = itemLayoutBinding;
        }
    }
}
