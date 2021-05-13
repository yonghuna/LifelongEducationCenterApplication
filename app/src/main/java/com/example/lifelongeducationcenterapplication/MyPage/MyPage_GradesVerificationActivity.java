package com.example.lifelongeducationcenterapplication.MyPage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lifelongeducationcenterapplication.Enrollment;
import com.example.lifelongeducationcenterapplication.Lecture;
import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RemoteService;
import com.example.lifelongeducationcenterapplication.StaticId;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class MyPage_GradesVerificationActivity extends AppCompatActivity {
    //마이페이지 성적확인

    TextView year, name, result;
    ListView listView;
    List<Enrollment> enrollments;
    MyAdapter adapter;

    Retrofit retrofit;//httpclient library
    RemoteService rs;//DB를 위한 인터페이스

    int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("성적확인");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_my_page__grades_verification);
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs = retrofit.create(RemoteService.class);
        listView = (ListView) findViewById(R.id.mypagegradeslist);
        adapter = new MyAdapter();
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

    @Override   //액셔바 홈버튼
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    protected void onResume() {
        Call<List<Enrollment>> call1 = rs.enrollment(StaticId.id);//call객체
        call1.enqueue(new Callback<List<Enrollment>>() {//enqueue 메소드 실행
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
                System.out.println("JSON 불러오기 실패" + call + " " + t);

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
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.item_mypagegradeschecklist, null);
            Enrollment enrollment = enrollments.get(position);
            name = (TextView) convertView.findViewById(R.id.Coursenamelist);
            year = (TextView) convertView.findViewById(R.id.gragdsYearsemesterlist);
            result = (TextView) convertView.findViewById(R.id.resultlist);

            if(enrollment.getGrade() != null){
                System.out.println(enrollment.getGrade());
                name.setText(enrollment.getName());
                year.setText(enrollment.getSubjectyear() + " / " + enrollment.getSubjectsemester());
                result.setText(enrollment.getGrade());
            }else{
                if(number == 1){ // 처음께 조회 결과가 없다면 다음꺼는 없애버림 
                    name.setVisibility(View.GONE);
                    year.setVisibility(View.GONE);
                    result.setVisibility(View.GONE);
                }else{ // 조회결과없습니다 하고 안보이게함
                    name.setText("조회 결과가 없습니다");
                    year.setVisibility(View.INVISIBLE);
                    result.setVisibility(View.INVISIBLE);
                    number++;
                }

            }

            return convertView;
        }
    }
}