package com.example.lifelongeducationcenterapplication.Creditbankingsystem;

import com.example.lifelongeducationcenterapplication.Account.Login;
import com.example.lifelongeducationcenterapplication.Enrollment;
import com.example.lifelongeducationcenterapplication.Generalcurriculum.ForeignlanguagecourseActivity;
import com.example.lifelongeducationcenterapplication.Generalcurriculum.LearnmoreaboutforeignlanguagecoursesActivity;
import com.example.lifelongeducationcenterapplication.Lecture;
import com.example.lifelongeducationcenterapplication.MyPage.MyPage_CourseDetailsActivity;
import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RegisterResult;
import com.example.lifelongeducationcenterapplication.RemoteService;
import com.example.lifelongeducationcenterapplication.StaticId;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
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

    Retrofit retrofit4;//httpclient library
    RemoteService rs4;//DB를 위한 인터페이스


    List<Lecture> lectures; // 배열 객체 생성
    ListView listLecture;//리스트뷰
    Button btDetail, btClassRg;
    TextView textName, textPeriod, textProfessor, textTime, textFee;
    MyAdapter adapter;
    List<Enrollment> enrollments = new ArrayList();
    String info = "수강신청";
    int register = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("학점은행제 수강신청");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

        retrofit4 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs4 = retrofit4.create(RemoteService.class);


        btKorean.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                register = 1;
                notifychangelist(register);
            }
        });

        btAthletic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register = 2;
                notifychangelist(register);
            }
        });

        btOperation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register = 3;
                notifychangelist(register);
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
    @Override
    protected void onResume() {


        notifychangelist(register);

        super.onResume();
    }

    public void notifychangelist(int register) {
        if (register == 1) {
            Call<List<Enrollment>> call2 = rs4.enrollment(StaticId.id);//call객체
            call2.enqueue(new Callback<List<Enrollment>>() {//enqueue 메소드 실행
                @Override
                public void onResponse(Call<List<Enrollment>> call, Response<List<Enrollment>> response) {
                    if (response.isSuccessful()) {
                        enrollments = response.body();
                    }
                }

                @Override
                public void onFailure(Call<List<Enrollment>> call, Throwable t) {
                    System.out.println("subjectnumber" + call + " " + t);

                }
            });


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
            Call<List<Enrollment>> call2 = rs4.enrollment(StaticId.id);//call객체
            call2.enqueue(new Callback<List<Enrollment>>() {//enqueue 메소드 실행
                @Override
                public void onResponse(Call<List<Enrollment>> call, Response<List<Enrollment>> response) {
                    if (response.isSuccessful()) {
                        enrollments = response.body();
                    }
                }

                @Override
                public void onFailure(Call<List<Enrollment>> call, Throwable t) {
                    System.out.println("subjectnumber" + call + " " + t);

                }
            });


            Call<List<Lecture>> call1 = rs2.lecture("체육학");//call객체
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

        } else if (register == 3) {

            Call<List<Enrollment>> call2 = rs4.enrollment(StaticId.id);//call객체
            call2.enqueue(new Callback<List<Enrollment>>() {//enqueue 메소드 실행
                @Override
                public void onResponse(Call<List<Enrollment>> call, Response<List<Enrollment>> response) {
                    if (response.isSuccessful()) {
                        enrollments = response.body();
                    }
                }

                @Override
                public void onFailure(Call<List<Enrollment>> call, Throwable t) {
                    System.out.println("subjectnumber" + call + " " + t);

                }
            });

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
            Enrollment enrollment;


            String day = lc.getDayOfTheWeek();
            String name = lc.getName();
            String startDate = lc.getStartDate();
            String endDate = lc.getEndDate();
            String startTime = lc.getStartTime();
            String endTime = lc.getEndTime();
            String professor = lc.getProfessor();
            String studyFee = lc.getStudyFee();
            String status = lc.getStatus();
            String course = lc.getDivision();
            int subjectsemester = lc.getSemester();
            int year = lc.getYear();
            int number = lc.getNumber();

            /////////////////////////////////////////


            textName = convertView.findViewById(R.id.foreignlanguagecourseName);
            textPeriod = convertView.findViewById(R.id.foreignlanguagecourseLecturePeriod);
            textProfessor = convertView.findViewById(R.id.foreignlanguagecourseLectureProfessor);
            textTime = convertView.findViewById(R.id.foreignlanguagecourseLectureTime);
            textFee = convertView.findViewById(R.id.foreignlanguagecourseLectureStudyfee);

            btClassRg = convertView.findViewById(R.id.foreignlanguagecourseEnrolment1);
            btDetail = convertView.findViewById(R.id.foreignlanguagecourseEnrolment2);


            textName.setText("강좌명  " + name);
            textPeriod.setText("・교육기간  " + startDate + " ~ " + endDate);
            textProfessor.setText("・교수진     " + professor);
            textTime.setText("・수업시간  " + day + " " + startTime + " ~ " + endTime);
            textFee.setText("・학습비     " + studyFee);


            // 수강 불가시 수강신청 버튼 변경

            for (int i = 0; i < enrollments.size(); i++) {
                enrollment = enrollments.get(i);
                if (enrollment.getSubjectnumber() == lc.getNumber()) {
                    btClassRg.setBackgroundColor(Color.GRAY);
                    btClassRg.setText("신청내역");
                    info = "신청내역";
                }
            }
            // 상세보기
            btDetail.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), LearnmoreaboutforeignlanguagecoursesActivity.class);
                    intent.putExtra("number", number);
                    intent.putExtra("info", info);
                    // 해야되는 부분 수강신청을 위해 해야됨
                    startActivity(intent);
                }

            });
            // 일반과정 수강신청

            if (status.equals("수강불가")) {
                btClassRg.setBackgroundColor(Color.GRAY);
                btClassRg.setText("수강불가");
                btClassRg.setOnClickListener(null);
                btClassRg.setClickable(false);
            } else {
                btClassRg.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        if (StaticId.id == null) {
                            Toast.makeText(getApplicationContext(), "로그인을 해야 수강신청이 가능합니다.", Toast.LENGTH_LONG).show();
                        } else {
                            if(StaticId.course.equals("학점은행제과정")) {
                                Call<RegisterResult> call = rs2.userSubjectRegister(StaticId.id, number, year, subjectsemester, course);//call객체
                                call.enqueue(new Callback<RegisterResult>() {//enqueue 메소드 실행
                                    @Override
                                    public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
                                        if (response.isSuccessful()) {
                                            RegisterResult registerResult = response.body();
                                            if (registerResult.getResult().equals("ok")) {
                                                Toast.makeText(getApplicationContext(), "수강신청이 되었습니다. " +register, Toast.LENGTH_SHORT).show();
                                                notifychangelist(register);
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
                            }else{
                                Toast.makeText(getApplicationContext(), "일반수강자는 신청하지 못합니다.", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }


                });
            }
            return convertView;
        }
    }

}