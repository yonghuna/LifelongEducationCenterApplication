package com.example.lifelongeducationcenterapplication.Community;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class Community_QAmodifyAndremoveActivity extends AppCompatActivity {


    // 글 수정 삭제
    // intent 문으로 어디서 오는지 확인
    EditText title, content;
    Button btModify;
    TextView num, writer, time,textview;
    Notice notice;
    Retrofit retrofit1; //httpclient library
    RemoteService rs1; //DB를 위한 인터페이스

    Retrofit retrofit2; //httpclient library
    RemoteService rs2; //DB를 위한 인터페이스

    Retrofit retrofit3; //httpclient library
    RemoteService rs3; //DB를 위한 인터페이스

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    int number;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("글 수정 삭제");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_modify_remove);

        Intent intent = getIntent(); /*데이터 수신*/
        number = intent.getIntExtra("number", 1); // pk로 구분
        setRetrofit();
        findId();
        clickBtModify();
        //스크롤
        textview = findViewById(R.id.write_content_tv);
        textview.setMovementMethod(new ScrollingMovementMethod());
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

    public void findId() {
        title = (EditText) findViewById(R.id.write_title_tv);
        content = (EditText) findViewById(R.id.write_content_tv);
        btModify = (Button) findViewById(R.id.write_modify);
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

        retrofit3 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs3 = retrofit3.create(RemoteService.class);
    }

    @Override
    protected void onResume() {

        Call<Notice> call = rs3.qnaContent(number);//call객체
        call.enqueue(new Callback<Notice>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<Notice> call, Response<Notice> response) {
                if (response.isSuccessful()) {
                    notice = response.body();
                    num.setText(Integer.toString(number));
                    writer.setText(notice.getName());
                    time.setText(getTime());
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

    public void clickBtModify() {
        btModify.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Call<Void> call = rs1.userModify(number, title.getText().toString().trim(), getTime(),  content.getText().toString(), StaticId.id);//call객체
                call.enqueue(new Callback<Void>() {//enqueue 메소드 실행
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), Community_QuestionAndAnswerActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        System.out.println("글 작성 실패" + call + " " + t);
                    }
                });
            }

        });

    }


    public String getTime() {
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
}



