package com.example.lifelongeducationcenterapplication.MyPage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.lifelongeducationcenterapplication.R;
public class MyPage_MemberInformationManagementActivity extends AppCompatActivity {
    //마이페이지 회원정보관리
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("회원정보관리");
        setContentView(R.layout.activity_my_page__member_information_management);
    }
}