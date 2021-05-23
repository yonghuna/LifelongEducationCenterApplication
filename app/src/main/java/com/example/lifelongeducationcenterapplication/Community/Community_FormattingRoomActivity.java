
package com.example.lifelongeducationcenterapplication.Community;


import androidx.appcompat.app.AppCompatActivity;

import com.example.lifelongeducationcenterapplication.Notice;
import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RemoteService;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class Community_FormattingRoomActivity extends AppCompatActivity {
    //커뮤니티서식 자료실

    Retrofit retrofit;//httpclient library
    RemoteService rs;//DB를 위한 인터페이스

    Retrofit retrofit2;//httpclient library
    RemoteService rs2;//DB를 위한 인터페이스

    LinearLayout click;
    List<Notice> notices; // 배열 객체 생성
    ListView listLecture;//리스트뷰
    ImageView imageView;
    TextView noticeNumber, noticeWriter, noticeDate, noticeTitle;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("서식자료실");

        setContentView(R.layout.activity_community__formatting_room);


        listLecture = (ListView) findViewById(R.id.referenceRoomListview);


        adapter = new MyAdapter();

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs = retrofit.create(RemoteService.class);

        retrofit2 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs2 = retrofit2.create(RemoteService.class);
    }



    @Override   //뒤로가기
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
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
    @Override
    protected void onResume() {

        Call<List<Notice>> call = rs.format();//call객체
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
            click = (LinearLayout) convertView.findViewById(R.id.click);
            noticeNumber = (TextView)convertView.findViewById(R.id.QandACountlecture); // 1 , 2 , 3
            noticeDate = (TextView)convertView.findViewById(R.id.QandADateCreatedlecture); // 날짜
            noticeWriter = (TextView)convertView.findViewById(R.id.QandAWriterlecture); // 글쓴이
            noticeTitle = (TextView)convertView.findViewById(R.id.QandANamelecture); // 제목
            imageView = (ImageView)  convertView.findViewById(R.id.imageView);


            imageView.setVisibility(View.GONE);
            String[] day = notice.getReportingdate().split(" ");

            noticeTitle.setText(notice.getTitle().trim());
            noticeDate.setText(day[0]);
            noticeWriter.setText("관리자");
            noticeNumber.setText(Integer.toString(notice.getNumber()));


            click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Community_FormattingRoomContentActivity.class);
                    intent.putExtra("number", notice.getNumber());
                    startActivity(intent);
                }
            });

            return convertView;
        }
    }





}
