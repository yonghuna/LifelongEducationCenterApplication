package com.example.lifelongeducationcenterapplication.MyPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.health.SystemHealthManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifelongeducationcenterapplication.Account.SignUpCheckFragment;
import com.example.lifelongeducationcenterapplication.CommunicationResult;
import com.example.lifelongeducationcenterapplication.Enrollment;
import com.example.lifelongeducationcenterapplication.Generalcurriculum.ForeignlanguagecourseActivity;
import com.example.lifelongeducationcenterapplication.Generalcurriculum.LearnmoreaboutforeignlanguagecoursesActivity;
import com.example.lifelongeducationcenterapplication.Lecture;
import com.example.lifelongeducationcenterapplication.MainActivity;
import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RegisterResult;
import com.example.lifelongeducationcenterapplication.RemoteService;
import com.example.lifelongeducationcenterapplication.StaticId;
import com.example.lifelongeducationcenterapplication.UserInfo;
import com.example.lifelongeducationcenterapplication.WebViewActivity;

import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class MyPage_CourseDetailsActivity extends AppCompatActivity {
    //마이페이지 수강내역
    Retrofit retrofit;//httpclient library
    RemoteService rs;//DB를 위한 인터페이스

    Retrofit retrofit1;//httpclient library
    RemoteService rs1;//DB를 위한 인터페이스


    LinearLayout linearLayout;
    List<Enrollment> enrollments; // 배열 객체 생성

    ListView listView;//리스트뷰
    MyAdapter adapter;
    TextView name, semester, certificate, payment;
    Button btDetail, btCancel, btPayment;
    RegisterResult registerResults;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("수강내역");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_my_page__course_details);

        listView = (ListView) findViewById(R.id.mypageCoursedetailslist);
        linearLayout = (LinearLayout) findViewById(R.id.gone);



        adapter = new MyAdapter();

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs = retrofit.create(RemoteService.class);

        retrofit1 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs1 = retrofit1.create(RemoteService.class);

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


    @Override
    protected void onResume() {
        Call<List<Enrollment>> call = rs.enrollment(StaticId.id);//call객체
        call.enqueue(new Callback<List<Enrollment>>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<List<Enrollment>> call, Response<List<Enrollment>> response) {
                if (response.isSuccessful()) {
                    enrollments = response.body();
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<Enrollment>> call, Throwable t) {
                System.out.println("내 강의 불러오기 실패" + call + " " + t);

            }
        });
        super.onResume();
    }




    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return enrollments.size();
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
            convertView = getLayoutInflater().inflate(R.layout.item_mypagecoursedetailslist, null);

            Enrollment enrollment = enrollments.get(position);
            name = convertView.findViewById(R.id.coursedetailsCoursename);
            semester = convertView.findViewById(R.id.coursedetailsYearsemester);
            certificate = convertView.findViewById(R.id.coursedetailsCertificateofCompletion);
            payment = convertView.findViewById(R.id.coursedetailspayment);

            btCancel = convertView.findViewById(R.id.btcoursedetail2);
            btDetail = convertView.findViewById(R.id.btcoursedetail1);
            btPayment = convertView.findViewById(R.id.btpayment);

            if(enrollment.getName() != "" ||  enrollment.getName() != null){
                name.setText(enrollment.getName());
            }else{
                name.setText("신청된 강좌가 없습니다.");
                btDetail.setVisibility(View.GONE);
                btCancel.setVisibility(View.GONE);
            }


            if (enrollment.getSubjectsemester() != 0) {
                semester.setText(enrollment.getSubjectyear() + " / " + enrollment.getSubjectsemester() + "학기");
            }

            if (enrollment.getCertificaterandomname() != null) {
                certificate.setText(enrollment.getCertificaterandomname());
            }else{
                certificate.setText("미 수료");
            }


            if (enrollment.getPayment().equals("결제완료")) {
                btPayment.setVisibility(View.GONE);
                payment.setText(enrollment.getPayment());
            }else{
                payment.setText(enrollment.getPayment());
            }


            btDetail.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    System.out.println("btDetail 클릭");
                    Intent intent = new Intent(getApplicationContext(), LearnmoreaboutforeignlanguagecoursesActivity.class);
                    intent.putExtra("number", enrollment.getSubjectnumber());
                    intent.putExtra("info", "myPage");
                    // 수강 내역
                    startActivity(intent);
                }

            });

            btPayment.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    System.out.println("btPayment 클릭");
                    Intent intent = new Intent(getApplicationContext(), MyPage_Payment.class);
                    intent.putExtra("number", enrollment.getSubjectnumber());
                    // 수강 내역
                    startActivity(intent);
                }

            });

            // 취소 시
            btCancel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (enrollment.getPayment().equals("결제완료")) {
                        Toast.makeText(getApplicationContext(), "결제완료를 하시면 환불을 위해 관리자와 문의해야 합니다. 041-530-8345", Toast.LENGTH_SHORT).show();
                    } else {

                        Call<RegisterResult> call = rs.userSubjectCancel(StaticId.id, enrollment.getSubjectnumber());//call객체
                        call.enqueue(new Callback<RegisterResult>() {//enqueue 메소드 실행
                            @Override
                            public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
                                if (response.isSuccessful()) {
                                    registerResults = response.body();
                                    if (response.isSuccessful()) {
                                        if (registerResults.getResult().equals("ok")) {
                                            Toast.makeText(getApplicationContext(), "수강취소 되었습니다", Toast.LENGTH_SHORT).show();
                                            change();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "오류 입니다", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                }
                            }

                            @Override
                            public void onFailure(Call<RegisterResult> call, Throwable t) {
                                System.out.println("수강 취소 실패" + call + " " + t);

                            }
                        });
                    }
                }
            });

            return convertView;
        }

    }
    public void change(){
        Call<List<Enrollment>> call = rs.enrollment(StaticId.id);//call객체
        call.enqueue(new Callback<List<Enrollment>>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<List<Enrollment>> call, Response<List<Enrollment>> response) {
                if (response.isSuccessful()) {
                    enrollments = response.body();
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<Enrollment>> call, Throwable t) {
                System.out.println("내 강의 불러오기 실패" + call + " " + t);

            }
        });
    }
}
