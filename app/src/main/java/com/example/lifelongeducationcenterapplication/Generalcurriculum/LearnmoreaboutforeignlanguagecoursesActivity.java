package com.example.lifelongeducationcenterapplication.Generalcurriculum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lifelongeducationcenterapplication.Lecture;
import com.example.lifelongeducationcenterapplication.LectureDetail;
import com.example.lifelongeducationcenterapplication.LectureWeek;
import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RemoteService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class LearnmoreaboutforeignlanguagecoursesActivity extends AppCompatActivity {
    //과정 상세보기

    Retrofit retrofit1; //httpclient library
    RemoteService rs1; //DB를 위한 인터페이스

    Retrofit retrofit2; //httpclient library
    RemoteService rs2; //DB를 위한 인터페이스


    List<LectureDetail> lecturesDetail; // 배열 객체 생성
    List<LectureWeek> lectureWeeks;
    ListView listLecture;//리스트뷰
    TextView textName, textPeriod, textProfessor, textTime, textFee, introduceProfessor, introduceLecture, textWeek1, textWeek2;
    Button btRegister;
    MyAdapter adapter;
    int number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("상세보기");
        setContentView(R.layout.activity_learnmoreaboutforeignlanguagecourses);
        textName = (TextView) findViewById(R.id.detailsforeignlanguagecourseName);
        textPeriod = (TextView)findViewById(R.id.detailsforeignlanguagecoursePeriod);
        textProfessor = (TextView)findViewById(R.id.Professor);
        textTime = (TextView)findViewById(R.id.detailsforeignlanguagecourseTime);
        textFee = (TextView)findViewById(R.id.detailsforeignlanguagecourseStudyfee);
        introduceProfessor = (TextView)findViewById(R.id.Instructorintroduction1);
        introduceLecture = (TextView)findViewById(R.id.LectureIntroduction1);

        btRegister = (Button) findViewById(R.id.Courseregistrationbt1);
        listLecture = (ListView) findViewById(R.id.Lecturelist);

        // number 값을 받아서 구분 해준다
        Intent intent = getIntent();
        number = intent.getIntExtra("number", 1); // pk로 구분

        adapter = new MyAdapter();

        retrofit1 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs1 = retrofit1.create(RemoteService.class);

        retrofit2 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs2 = retrofit2.create(RemoteService.class);


    }

    @Override
    protected void onResume() {

        // 다른 detail 부분 
        Call<List<LectureDetail>> call1 = rs1.lectureDetail(number);//detail
        call1.enqueue(new Callback<List<LectureDetail>>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<List<LectureDetail>> call, Response<List<LectureDetail>> response) {
                if(response.isSuccessful()){
                    lecturesDetail = response.body();
                    LectureDetail ld = lecturesDetail.get(0);
                    textName.setText(ld.getName());
                    textPeriod.setText("교육기간  : " + ld.getStartDate() +" ~ " + ld.getEndDate() + "(" + ld.getDatedetail() + ")");
                    textProfessor.setText(ld.getProfessor());
                    textTime.setText("교육 시간 : " +ld.getDayOfTheWeek() + " " + ld.getStartTime() + " ~ " + ld.getEndTime());
                    textFee.setText("수강료      : "+ld.getStudyFee());
                    System.out.println(ld.getBriefhistory());
                    if(ld.getBriefhistory() == null || ld.getBriefhistory().equals("")){
                        introduceProfessor.setText("");
                    }else{
                        introduceProfessor.setText(ld.getBriefhistory());
                    }
                    introduceLecture.setText(ld.getIntroduce());
                    if(ld.getStatus().equals("수강불가")){
                        btRegister.setBackgroundColor(Color.GRAY);
                        btRegister.setText("수강불가");
                        btRegister.setClickable(false);
                    }else{

                    }

                }

            }

            @Override
            public void onFailure(Call<List<LectureDetail>> call, Throwable t) {
                System.out.println("Detail 불러오기 실패" +call +" " + t);

            }
        });

        // week 주 내용 과정 불러오기
        Call<List<LectureWeek>> call2 = rs2.lectureWeek(number);//week
        call2.enqueue(new Callback<List<LectureWeek>>() {
            @Override
            public void onResponse(Call<List<LectureWeek>> call, Response<List<LectureWeek>> response) {
                if(response.isSuccessful()){
                    lectureWeeks = response.body();
                    adapter.notifyDataSetChanged();
                    listLecture.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<LectureWeek>> call, Throwable t) {
                System.out.println("Week 불러오기 실패" +call +" " + t);
            }
        });

        super.onResume();
    }

    
// week content convertview 에 올리기
    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return lectureWeeks.size();
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
            convertView = getLayoutInflater().inflate(R.layout.item_learnmoreaboutlanguageweeklecture,null);

            LectureWeek lectureWeek =  lectureWeeks.get(position);

            textWeek1 = (TextView)convertView.findViewById(R.id.textweek1);
            textWeek2 = (TextView)convertView.findViewById(R.id.textTrainingcontent1);

            textWeek1.setText(lectureWeek.getWeek());
            textWeek2.setText(lectureWeek.getContents());


            return convertView;
        }
    }
}