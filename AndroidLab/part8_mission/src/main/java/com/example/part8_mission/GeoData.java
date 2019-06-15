package com.example.part8_mission;

import java.util.ArrayList;
/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교재에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */
public class GeoData {

	
	public static ArrayList<BankData> getAddressData(){
        ArrayList<BankData> list=new ArrayList<>();
        BankData data=new BankData();
        data.bankName="국민은행";
        data.bankLat=37.502268;
        data.bankLng=127.040707;
        list.add(data);

        data=new BankData();
        data.bankName="한국은행";
        data.bankLat=37.500600;
        data.bankLng=127.038143;
        list.add(data);

        data=new BankData();
        data.bankName="신한은행";
        data.bankLat=37.501928;
        data.bankLng=127.037698;

        list.add(data);
        return list;
	}
}
class BankData {
	String bankName;
	double bankLat;
	double bankLng;
}