package com.example.lifelongeducationcenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listLecture;
    ArrayList<MainLecture> mainLectureArrayList = new ArrayList<>();
    //ArrayList<MainLecture> mainLectureArrayList;
    MainLectureAdapter mainLectureAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listLecture = findViewById(R.id.listLecture);

        //mainLectureArrayList = new ArrayList<>();

        /* 샘플데이터
        MainLecture mainLecture = new MainLecture("1","11","111","111","1111");
        mainLectureArrayList.add(mainLecture);
        */

        mainLectureAdapter = new MainLectureAdapter();
        listLecture.setAdapter(mainLectureAdapter);
    }


    class MainLectureAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mainLectureArrayList.size();
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
            convertView = getLayoutInflater().inflate(R.layout.item_mainlecture,null);

            LinearLayout abc = findViewById(R.id.abc);
            LinearLayout listMainLecture = findViewById(R.id.listMainLecture);
            TextView mainLectureName = convertView.findViewById(R.id.mainLectureName);//강좌명

            TextView mainLecturePeriod = convertView.findViewById(R.id.mainLecturePeriod);//교육기간
            TextView mainLectureProfessor = convertView.findViewById(R.id.mainLectureProfessor);//교수진
            TextView mainLectureTime = convertView.findViewById(R.id.mainLectureTime);//수업시간
            TextView mainLectureStudyfee = convertView.findViewById(R.id.mainLectureStudyfee);//학습비

            /*
            mainLectureArrayList.add(new MainLecture("1","11","111","1111","11111"));
            mainLectureArrayList.add(new MainLecture("2","21","211","2111","21111"));
             */

            MainLecture mainLecture = mainLectureArrayList.get(position);
            System.out.println("확인 : "+ mainLecture.getName()+" "+mainLecture.getPeriod());

            mainLectureName.setText(mainLecture.getName());
            mainLecturePeriod.setText(mainLecture.getPeriod());
            mainLectureProfessor.setText(mainLecture.getProfessor());
            mainLectureTime.setText(mainLecture.getTime());
            mainLectureStudyfee.setText(mainLecture.getStudyfee());

            return convertView;
        }
    }

}