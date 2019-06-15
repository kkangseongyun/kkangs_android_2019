package com.example.part6_17;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교재에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */
public class SettingPreferenceFragment extends PreferenceFragmentCompat {
    SharedPreferences prefs;

    Preference soundPreference;
    Preference keywordSoundPreference;
    Preference keywordScreen;

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        setPreferencesFromResource(R.xml.settings_preference, s);

        if (s == null) {
            soundPreference = findPreference("sound_list");
            keywordSoundPreference = findPreference("keyword_sound_list");
            keywordScreen = findPreference("keyword_screen");

            prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

            if (!prefs.getString("sound_list", "").equals("")) {
                soundPreference.setSummary(prefs.getString("sound_list", "카톡"));
            }

            if (!prefs.getString("keyword_sound_list", "").equals("")) {
                keywordSoundPreference.setSummary(prefs.getString("keyworld_sound_list", "카톡"));
            }
            if (prefs.getBoolean("keyword", false)) {
                keywordScreen.setSummary("사용");
            }

            prefs.registerOnSharedPreferenceChangeListener(prefListener);
        }

    }

    SharedPreferences.OnSharedPreferenceChangeListener prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals("sound_list")) {
                soundPreference.setSummary(prefs.getString("sound_list", "카톡"));
            }
            if (key.equals("keyword_sound_list")) {
                keywordSoundPreference.setSummary(prefs.getString("keyword_sound_list", "카톡"));
            }
        }
    };

    @Override
    public void onNavigateToScreen(android.support.v7.preference.PreferenceScreen preferenceScreen) {
        Intent intent = new Intent(getActivity(), Lab17_2Activity.class)
                .putExtra(Lab17_2Activity.TARGET_SETTING_PAGE, preferenceScreen.getKey());
        startActivity(intent);
    }
}
