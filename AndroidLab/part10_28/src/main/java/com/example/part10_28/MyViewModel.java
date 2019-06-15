package com.example.part10_28;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교재에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */
public class MyViewModel extends ViewModel {
    private static final String QUERY = "kluane";
    private static final String API_KEY = "~~~~";
    RetrofitService networkService = RetrofitFactory.create();

    public MutableLiveData<List<ItemModel>> getNews() {
        MutableLiveData<List<ItemModel>> liveData = new MutableLiveData<>();
        networkService.getList(QUERY, API_KEY, 1, 10)
                .enqueue(new Callback<PageListModel>() {
                    @Override
                    public void onResponse(Call<PageListModel> call, Response<PageListModel> response) {
                        if (response.isSuccessful()) {
                            liveData.postValue(response.body().articles);

                        }
                    }

                    @Override
                    public void onFailure(Call<PageListModel> call, Throwable t) {

                    }
                });
        return liveData;
    }

}
