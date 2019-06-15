package com.example.part10_29.retrofit;



import com.example.part10_29.model.PageListModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교재에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */
public interface RetrofitService {

    @GET("/v2/everything")
    Call<PageListModel> getList(@Query("q") String q,
                                @Query("apiKey") String apiKey,
                                @Query("page") long page,
                                @Query("pageSize") int pageSize);
}
