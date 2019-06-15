package com.example.part11_34

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교재에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */
class DBHelper(context: Context): SQLiteOpenHelper(context, "datadb", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        val tableSql="create table tb_data ("+
                "_id integer primary key autoincrement," +
                "name not null," +
                "date)"

        db?.execSQL(tableSql)

        db?.execSQL("insert into tb_data (name,date) values ('안영주','2019-07-01')")
        db?.execSQL("insert into tb_data (name,date) values ('최은경','2019-07-01')")
        db?.execSQL("insert into tb_data (name,date) values ('최호성','2019-07-01')")
        db?.execSQL("insert into tb_data (name,date) values ('정성택','2019-06-30')")
        db?.execSQL("insert into tb_data (name,date) values ('채규태','2019-06-28')")
        db?.execSQL("insert into tb_data (name,date) values ('원형섭','2019-06-28')")
        db?.execSQL("insert into tb_data (name,date) values ('표선영','2019-06-28')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table tb_data")
        onCreate(db)
    }
}