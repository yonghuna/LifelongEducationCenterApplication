package com.example.lifelongeducationcenterapplication.Community;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifelongeducationcenterapplication.Notice;
import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RemoteService;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class Community_NoticeContentActivity extends AppCompatActivity {
    //공지사항
    TextView title, content, who, time, postedNumber, comment;
    Button list, remove, modify;
    int number;
    Retrofit retrofit;
    RemoteService rs;

    Notice notice;
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("공지사항");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_community__bulletin_boardwriting);


        Intent intent = getIntent();
        number = intent.getIntExtra("number", 1); // pk로 구분

        title = (TextView) findViewById(R.id.write_title_tv);
        content = (TextView) findViewById(R.id.write_content_tv);
        postedNumber = (TextView) findViewById(R.id.number);
        who = (TextView) findViewById(R.id.who);
        time = (TextView) findViewById(R.id.time);
        comment = (TextView) findViewById(R.id.comment);
        list = (Button) findViewById(R.id.list);
        remove = (Button) findViewById(R.id.remove);
        modify = (Button) findViewById(R.id.modfiy);
        listview = (ListView) findViewById(R.id.contentListView);

        listview.setVisibility(View.GONE);
        remove.setVisibility(View.GONE);
        modify.setVisibility(View.GONE);
        comment.setVisibility(View.GONE);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs = retrofit.create(RemoteService.class);


        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
    /*
    @Override   //액셔바 홈버튼
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

     */
    @Override
    protected void onResume() {

        Call<Notice> call = rs.noticeEnter(number);//call객체
        call.enqueue(new Callback<Notice>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<Notice> call, Response<Notice> response) {
                if (response.isSuccessful()) {
                    notice = response.body();
                    postedNumber.setText(Integer.toString(notice.getNumber()));
                    time.setText(notice.getReportingdate());
                    content.setText(Html.fromHtml(notice.getContents()).toString());
                    who.setText("관리자");
                    title.setText(notice.getTitle());
                }
            }

            @Override
            public void onFailure(Call<Notice> call, Throwable t) {
                System.out.println("공지사항 글 들어갈떄 " + call + " " + t);

            }
        });


        super.onResume();
    }

}
