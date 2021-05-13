package com.example.lifelongeducationcenterapplication.Community;

import androidx.appcompat.app.AppCompatActivity;
import com.example.lifelongeducationcenterapplication.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Community_GalleryActivity extends AppCompatActivity {
    //커뮤니티 갤러리
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("갤러리");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_community__gallery);
    }
    @Override   //뒤로가기
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    @Override   //액션바 홈버튼
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}