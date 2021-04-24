package com.example.lifelongeducationcenterapplication.Creditbankingsystem;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifelongeducationcenterapplication.Account.Login;
import com.example.lifelongeducationcenterapplication.Generalcurriculum.LearnmoreaboutforeignlanguagecoursesActivity;
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

public class EnrolmentKoreanStudiesFragment extends Fragment {
    //외국어로서의 한국어학
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Retrofit retrofit;//httpclient library
    RemoteService rs;//DB를 위한 인터페이스

    List<Lecture> lectures; // 배열 객체 생성
    ListView listLecture;//리스트뷰
    Button btDetail, btClassRg;
    TextView textName, textPeriod, textProfessor, textTime, textFee;
    MyAdapter adapter;




    @Override
    public void onResume() {

        Call<List<Lecture>> call = rs.lecture("외국어로서의 한국어학");//call객체
        call.enqueue(new Callback<List<Lecture>>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<List<Lecture>> call, Response<List<Lecture>> response) {
                if(response.isSuccessful()){
                    lectures = response.body();
                    adapter.notifyDataSetChanged();
                    listLecture.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Lecture>> call, Throwable t) {
                System.out.println("JSON 불러오기 실패" +call +" " + t);

            }
        });
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enrolment_korean_studies, container, false);
        // Inflate the layout for this fragment

        listLecture = (ListView) view.findViewById(R.id.EnrolmentKoreanStudieslistLecture);


        adapter = new MyAdapter();

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs = retrofit.create(RemoteService.class);
        return view;
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
            convertView = getLayoutInflater().inflate(R.layout.item_foreignlanguagecourse,null);

            Lecture lc =  lectures.get(position);

            String day = lc.getDayOfTheWeek();
            String name = lc.getName();
            String startDate = lc.getStartDate();
            String endDate = lc.getEndDate();
            String startTime = lc.getStartTime();
            String endTime = lc.getEndTime();
            String professor = lc.getProfessor();
            String studyFee = lc.getStudyFee();
            String status = lc.getStatus();
            int number = lc.getNumber();


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
            textTime.setText("・수업시간  " + day + " " + startTime +" ~ " + endTime);
            textFee.setText("・학습비     " +studyFee);


            // 수강 불가시 수강신청 버튼 변경
            if(status.equals("수강불가")){
                btClassRg.setBackgroundColor(Color.GRAY);
                btClassRg.setText("수강불가");
                btClassRg.setClickable(false);
            }

            // 상세보기
            btDetail.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Intent intent = new Intent(getActivity(), LearnmoreaboutforeignlanguagecoursesActivity.class);
                    intent.putExtra("number", number);
                    startActivity(intent);
                }

            });
            // 일반과정 수강신청
            btClassRg.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    if(StaticId.id.equals("") || StaticId.id == null){
                        Intent intent = new Intent(getActivity(), Login.class);
                        Toast.makeText(getContext(), "로그인을 해야 수강신청이 가능합니다.", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }else{
                        //
                    }
                }
            });
            return convertView;
        }
    }
}