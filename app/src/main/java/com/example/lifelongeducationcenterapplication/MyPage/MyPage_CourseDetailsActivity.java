package com.example.lifelongeducationcenterapplication.MyPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.lifelongeducationcenterapplication.Generalcurriculum.ForeignlanguagecourseActivity;
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


    List<Lecture> lectures; // 배열 객체 생성
    ListView listLecture;//리스트뷰
    MyAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("수강내역");
        setContentView(R.layout.activity_my_page__course_details);

        listLecture = (ListView) findViewById(R.id.foreignlanguagecourselistLecture);


        adapter = new MyAdapter();

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs = retrofit.create(RemoteService.class);

    }

    @Override
    protected void onResume() {

        Call<List<Lecture>> call = rs.lecture("외국어과정");//call객체
        call.enqueue(new Callback<List<Lecture>>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<List<Lecture>> call, Response<List<Lecture>> response) {
                if (response.isSuccessful()) {
                    lectures = response.body();
                    adapter.notifyDataSetChanged();
                    listLecture.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Lecture>> call, Throwable t) {
                System.out.println("JSON 불러오기 실패" + call + " " + t);

            }
        });
        super.onResume();
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
            int subjectsemester = lc.getSemester();
            int year = lc.getYear();
            int number = lc.getNumber();
            return convertView;
        }

    }
}
