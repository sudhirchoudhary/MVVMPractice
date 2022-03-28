package com.example.mvvmpractice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvvmpractice.R;
import com.example.mvvmpractice.databinding.ItemLayoutBinding;
import com.example.mvvmpractice.model.Image;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // normal Adapter (without paging feature)

    private final ArrayList<Image> mNicePlaces;
    private final Context mContext;

    public RecyclerAdapter(Context context, ArrayList<Image> nicePlaces) {
        mNicePlaces = nicePlaces;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater
                .from(parent.getContext()), R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ((ViewHolder)viewHolder).itemLayoutBinding.setImageBind(mNicePlaces.get(i));

        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(mNicePlaces.get(i).getLargeImageURL())
                .into(((ViewHolder)viewHolder).itemLayoutBinding.imageView);
    }

    @Override
    public int getItemCount() {
        return mNicePlaces.size();
    }

    private static class ViewHolder extends RecyclerView.ViewHolder{

        ItemLayoutBinding itemLayoutBinding;

        public ViewHolder(ItemLayoutBinding itemLayoutBinding) {
            super(itemLayoutBinding.getRoot());
            this.itemLayoutBinding = itemLayoutBinding;
        }
    }
}
