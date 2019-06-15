package com.example.part10_30.room;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.content.ClipData;


import com.example.part10_30.model.ItemModel;
/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교재에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */
@Dao
public interface ArticleDAO {

    @Query("SELECT * FROM article")
    DataSource.Factory<Integer, ItemModel> getAll();

    @Insert
    void insertAll(ItemModel... users);

    @Query("DELETE FROM article")
    void deleteAll();
}
