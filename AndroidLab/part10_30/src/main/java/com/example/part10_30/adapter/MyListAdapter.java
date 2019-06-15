package com.example.part10_30.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.part10_30.databinding.ItemMainBinding;
import com.example.part10_30.model.ItemModel;
/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교재에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */

public class MyListAdapter extends PagedListAdapter<ItemModel, MyListAdapter.ItemViewHolder> {


    public static DiffUtil.ItemCallback<ItemModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<ItemModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull ItemModel oldItem, @NonNull ItemModel newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ItemModel oldItem, @NonNull ItemModel newItem) {
            return oldItem.equals(newItem);
        }
    };
    public MyListAdapter(Context context){
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        ItemMainBinding binding = ItemMainBinding.inflate(layoutInflater, parent, false);
        return new ItemViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        ItemModel article=getItem(position);
        holder.binding.setData(article);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ItemMainBinding binding;
        public ItemViewHolder(ItemMainBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
