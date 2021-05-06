package com.example.lifelongeducationcenterapplication.Community;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifelongeducationcenterapplication.Notice;
import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RemoteService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class Community_QuestionAndAnswerActivity extends AppCompatActivity {
    //커뮤니티 1:1질의응답
    ListView listView;
    List<Notice> notices;
    TextView noticeNumber, noticeDate, noticeWriter, noticeTitle;

    Retrofit retrofit1;//httpclient library
    RemoteService rs1;//DB를 위한 인터페이스
    FloatingActionButton btWrite;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("1:1질의응답");
        setContentView(R.layout.activity_community__question_and_answer);
        listView = (ListView) findViewById(R.id.QandAListview);
        btWrite = (FloatingActionButton) findViewById(R.id.btwriting);
        retrofit1 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs1 = retrofit1.create(RemoteService.class);
        adapter = new MyAdapter();

        btWrite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Community_Bulletin_boardwritingActivity.class);
                intent.putExtra("secret", "false");
                intent.putExtra("info", "글작성");
                startActivity(intent);
                // 글 작성
            }

        });

    }

    protected void onResume() {
        Call<List<Notice>> call1 = rs1.notSecret();//call객체
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
                System.out.println("JSON 불러오기 실패 외국어 과정" + call + " " + t);

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

            convertView = getLayoutInflater().inflate(R.layout.item_community_question_and_answerlist,null);

            Notice notice =  notices.get(position);

            noticeNumber = (TextView)convertView.findViewById(R.id.QandACountlecture); // 1 , 2 , 3
            noticeDate = (TextView)convertView.findViewById(R.id.QandADateCreatedlecture); // 날짜
            noticeWriter = (TextView)convertView.findViewById(R.id.QandAWriterlecture); // 글쓴이
            noticeTitle = (TextView)convertView.findViewById(R.id.QandANamelecture); // 제목


            String[] day = notice.getReportingdate().split(" ");

            noticeTitle.setText(notice.getTitle());
            noticeDate.setText(day[0]);
            noticeWriter.setText(notice.getName());
            noticeNumber.setText(Integer.toString(notice.getNumber()));


            noticeTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Community_NoticeContentActivity.class);
                    intent.putExtra("number", notice.getNumber());
                    intent.putExtra("secret", "false");
                    intent.putExtra("info", "글목록");
                    startActivity(intent);
                }
            });
            return convertView;
        }
    }
}