package com.example.lifelongeducationcenterapplication.Generalcurriculum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifelongeducationcenterapplication.Account.Login;
import com.example.lifelongeducationcenterapplication.Enrollment;
import com.example.lifelongeducationcenterapplication.Lecture;
import com.example.lifelongeducationcenterapplication.LectureDetail;
import com.example.lifelongeducationcenterapplication.LectureWeek;
import com.example.lifelongeducationcenterapplication.MyPage.MyPage_CourseDetailsActivity;
import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RegisterResult;
import com.example.lifelongeducationcenterapplication.RemoteService;
import com.example.lifelongeducationcenterapplication.StaticId;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;
import static com.example.lifelongeducationcenterapplication.StaticId.course;

public class LearnmoreaboutforeignlanguagecoursesActivity extends AppCompatActivity {
    //과정 상세보기

    Retrofit retrofit1; //httpclient library
    RemoteService rs1; //DB를 위한 인터페이스

    Retrofit retrofit2; //httpclient library
    RemoteService rs2; //DB를 위한 인터페이스

    Retrofit retrofit3;//httpclient library
    RemoteService rs3;//DB를 위한 인터페이스

    List<LectureDetail> lecturesDetail; // 배열 객체 생성
    List<LectureWeek> lectureWeeks;

    ListView listLecture;//리스트뷰

    TextView textName, textPeriod, textProfessor, textTime, textFee, introduceProfessor, introduceLecture, textWeek1, textWeek2;
    Button btRegister;
    MyAdapter adapter;
    // 받아온 값
    int number;
    String info;
    int year, subjectsemester;

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
        info = intent.getStringExtra("info"); // 어디서 날라왓는지 구분

        adapter = new MyAdapter();

        retrofit1 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs1 = retrofit1.create(RemoteService.class);

        retrofit2 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs2 = retrofit2.create(RemoteService.class);

        retrofit3 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs3 = retrofit3.create(RemoteService.class);

        if(info.equals("신청내역")){
            btRegister.setText("신청내역");
            btRegister.setBackgroundColor(Color.GRAY);
        }else if(info.equals("myPage")){
            btRegister.setVisibility(View.GONE);
        }

        setListViewHeightBasedOnChildren(listLecture); ///setadapter한뒤 해당메소드를 실행해야함.(scrollview안에 listview를 넣으면 크기가 잘려지는 문제를 해결.)
        pushRegister();
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
                    year = ld.getYaer();
                    subjectsemester = ld.getSemester();
                    if(ld.getDayOfTheWeek() == ("") || ld.getDayOfTheWeek() == null){
                        ld.setDayOfTheWeek("");
                    }
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

    public static void setListViewHeightBasedOnChildren(ListView listView) { ///setadapter한뒤 해당메소드를 실행해야함.(scrollview안에 listview를 넣으면 크기가 잘려지는 문제를 해결.)
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
    public void pushRegister(){
        btRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(StaticId.id == null){
                    Toast.makeText(getApplicationContext(), "로그인을 해야 수강신청이 가능합니다.", Toast.LENGTH_LONG).show();

                }else{
                    if(info == "신청내역" || info.equals("신청내역")){
                        Intent intent1 = new Intent(getApplicationContext(), MyPage_CourseDetailsActivity.class);
                        startActivity(intent1);
                    }
                    else{
                        Call<RegisterResult> call = rs2.userSubjectRegister(StaticId.id, number, year, subjectsemester, course);//call객체
                        call.enqueue(new Callback<RegisterResult>() {//enqueue 메소드 실행
                            @Override
                            public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
                                if (response.isSuccessful()) {
                                    RegisterResult registerResult = response.body();
                                    if (registerResult.getResult().equals("ok")) {
                                        Toast.makeText(getApplicationContext(), "수강신청이 되었습니다.", Toast.LENGTH_LONG).show();
                                        Intent intent1 = new Intent(getApplicationContext(), ForeignlanguagecourseActivity.class);
                                        startActivity(intent1);
                                    } else {
                                        Intent intent1 = new Intent(getApplicationContext(), MyPage_CourseDetailsActivity.class);
                                        startActivity(intent1);
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<RegisterResult> call, Throwable t) {
                                System.out.println("일반과정 수강신청 실패 " + call + " " + t);

                            }
                        });
                    }
                }


            }
        });
    }

}