
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
import com.example.lifelongeducationcenterapplication.RegisterResult;
import com.example.lifelongeducationcenterapplication.RemoteService;
import com.example.lifelongeducationcenterapplication.StaticId;

import java.util.List;

import kr.co.bootpay.listener.ErrorListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import kr.co.bootpay.Bootpay;
import kr.co.bootpay.BootpayAnalytics;
import kr.co.bootpay.enums.Method;
import kr.co.bootpay.enums.PG;
import kr.co.bootpay.enums.UX;
import kr.co.bootpay.listener.CancelListener;
import kr.co.bootpay.listener.CloseListener;
import kr.co.bootpay.listener.ConfirmListener;
import kr.co.bootpay.listener.DoneListener;
import kr.co.bootpay.listener.ReadyListener;
import kr.co.bootpay.model.BootExtra;
import kr.co.bootpay.model.BootUser;


import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class MyPage_Payment extends AppCompatActivity {
    int number1;
    Button btPay;
    String phone, payTitle, money, getPayNumber;
    private int stuck = 10;
    Retrofit retrofit;//httpclient library
    RemoteService rs;//DB를 위한 인터페이스
    Retrofit retrofit2;//httpclient library
    RemoteService rs2;//DB를 위한 인터페이스

    TextView name, title, pay, number, paynumber;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_my_page_payment);
        getSupportActionBar().setTitle("결제");
        findId();
        Intent intent = getIntent();
        number1 = intent.getIntExtra("number", 1); // pk로 구분

        BootpayAnalytics.init(this, "60aa4e0ed8c1bd001e2bb3d1");



        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs = retrofit.create(RemoteService.class);

        retrofit2 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs2 = retrofit2.create(RemoteService.class);

        //결제
        btPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                BootUser bootUser = new BootUser().setPhone("010-3887-9849"); // 자신의 핸드폰 번호로 바꾸기
                BootExtra bootExtra = new BootExtra().setQuotas(new int[] {0, 2, 3});

                Bootpay.init(getFragmentManager())
                        .setApplicationId("60aa4e0ed8c1bd001e2bb3d1") // 해당 프로젝트(안드로이드)의 application id 값(위의 값 복붙)
                        .setPG(PG.INICIS) // 결제할 PG 사
                        .setMethod(Method.CARD) // 결제수단
                        .setContext(MyPage_Payment.this)
                        .setBootUser(bootUser)
                        .setBootExtra(bootExtra)
                        .setUX(UX.PG_DIALOG)
     //                   .setUserPhone(phone) // 구매자 전화번호
                        .setName(payTitle) // 결제할 상품명
                        .setOrderId(getPayNumber) // 결제 고유번호 (expire_month)
                        .setPrice(Integer.parseInt(money)) // 결제할 금액
                        .addItem(payTitle, 1, "ITEM_CODE_SUBJECT", Integer.parseInt(money)) // 주문정보에 담길 상품정보, 통계를 위해 사용
                        .onConfirm(new ConfirmListener() { // 결제가 진행되기 바로 직전 호출되는 함수로, 주로 재고처리 등의 로직이 수행
                            @Override
                            public void onConfirm(@Nullable String message) {

                                if (0 < stuck) Bootpay.confirm(message); // 재고가 있을 경우.
                                else Bootpay.removePaymentWindow(); // 재고가 없어 중간에 결제창을 닫고 싶을 경우
                                Log.d("confirm", message);
                            }
                        })
                        .onDone(new DoneListener() { // 결제완료시 호출, 아이템 지급 등 데이터 동기화 로직을 수행합니다
                            @Override
                            public void onDone(@Nullable String message) {

                                Call<RegisterResult> call = rs2.paymentSuccess("결제완료", StaticId.id);//call객체
                                call.enqueue(new Callback<RegisterResult>() {//enqueue 메소드 실행
                                    @Override
                                    public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
                                        if (response.isSuccessful()) {
                                            RegisterResult registerResult = response.body();
                                            if (registerResult.getResult().equals("ok")) {
                                                Toast.makeText(getApplicationContext(), "결제 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(getApplicationContext(), "결제 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<RegisterResult> call, Throwable t) {
                                        System.out.println("결제 실패 " + call + " " + t);

                                    }
                                });
                                Log.d("done", message);
                            }
                        })
                        .onReady(new ReadyListener() { // 가상계좌 입금 계좌번호가 발급되면 호출되는 함수입니다.
                            @Override
                            public void onReady(@Nullable String message) {
                                Log.d("ready", message);
                            }
                        })
                        .onCancel(new CancelListener() { // 결제 취소시 호출
                            @Override
                            public void onCancel(@Nullable String message) {

                                Log.d("cancel", message);
                            }
                        })
                        .onError(new ErrorListener() {
                            @Override
                            public void onError(String message) {
                                Log.d("error", message);
                            }
                        })
                        .onClose(
                                new CloseListener() { //결제창이 닫힐때 실행되는 부분
                                    @Override
                                    public void onClose(String message) {
                                        Log.d("close", "close");
                                    }
                                })
                        .request();
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


    public void findId(){
        name = (TextView) findViewById(R.id.paymentname);
        title = (TextView) findViewById(R.id.paymentCoursename);
        pay = (TextView) findViewById(R.id.paymentprice);
        number = (TextView) findViewById(R.id.paymentnumber);
        btPay = (Button) findViewById(R.id.pay);
        paynumber = (TextView) findViewById(R.id.paynumber);
    }

    @Override
    protected void onResume() {
        Call<Enrollment> call = rs.payment(number1, StaticId.id);//call객체
        call.enqueue(new Callback<Enrollment>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<Enrollment> call, Response<Enrollment> response) {
                if (response.isSuccessful()) {
                    Enrollment enrollment = response.body();
                    name.setText(enrollment.getName());
                    title.setText(enrollment.getSubjectname());
                    pay.setText(enrollment.getStudyfee());

                    paynumber.setText(enrollment.getPaymentnumber());
                    String phone1 = enrollment.getPhonenumber().substring(0, 3);
                    String phone2 = enrollment.getPhonenumber().substring(3, 7);
                    String phone3 = enrollment.getPhonenumber().substring(7, 11);
                    phone = phone1 + "-" + phone2 + "-" + phone3;
                    number.setText(phone);

                    payTitle = enrollment.getSubjectname();
                    getPayNumber = enrollment.getPaymentnumber();
                    money = enrollment.getStudyfee();
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



