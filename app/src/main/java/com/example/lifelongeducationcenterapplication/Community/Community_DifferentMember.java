package com.example.lifelongeducationcenterapplication.Community;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifelongeducationcenterapplication.Notice;
import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RemoteService;
import com.example.lifelongeducationcenterapplication.StaticId;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class Community_DifferentMember extends AppCompatActivity {
    Button btList;
    TextView num, writer, time, title, content;
    Notice notice;
    Retrofit retrofit1; //httpclient library
    RemoteService rs1; //DB를 위한 인터페이스

    Retrofit retrofit2; //httpclient library
    RemoteService rs2; //DB를 위한 인터페이스
    String secret;
    String info;
    int number;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("글 내용");
        setContentView(R.layout.activity_community__bulletin_boardwriting);


        // 어디서 왓는지 구분
        Intent intent = getIntent(); /*데이터 수신*/
        number = intent.getIntExtra("number", 1); // pk로 구분
        findId();
        setRetrofit();
        btList.setVisibility(View.GONE);

    }

    public void findId() {
        title = (TextView) findViewById(R.id.write_title_tv);
        content = (TextView) findViewById(R.id.write_content_tv);
        btList = (Button) findViewById(R.id.list);
        num = (TextView) findViewById(R.id.number);
        writer = (TextView) findViewById(R.id.who);
        time = (TextView) findViewById(R.id.time);
    }


    public void setRetrofit() {
        retrofit1 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs1 = retrofit1.create(RemoteService.class);

        retrofit2 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs2 = retrofit2.create(RemoteService.class);

    }

    @Override
    protected void onResume() {

        Call<Notice> call = rs1.qnaContent(number);//call객체
        call.enqueue(new Callback<Notice>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<Notice> call, Response<Notice> response) {
                if (response.isSuccessful()) {
                    notice = response.body();

                    num.setText(Integer.toString(number));
                    writer.setText(notice.getName());
                    time.setText(notice.getReportingdate());
                    title.setText(notice.getTitle());
                    content.setText(notice.getContents());

                }
            }

            @Override
            public void onFailure(Call<Notice> call, Throwable t) {
                System.out.println("Q N A 읽기" + call + " " + t);
            }
        });
        super.onResume();
    }
}
