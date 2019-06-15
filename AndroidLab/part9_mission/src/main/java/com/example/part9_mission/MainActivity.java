package com.example.part9_mission;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교재에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */
public class MainActivity extends AppCompatActivity {

    private final static String API_KEY="~~~~";

    ImageView symbolView;
    TextView temperatureView;
    TextView upView;
    TextView downView;
    RecyclerView recyclerView;

    MyAdapter adapter;

    RetrofitService networkService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temperatureView=findViewById(R.id.mission1_temperature);
        upView=findViewById(R.id.mission1_up_text);
        downView=findViewById(R.id.mission1_down_text);
        symbolView=findViewById(R.id.mission1_symbol);
        recyclerView=findViewById(R.id.mission1_recycler);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new MyItemDecoration());



        networkService=RetrofitFactory.create();
        networkService.getCurrentData("seoul", "json", "metric", API_KEY)
                .enqueue(new Callback<CurrentData>() {
                    @Override
                    public void onResponse(Call<CurrentData> call, Response<CurrentData> response) {
                        if(response.isSuccessful()){
                            CurrentData data=response.body();
                            temperatureView.setText(String.valueOf(data.main.temp));
                            upView.setText(String.valueOf(data.main.temp_max));
                            downView.setText(String.valueOf(data.main.temp_min));

                            Glide.with(MainActivity.this).load("http://openweathermap.org/img/w/"+data.weather.get(0).icon+".png").into(symbolView);
                        }
                    }

                    @Override
                    public void onFailure(Call<CurrentData> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

        networkService.getForecastData("seoul","json","metric", API_KEY)
                .enqueue(new Callback<ForecastData>() {
                    @Override
                    public void onResponse(Call<ForecastData> call, Response<ForecastData> response) {
                        if(response.isSuccessful()){
                            ForecastData responseData= response.body();
                            ArrayList<ItemData> itemList=new ArrayList<>();
                            Log.d("kkang","size:"+responseData.list.size());
                            for(ForecastData.DataList data : responseData.list ){
                                ItemData item=new ItemData();
                                item.max=String.valueOf(data.temp.max);
                                item.min=String.valueOf(data.temp.min);
                                item.iconUrl=data.weather.get(0).icon;
                                itemList.add(item);
                            }

                            new DownloadThread(itemList).start();
                        }
                    }

                    @Override
                    public void onFailure(Call<ForecastData> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==10){
                List<ItemData> list=(List<ItemData>)msg.obj;
                recyclerView.setAdapter(adapter=new MyAdapter(list));
            }
        }
    };
    class DownloadThread extends Thread {
        List<ItemData> list;
        public DownloadThread(List<ItemData> list){
            this.list=list;
        }
        @Override
        public void run() {
            super.run();
            for(ItemData item: list) {
                try {
                    //add.......................
                    item.image=Glide
                            .with(MainActivity.this)
                            .load("http://openweathermap.org/img/w/" + item.iconUrl + ".png")
                            .asBitmap()
                            .into(50, 50)
                            .get();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Message message=new Message();
            message.what=10;
            message.obj=list;
            handler.sendMessage(message);
        }
    }
    private class ItemData {
        public String max;
        public String min;
        public String iconUrl;
        public Bitmap image;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView maxView;
        public TextView minView;
        public ImageView imageView;

        public MyViewHolder(View itemView){
            super(itemView);
            maxView=itemView.findViewById(R.id.mission1_item_max);
            minView=itemView.findViewById(R.id.mission1_item_min);
            imageView=itemView.findViewById(R.id.mission1_item_image);

        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
        private final List<ItemData> list;
        public MyAdapter(List<ItemData> list){
            this.list=list;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.mission1_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            ItemData vo=list.get(position);
            holder.maxView.setText(vo.max);
            holder.minView.setText(vo.min);
            holder.imageView.setImageBitmap(vo.image);

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    class MyItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(10, 10, 10, 10);
            view.setBackgroundColor(0x88929090);
        }
    }

}
