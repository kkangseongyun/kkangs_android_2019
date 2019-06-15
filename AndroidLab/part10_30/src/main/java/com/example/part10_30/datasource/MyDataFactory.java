package com.example.part10_30.datasource;

import android.arch.paging.DataSource;
/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교재에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */
public class MyDataFactory extends DataSource.Factory{
    @Override
    public DataSource create() {
        return new MyDataSource();
    }
}
