package com.example.part4_12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Method;

public class Lab12_2Activity extends AppCompatActivity {

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab12_2);

        ImageView imageView=findViewById(R.id.imageView);
        registerForContextMenu(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_lab2, menu);
        try{
            Method method=menu.getClass().getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            method.setAccessible(true);
            method.invoke(menu, true);
        }catch (Exception e){
            e.printStackTrace();
        }

        MenuItem menuItem=menu.findItem(R.id.menu_main_search);
        searchView=(SearchView)menuItem.getActionView();
        searchView.setQueryHint(getResources().getString(R.string.query_hint));
        searchView.setOnQueryTextListener(queryTextListener);

        return true;
    }

    SearchView.OnQueryTextListener queryTextListener=new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            searchView.setQuery("", false);
            searchView.setIconified(true);
            showToast(s);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            return false;
        }
    };

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,0,0,"서버전송");
        menu.add(0,1,0,"보관함에 보관");
        menu.add(0,2,0, "삭제");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                showToast("서버 전송이 선택되었습니다.");
                break;
            case 1:
                showToast("보관함에 보관이 선택되었습니다.");
                break;
            case 2:
                showToast("삭제가 선택되었습니다.");
                break;
        }
        return true;
    }

    private void showToast(String message){
        Toast toast=Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
