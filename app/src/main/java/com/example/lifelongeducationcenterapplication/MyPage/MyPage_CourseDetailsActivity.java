package com.example.lifelongeducationcenterapplication.MyPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.health.SystemHealthManager;
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
    Button btDetail, btCancel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("수강내역");
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

    @Override
    protected void onResume() {

        Call<List<Enrollment>> call = rs.enrollment(StaticId.id);//call객체
        call.enqueue(new Callback<List<Enrollment>>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<List<Enrollment>> call, Response<List<Enrollment>> response) {
                if (response.isSuccessful()) {
                    enrollments = response.body();
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);
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
            int getName = enrollment.getSubjectnumber();
            semester = convertView.findViewById(R.id.coursedetailsYearsemester);
            certificate = convertView.findViewById(R.id.coursedetailsCertificateofCompletion);
            payment = convertView.findViewById(R.id.coursedetailspayment);

            btCancel = convertView.findViewById(R.id.btcoursedetail1);
            btDetail = convertView.findViewById(R.id.btcoursedetail2);

            if(getName != 0){
                Call<Lecture> call1 = rs1.lectureName(getName);//call객체
                call1.enqueue(new Callback<Lecture>() {//enqueue 메소드 실행
                    @Override
                    public void onResponse(Call<Lecture> call, Response<Lecture> response) {
                        if (response.isSuccessful()) {
                            System.out.println("------------" + getName);
                            Lecture lecture = response.body();
                            name.setText(lecture.getName());
                        }
                    }

                    @Override
                    public void onFailure(Call<Lecture> call, Throwable t) {
                        System.out.println("강좌 이름 불러오기 실패" + call + " " + t);
                    }
                });
            }else{
                name.setText("수강신청한 강좌가 없습니다.");
                btCancel.setVisibility(View.GONE);
                btDetail.setVisibility(View.GONE);
            }

            if (enrollment.getSubjectsemester() != 0) {
                semester.setText(enrollment.getSubjectyear() + " / " + enrollment.getSubjectsemester());
            }

            if (enrollment.getCertificaterandomname() != "" || enrollment.getCertificaterandomname() != null) {
                certificate.setText(enrollment.getCertificaterandomname());
            }

            if (enrollment.getPayment() != "" || enrollment.getPayment() != null) {
                payment.setText(enrollment.getPayment());
            }


            btDetail.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), LearnmoreaboutforeignlanguagecoursesActivity.class);
                    intent.putExtra("number", enrollment.getSubjectnumber());
                    // 수강 내역
                    startActivity(intent);
                }

            });
            btCancel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //?
                }
            });

            return convertView;
        }

    }
}
