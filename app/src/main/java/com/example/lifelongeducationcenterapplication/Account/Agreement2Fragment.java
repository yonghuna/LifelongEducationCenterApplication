package com.example.lifelongeducationcenterapplication.Account;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lifelongeducationcenterapplication.R;


public class Agreement2Fragment extends Fragment {
    Button cancel_button2,agree_button2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agreement2, container, false);

        setfindviewbyid(view);
        movefragment();

        return view;
    }

    public void setfindviewbyid(View view){
        cancel_button2 = view.findViewById(R.id.cancel_button2);
        agree_button2 = view.findViewById(R.id.agree_button2);
    }

    public void movefragment(){//동의또는 취소를 눌러서 이전화면 또는 다음화면으로 이동
        cancel_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignActivity signActivity = (SignActivity) getActivity();
                signActivity.onFragmentChanged(0);
            }
        });
        agree_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignActivity signActivity = (SignActivity) getActivity();
                signActivity.onFragmentChanged(3);
            }
        });
    }
}