package com.example.lifelongeducationcenterapplication.Community;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class Community_Bulletin_boardwritingActivity extends AppCompatActivity {
    // 글 쓰기
    // intent 문으로 어디서 오는지 확인
    EditText title, content;
    Button btSave;

    Retrofit retrofit1; //httpclient library
    RemoteService rs1; //DB를 위한 인터페이스

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    String secret;
    String info;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("글 작성");
        setContentView(R.layout.activity_writing);

        Intent intent = getIntent(); /*데이터 수신*/

        secret = intent.getExtras().getString("secret"); /*String형*/
        info = intent.getExtras().getString("info"); /*String형*/

        setRetrofit();
        findId();
        clickBtSave();


    }

    public void findId(){
        title = (EditText) findViewById(R.id.write_title_tv);
        content = (EditText) findViewById(R.id.write_content_tv);
        btSave = (Button) findViewById(R.id.modfiy);
    }

    public void setRetrofit(){
        retrofit1 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs1 = retrofit1.create(RemoteService.class);

    }

    public void clickBtSave(){
        if(title.getText().toString().trim() == null && content.getText().toString() == null || content.getText().toString() == "" || title.getText().toString().trim() == null) {
            Toast.makeText(getApplicationContext(), "글을 채워주세요 !! ", Toast.LENGTH_SHORT).show();
        }else{
            btSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Call<Void> call = rs1.userWriting(StaticId.id, title.getText().toString().trim(),getTime(),secret,content.getText().toString());//call객체
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

    }


    public String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
}
