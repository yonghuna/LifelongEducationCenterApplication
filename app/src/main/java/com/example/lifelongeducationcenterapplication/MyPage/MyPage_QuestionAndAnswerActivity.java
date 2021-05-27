package com.example.lifelongeducationcenterapplication.MyPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifelongeducationcenterapplication.Community.Community_Bulletin_boardwritingActivity;
import com.example.lifelongeducationcenterapplication.Community.Community_DifferentMember;
import com.example.lifelongeducationcenterapplication.Community.Community_QAmodifyAndremoveActivity;
import com.example.lifelongeducationcenterapplication.Community.Community_QuestionAndAnswerActivity;
import com.example.lifelongeducationcenterapplication.Notice;
import com.example.lifelongeducationcenterapplication.PostNumber;
import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RemoteService;
import com.example.lifelongeducationcenterapplication.StaticId;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class MyPage_QuestionAndAnswerActivity extends AppCompatActivity {
    //마이페이지 질의응답

    ListView listView;
    List<Notice> notices;
    TextView noticeNumber, noticeDate, noticeWriter, noticeTitle;

    Retrofit retrofit1;//httpclient library
    RemoteService rs1;//DB를 위한 인터페이스
    FloatingActionButton btWrite;
    MyAdapter adapter;
    PostNumber postNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("비밀 질의응답");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_my_page__question_and_answer);

        btWrite = (FloatingActionButton) findViewById(R.id.btwriting);
        listView = (ListView) findViewById(R.id.questionandanswerlist);
        retrofit1 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs1 = retrofit1.create(RemoteService.class);
        adapter = new MyAdapter();



        btWrite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (StaticId.id == null || StaticId.id == "") {
                    Toast.makeText(MyPage_QuestionAndAnswerActivity.this, "로그인을 하세요", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), Community_Bulletin_boardwritingActivity.class);
                    intent.putExtra("secret", "true");
                    intent.putExtra("info", "글작성");
                    startActivity(intent);
                }
                // 글 작성
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
    protected void onResume() {
        Call<List<Notice>> call1 = rs1.secret(StaticId.id);//call객체
        call1.enqueue(new Callback<List<Notice>>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<List<Notice>> call, Response<List<Notice>> response) {
                if (response.isSuccessful()) {
                    notices = response.body();
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Notice>> call, Throwable t) {
                System.out.println("JSON 불러오기 실패" + call + " " + t);

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

            convertView = getLayoutInflater().inflate(R.layout.item_community_question_and_answerlist, null);

            Notice notice = notices.get(position);

            noticeNumber = (TextView) convertView.findViewById(R.id.QandACountlecture); // 1 , 2 , 3
            noticeDate = (TextView) convertView.findViewById(R.id.QandADateCreatedlecture); // 날짜
            noticeWriter = (TextView) convertView.findViewById(R.id.QandAWriterlecture); // 글쓴이
            noticeTitle = (TextView) convertView.findViewById(R.id.QandANamelecture); // 제목


            String[] day = notice.getReportingdate().split(" ");

            postNumber(notice.getTitle(),notice.getNumber());
            noticeDate.setText(day[0]);
            noticeWriter.setText(notice.getName());
            noticeNumber.setText(Integer.toString(notice.getNumber()));


            noticeTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    System.out.println(StaticId.id + " ----" + notice.getId());

                    Intent intent = new Intent(getApplicationContext(), Community_DifferentMember.class);
                    intent.putExtra("number", notice.getNumber());
                    intent.putExtra("id", StaticId.id);
                    startActivity(intent);

                }
            });
            return convertView;
        }
    }

    public void postNumber(String title, int number){

        Call<PostNumber> call1 = rs1.postNumber(number);//call객체
        call1.enqueue(new Callback<PostNumber>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<PostNumber> call, Response<PostNumber> response) {
                if (response.isSuccessful()) {
                    postNumber = response.body();
                    noticeTitle.setText(title + " [" + postNumber.getCount() +"]");
                }
            }

            @Override
            public void onFailure(Call<PostNumber> call, Throwable t) {
                System.out.println("JSON 불러오기 실패" + call + " " + t);

            }
        });

    }
}