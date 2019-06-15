package com.example.part9_mission;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교재에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */
public interface RetrofitService {
    @GET("weather")
    Call<CurrentData> getCurrentData(@Query("q") String q,
                                     @Query("mode") String mode,
                                     @Query("units") String units,
                                     @Query("appid") String appid);

    @GET("forecast/daily")
    Call<ForecastData> getForecastData(@Query("q") String q,
                                       @Query("mode") String mode,
                                       @Query("units") String units,
                                       @Query("appid") String appid);
}
