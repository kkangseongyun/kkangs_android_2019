package com.example.part7_19;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교재에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */
public class DialogActivity extends Activity implements View.OnClickListener{
    ImageView finishBtn;
    TextView numberView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);
        numberView=findViewById(R.id.lab1_phone_number);
        finishBtn=findViewById(R.id.lab1_remove_icon);

        finishBtn.setOnClickListener(this);

        Intent intent=getIntent();
        String number=intent.getStringExtra("number");
        numberView.setText(number);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
