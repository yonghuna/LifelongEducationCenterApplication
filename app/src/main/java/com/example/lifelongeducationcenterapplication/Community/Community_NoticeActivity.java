package com.example.lifelongeducationcenterapplication.Community;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifelongeducationcenterapplication.Generalcurriculum.ForeignlanguagecourseActivity;
import com.example.lifelongeducationcenterapplication.Generalcurriculum.LearnmoreaboutforeignlanguagecoursesActivity;
import com.example.lifelongeducationcenterapplication.Lecture;
import com.example.lifelongeducationcenterapplication.LectureWeek;
import com.example.lifelongeducationcenterapplication.MyPage.MyPage_CourseDetailsActivity;
import com.example.lifelongeducationcenterapplication.Notice;
import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RegisterResult;
import com.example.lifelongeducationcenterapplication.RemoteService;
import com.example.lifelongeducationcenterapplication.StaticId;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class Community_NoticeActivity extends AppCompatActivity {
    //커뮤니티 공지사항

    Retrofit retrofit;//httpclient library
    RemoteService rs;//DB를 위한 인터페이스

    Retrofit retrofit2;//httpclient library
    RemoteService rs2;//DB를 위한 인터페이스


    List<Notice> notices; // 배열 객체 생성
    ListView listLecture;//리스트뷰

    TextView noticeNumber, noticeWriter, noticeDate, noticeTitle;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("공지사항");
        setContentView(R.layout.activity_community__notice);



        listLecture = (ListView) findViewById(R.id.noticeListview);


        adapter = new MyAdapter();

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs = retrofit.create(RemoteService.class);

        retrofit2 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs2 = retrofit2.create(RemoteService.class);

    }

    @Override
    protected void onResume() {

        Call<List<Notice>> call = rs.notice();//call객체
        call.enqueue(new Callback<List<Notice>>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<List<Notice>> call, Response<List<Notice>> response) {
                if(response.isSuccessful()){
                    notices = response.body();
                    adapter.notifyDataSetChanged();
                    listLecture.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<List<Notice>> call, Throwable t) {
                System.out.println("JSON 불러오기 실패" +call +" " + t);

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
            noticeWriter.setText("관리자");
            noticeNumber.setText(Integer.toString(notice.getNumber()));


            noticeTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Community_Bulletin_boardwritingActivity.class);
                    intent.putExtra("number", notice.getNumber());
                    startActivity(intent);
                }
            });

            return convertView;
        }
    }
}