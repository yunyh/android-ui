package com.my.listapplication.adapter;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.my.listapplication.R;

/**
 * Created by YoungHyup on 2018-05-01.
 */

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ItemViewHolder> {


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public static final class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemViewHolder(View itemView) {
            super(itemView);
        }
    }

}