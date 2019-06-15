package com.example.part6_17;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교재에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */
public class Lab17_2Activity extends AppCompatActivity {
    public static final String TARGET_SETTING_PAGE = "target";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab17_2);

        SettingPreferenceFragment settingsFragment = new SettingPreferenceFragment();
        Intent intent = getIntent();
        if (intent != null) {
            String rootKey = intent.getStringExtra(TARGET_SETTING_PAGE);
            if (rootKey != null) {
                Bundle args = new Bundle();
                args.putString(PreferenceFragmentCompat.ARG_PREFERENCE_ROOT, rootKey);
                settingsFragment.setArguments(args);
            }
        }

        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, settingsFragment, null)
                .commit();
    }
}
