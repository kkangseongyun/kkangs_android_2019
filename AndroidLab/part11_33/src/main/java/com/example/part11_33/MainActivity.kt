package com.example.part11_33

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교재에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inheritanceTestBtn.setOnClickListener(this)
        propertyTestBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v==inheritanceTestBtn){
            var customer=Customer()
            customer.someFun()

        }else if(v==propertyTestBtn){
            val obj=PropertyTest()
            obj.greeting="kkang"
            resultTextView.setText("Property Test \n greeting : ${obj.greeting} , name: ${obj.name}")
        }
    }
}
