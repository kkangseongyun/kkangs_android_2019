package com.example.part10_30;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.part10_30.retrofit.RetrofitFactory;
import com.example.part10_30.retrofit.RetrofitService;
import com.example.part10_30.room.AppDatabase;
import com.example.part10_30.room.ArticleDAO;
/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교재에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */
public class MyApplication extends Application {

    public static Context context;
    public static ArticleDAO dao;
    public static RetrofitService networkService;
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "database-name").build();
        dao = db.articleDao();
        networkService = RetrofitFactory.create();
    }
}
