package com.example.lifelongeducationcenterapplication.Creditbankingsystem;

import com.example.lifelongeducationcenterapplication.Account.Login;
import com.example.lifelongeducationcenterapplication.Generalcurriculum.ForeignlanguagecourseActivity;
import com.example.lifelongeducationcenterapplication.Generalcurriculum.LearnmoreaboutforeignlanguagecoursesActivity;
import com.example.lifelongeducationcenterapplication.Lecture;
import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RemoteService;
import com.example.lifelongeducationcenterapplication.StaticId;

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
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class CreditbanksystemEnrolmentActivity extends AppCompatActivity {
    //학점은행제 수강신청 액티비티

    Button btKorean, btAthletic, btOperation;

    Retrofit retrofit1;//httpclient library
    RemoteService rs1;//DB를 위한 인터페이스

    Retrofit retrofit2;//httpclient library
    RemoteService rs2;//DB를 위한 인터페이스

    Retrofit retrofit3;//httpclient library
    RemoteService rs3;//DB를 위한 인터페이스


    List<Lecture> lectures; // 배열 객체 생성
    ListView listLecture;//리스트뷰
    Button btDetail, btClassRg;
    TextView textName, textPeriod, textProfessor, textTime, textFee;
    MyAdapter adapter;

    int register = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("학점은행제 수강신청");
        setContentView(R.layout.activity_creditbanksystem_enrolment);


        btKorean = (Button) findViewById(R.id.bt_tab1);
        btAthletic = (Button) findViewById(R.id.bt_tab2);
        btOperation = (Button) findViewById(R.id.bt_tab3);
        listLecture = (ListView) findViewById(R.id.registerListView);


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

        btKorean.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register = 1;
                notifychangelist(register);
            }
        });

        btAthletic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register = 2;
                notifychangelist(2);
            }
        });

        btOperation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register = 3;
                notifychangelist(3);
            }
        });

    }

    @Override
    protected void onResume() {
        notifychangelist(register);

        super.onResume();
    }

    public void notifychangelist(int register){
        if (register == 1) {
            Call<List<Lecture>> call1 = rs1.lecture("외국어로서의 한국어학");//call객체
            call1.enqueue(new Callback<List<Lecture>>() {//enqueue 메소드 실행
                @Override
                public void onResponse(Call<List<Lecture>> call, Response<List<Lecture>> response) {
                    if (response.isSuccessful()) {
                        lectures = response.body();
                        listLecture.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    }
                }

                @Override
                public void onFailure(Call<List<Lecture>> call, Throwable t) {
                    System.out.println("학점은행제 불러오기 실패" + call + " " + t);

                }
            });
        } else if (register == 2) {
            Call<List<Lecture>> call2 = rs2.lecture("체육학");//call객체
            call2.enqueue(new Callback<List<Lecture>>() {//enqueue 메소드 실행
                @Override
                public void onResponse(Call<List<Lecture>> call, Response<List<Lecture>> response) {
                    if (response.isSuccessful()) {
                        lectures = response.body();
                        listLecture.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    }
                }

                @Override
                public void onFailure(Call<List<Lecture>> call, Throwable t) {
                    System.out.println("학점은행제 불러오기 실패" + call + " " + t);

                }
            });

        } else if (register == 3) {
            Call<List<Lecture>> call3 = rs3.lecture("경영학");//call객체
            call3.enqueue(new Callback<List<Lecture>>() {//enqueue 메소드 실행
                @Override
                public void onResponse(Call<List<Lecture>> call, Response<List<Lecture>> response) {
                    if (response.isSuccessful()) {
                        lectures = response.body();
                        listLecture.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    }
                }

                @Override
                public void onFailure(Call<List<Lecture>> call, Throwable t) {
                    System.out.println("학점은행제 불러오기 실패" + call + " " + t);
                }
            });
        }
    }


    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return lectures.size();
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
            convertView = getLayoutInflater().inflate(R.layout.item_foreignlanguagecourse, null);

            Lecture lc = lectures.get(position);

            String day = lc.getDayOfTheWeek();
            String name = lc.getName();
            String startDate = lc.getStartDate();
            String endDate = lc.getEndDate();
            String startTime = lc.getStartTime();
            String endTime = lc.getEndTime();
            String professor = lc.getProfessor();
            String studyFee = lc.getStudyFee();
            String status = lc.getStatus();
            int number = lc.getNumber();


            textName = convertView.findViewById(R.id.foreignlanguagecourseName);
            textPeriod = convertView.findViewById(R.id.foreignlanguagecourseLecturePeriod);
            textProfessor = convertView.findViewById(R.id.foreignlanguagecourseLectureProfessor);
            textTime = convertView.findViewById(R.id.foreignlanguagecourseLectureTime);
            textFee = convertView.findViewById(R.id.foreignlanguagecourseLectureStudyfee);

            btClassRg = convertView.findViewById(R.id.foreignlanguagecourseEnrolment1);
            btDetail = convertView.findViewById(R.id.foreignlanguagecourseEnrolment2);


            if(day =="" || day == null){
                day = "";
            }

            textName.setText("강좌명  " + name);
            textPeriod.setText("・교육기간  " + startDate + " ~ " + endDate);
            textProfessor.setText("・교수진     " + professor);
            textTime.setText("・수업시간  " + day + " " + startTime + " ~ " + endTime);
            textFee.setText("・학습비     " + studyFee);


            // 수강 불가시 수강신청 버튼 변경
            if (status.equals("수강불가")) {
                btClassRg.setBackgroundColor(Color.GRAY);
                btClassRg.setText("수강불가");
                btClassRg.setClickable(false);
            }

            // 상세보기 
            btDetail.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), LearnmoreaboutforeignlanguagecoursesActivity.class);
                    intent.putExtra("number", number);
                    startActivity(intent);
                }

            });
            // 일반과정 수강신청
            btClassRg.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (StaticId.id.equals("") || StaticId.id == null) {
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        Toast.makeText(getApplicationContext(), "로그인을 해야 수강신청이 가능합니다.", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    } else {
                        //
                    }
                }
            });
            return convertView;
        }
    }

}