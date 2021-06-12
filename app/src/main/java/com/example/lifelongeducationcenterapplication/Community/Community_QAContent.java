package com.example.lifelongeducationcenterapplication.Community;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearSnapHelper;

import com.example.lifelongeducationcenterapplication.Notice;
import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RemoteService;
import com.example.lifelongeducationcenterapplication.StaticId;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;
// QNA 안 내용물
public class Community_QAContent extends AppCompatActivity {
    Button btModify, btRemove;
    TextView num, writer, time, title, content, time2, comment,textview;
    LinearLayout file;
    Notice notice;
    Retrofit retrofit1; //httpclient library
    RemoteService rs1; //DB를 위한 인터페이스

    ListView listview;
    List<Notice> notices;
    Retrofit retrofit2; //httpclient library
    RemoteService rs2; //DB를 위한 인터페이스
    MyAdapter adapter;
    int number;
    String id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("글 내용");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activty_community_question_and_answer_content);


        //
        Intent intent = getIntent(); /*데이터 수신*/
        number = intent.getIntExtra("number", 1); // pk로 구분
        id = intent.getStringExtra("id"); // pk로 구분

        adapter = new MyAdapter();
        findId();
        setRetrofit();

        //스크롤
        textview = findViewById(R.id.write_content_tv);
        textview.setMovementMethod(new ScrollingMovementMethod());

        btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (StaticId.id == null) {
                    // 아닐경우
                    Toast.makeText(getApplicationContext(), "로그인 하시오.", Toast.LENGTH_SHORT).show();
                }else if(!StaticId.id.equals(id)){
                    Toast.makeText(getApplicationContext(), "본인이 아닙니다.", Toast.LENGTH_SHORT).show();
                }
                else if (StaticId.id.equals(id)) {
                    Call<Void> call = rs2.userRemove(number, StaticId.id);//call객체
                    call.enqueue(new Callback<Void>() {//enqueue 메소드 실행
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "삭제 되었습니다.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            System.out.println("글 삭제 실패" + call + " " + t);
                        }
                    });
                }
            }
        });


        btModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StaticId.id == null) {
                    // 아닐경우
                    Toast.makeText(getApplicationContext(), "로그인 하시오.", Toast.LENGTH_SHORT).show();
                }
                else if(!StaticId.id.equals(id)){
                    Toast.makeText(getApplicationContext(), "본인이 아닙니다.", Toast.LENGTH_SHORT).show();
                }
                else if (StaticId.id.equals(id)) {
                    Intent intent = new Intent(getApplicationContext(), Community_QAmodifyAndremoveActivity.class);
                    intent.putExtra("number", number);
                    startActivity(intent);
                }
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

    public void findId() {
        title = (TextView) findViewById(R.id.write_title_tv);
        content = (TextView) findViewById(R.id.write_content_tv);
        btModify = (Button) findViewById(R.id.modfiy);
        btRemove = (Button) findViewById(R.id.remove);

        num = (TextView) findViewById(R.id.number);
        writer = (TextView) findViewById(R.id.who);
        time = (TextView) findViewById(R.id.time);
        listview = (ListView) findViewById(R.id.contentListView);



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
                    content.setText(Html.fromHtml(notice.getContents()).toString());

                }
            }

            @Override
            public void onFailure(Call<Notice> call, Throwable t) {
                System.out.println("Q N A 읽기" + call + " " + t);
            }
        });

        Call<List<Notice>> call1 = rs2.comment(number);//call객체
        call1.enqueue(new Callback<List<Notice>>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<List<Notice>> call, Response<List<Notice>> response) {
                if(response.isSuccessful()){
                    notices = response.body();
                    adapter.notifyDataSetChanged();
                    listview.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<List<Notice>> call, Throwable t) {
                System.out.println("comment 오류" +call +" " + t);

            }
        });
        super.onResume();
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return notices.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.item_comment,null);

            Notice notice =  notices.get(position);

            if(notice.getDate() == null || notice.getContents() == null){
                listview.setVisibility(View.GONE);
            }
            time2 = (TextView)convertView.findViewById(R.id.time); // 1 , 2 , 3
            comment = (TextView)convertView.findViewById(R.id.editTextTextcoment); // 날짜


            time2.setText(notice.getDate());
            comment.setText(Html.fromHtml(notice.getContents()).toString());

            return convertView;
        }
    }
}