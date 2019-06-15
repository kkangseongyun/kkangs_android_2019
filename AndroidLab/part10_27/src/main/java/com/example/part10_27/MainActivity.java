package com.example.part10_27;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.part10_27.databinding.ActivityMainBinding;
import com.example.part10_27.databinding.ItemMainBinding;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교재에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */
public class MainActivity extends AppCompatActivity {

    private static final String QUERY = "movies";
    private static final String API_KEY = "~~~~";

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        


        RetrofitService networkService = RetrofitFactory.create();

        networkService.getList(QUERY, API_KEY, 1, 10)
                .enqueue(new Callback<PageListModel>() {
                    @Override
                    public void onResponse(Call<PageListModel> call, Response<PageListModel> response) {
                        if (response.isSuccessful()) {

                            MyAdapter adapter=new MyAdapter(response.body().articles);
                            binding.recyclerView.setAdapter(adapter);

                        }
                    }

                    @Override
                    public void onFailure(Call<PageListModel> call, Throwable t) {

                    }
                });

    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        ItemMainBinding binding;
        public ItemViewHolder(ItemMainBinding binding){
            super(binding.getRoot());
            this.binding=binding;
        }

    }

    class MyAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        List<ItemModel> articles;

        public MyAdapter(List<ItemModel> articles) {
            this.articles = articles;
        }

        @Override
        public int getItemCount() {
            return articles.size();
        }


        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            ItemMainBinding binding=ItemMainBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
            return new ItemViewHolder((binding));
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
            ItemModel model=articles.get(i);
            itemViewHolder.binding.setItem(model);
        }

    }

    @BindingAdapter("bind:publishedAt")
    public static void publishedAt(TextView view, String date){
        view.setText(AppUtils.getDate(date)+" at "+AppUtils.getTime(date));
    }
    @BindingAdapter("bind:urlToImage")
    public static void urlToImage(ImageView view, String url){
        Glide.with(MyApplication.getAppContext()).load(url).override(250, 200).into(view);
    }

}


