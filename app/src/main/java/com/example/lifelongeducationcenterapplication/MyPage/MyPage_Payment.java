
package com.example.lifelongeducationcenterapplication.MyPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifelongeducationcenterapplication.Enrollment;
import com.example.lifelongeducationcenterapplication.Notice;
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

public class MyPage_Payment extends AppCompatActivity {
    int number1;
    Retrofit retrofit;//httpclient library
    RemoteService rs;//DB를 위한 인터페이스
    TextView name, title, pay, number;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_my_page_payment);
        getSupportActionBar().setTitle("결제");
        Intent intent = getIntent();
        number1 = intent.getIntExtra("number", 1); // pk로 구분


        findId();


        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs = retrofit.create(RemoteService.class);

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

    @Override   //액션바 홈버튼
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void findId(){
        name = (TextView) findViewById(R.id.paymentname);
        title = (TextView) findViewById(R.id.paymentCoursename);
        pay = (TextView) findViewById(R.id.paymentprice);
        number = (TextView) findViewById(R.id.paymentnumber);
    }

    @Override
    protected void onResume() {
        Call<Enrollment> call = rs.payment(number1);//call객체
        call.enqueue(new Callback<Enrollment>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<Enrollment> call, Response<Enrollment> response) {
                if (response.isSuccessful()) {
                    Enrollment enrollment = response.body();
                    name.setText(enrollment.getName());
                    title.setText(enrollment.getSubjectname());
                    pay.setText(enrollment.getStudyfee());
                    number.setText(enrollment.getPaymentnumber());
                }
            }

            @Override
            public void onFailure(Call<Enrollment> call, Throwable t) {
                System.out.println("payment 불러오기 실패" + call + " " + t);

            }
        });
        super.onResume();
    }

}



