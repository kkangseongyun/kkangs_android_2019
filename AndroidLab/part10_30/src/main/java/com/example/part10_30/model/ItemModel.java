package com.example.part10_30.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교재에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */
@Entity(tableName = "article")
public class ItemModel {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String author;
    public String title;
    public String description;
    public String urlToImage;
    public String publishedAt;
}
