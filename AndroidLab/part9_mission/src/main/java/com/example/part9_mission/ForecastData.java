package com.example.part9_mission;
/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교재에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */
public class ForecastData {
    public class DataList {
        public class Temp {
            public Double min;
            public Double max;
        }
        public class Weather {
            public String icon;
        }
        public Temp temp;
        public java.util.List<Weather> weather = null;
    }

    public java.util.List<DataList> list = null;
}
