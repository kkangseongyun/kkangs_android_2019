package com.example.part10_29.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.part10_29.model.ItemModel;
/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교재에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */

@Database(entities = {ItemModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    public abstract  ArticleDAO articleDAO();
}
