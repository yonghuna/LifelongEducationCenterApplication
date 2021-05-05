package com.example.lifelongeducationcenterapplication.Community;



import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RemoteService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class Community_Bulletin_boardwritingActivity extends AppCompatActivity {
    // 글 쓰기
    EditText title, content;
    Button btModify, btSave;

    Retrofit retrofit1; //httpclient library
    RemoteService rs1; //DB를 위한 인터페이스

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("글 작성");
        setContentView(R.layout.activity_writing);
        retrofit1 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs1 = retrofit1.create(RemoteService.class);
        findId();


    }

    public void findId(){
        title = (EditText) findViewById(R.id.write_content_tv);
        content = (EditText) findViewById(R.id.write_content_tv);
        btModify = (Button) findViewById(R.id.write_modify);
        btSave = (Button) findViewById(R.id.write_post_btn);
    }

}
