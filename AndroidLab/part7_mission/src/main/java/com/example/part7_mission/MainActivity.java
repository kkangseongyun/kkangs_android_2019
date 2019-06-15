package com.example.part7_mission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.util.List;
/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교재에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */
public class MainActivity extends AppCompatActivity {
    EditText contentView;
    SpannableStringBuilder stringBuilder;
    int width;
    int height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentView=findViewById(R.id.mission1_edit);

        DisplayMetrics metrics=getResources().getDisplayMetrics();
        width=(int)metrics.xdpi;
        height=(int)metrics.ydpi;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_mission1){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                Matisse.from(this)
                        .choose(MimeType.of(MimeType.JPEG))
                        .countable(true)
                        .maxSelectable(9)
                        .spanCount(3)
                        .imageEngine(new GlideEngine())
                        .forResult(100);
            }else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==100 && resultCode==RESULT_OK){
            List<Uri> mSelect=Matisse.obtainResult(data);
            for(Uri uri : mSelect){
                DownloadThread thread=new DownloadThread(uri);
                thread.start();
            }
        }
    }
    class DownloadThread extends Thread {
        Uri uri;
        DownloadThread(Uri uri){
            this.uri=uri;
        }

        @Override
        public void run() {
            try{
                Bitmap bitmap = Glide
                        .with(MainActivity.this)
                        .load(uri)
                        .asBitmap()
                        .override(width, height)
                        .into(width, height)
                        .get();

                Message message=new Message();
                message.what=10;
                Bundle bundle=new Bundle();
                bundle.putParcelable("bitmap", bitmap);
                bundle.putParcelable("uri", uri);
                message.setData(bundle);
                handler.sendMessage(message);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==10){
                Bundle bundle=msg.getData();
                Uri uri=bundle.getParcelable("uri");
                Bitmap bitmap=bundle.getParcelable("bitmap");

                stringBuilder=new SpannableStringBuilder(contentView.getText());
                stringBuilder.insert(contentView.getSelectionStart(), "\n [["+uri+"]] \n");

                Log.d("kkang", "11111111111111"+stringBuilder.toString());

                String result = stringBuilder.toString();
                int start = result.indexOf("[[" + uri + "]]");
                int end = start + new String("[[" + uri + "]]").length();

                stringBuilder.setSpan(new ImageSpan(MainActivity.this, bitmap, ImageSpan.ALIGN_BASELINE), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                contentView.setText(stringBuilder);

                contentView.setSelection(end + 1);

                contentView.requestFocus();
                final InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                contentView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        inputMethodManager.showSoftInput(contentView, 0);
                    }
                }, 30);
            }
        }
    };

}
