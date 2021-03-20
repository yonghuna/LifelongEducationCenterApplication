package com.example.lifelongeducationcenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
<<<<<<< HEAD
import android.util.Log;
=======
>>>>>>> 30959068e9eaa29ccbea61c2ef9ada9163b93ee6
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;
    RemoteService rs;
    List<MainLecture> lectures = new ArrayList<>();

    ListView listLecture;
    //ArrayList<MainLecture> mainLectureArrayList = new ArrayList<>();
    //ArrayList<MainLecture> mainLectureArrayList;
    MainLectureAdapter mainLectureAdapter;
    //CustomTask custome = new CustomTask();
    String result;
=======
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listLecture;
    ArrayList<MainLecture> mainLectureArrayList = new ArrayList<>();
    //ArrayList<MainLecture> mainLectureArrayList;
    MainLectureAdapter mainLectureAdapter;

>>>>>>> 30959068e9eaa29ccbea61c2ef9ada9163b93ee6

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        listLecture = findViewById(R.id.listLecture);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        rs = retrofit.create(RemoteService.class);



        //mainLectureArrayList = new ArrayList<>();

        /*

        try {
            result  = custome.execute("start").get();
            Log.i("리턴 값",result);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

         */
        /*
        MainLecture mainLecture = new MainLecture(result,"11","111","111","1111");
        mainLectureArrayList.add(mainLecture);
         */

        mainLectureAdapter = new MainLectureAdapter();
        listLecture.setAdapter(mainLectureAdapter);
    }

    @Override
    protected void onResume() {
        Call<List<MainLecture>> call = rs.listLecture();
        call.enqueue(new Callback<List<MainLecture>>() {
            @Override
            public void onResponse(Call<List<MainLecture>> call, Response<List<MainLecture>> response) {
                lectures = response.body();
                mainLectureAdapter.notifyDataSetChanged();
                listLecture.setAdapter(mainLectureAdapter);
            }

            @Override
            public void onFailure(Call<List<MainLecture>> call, Throwable t) {
                System.out.println("JSON 불러오기 실패" +call +" " + t);

            }
        });

        super.onResume();
    }

=======

        listLecture = findViewById(R.id.listLecture);

        //mainLectureArrayList = new ArrayList<>();

        /* 샘플데이터
        MainLecture mainLecture = new MainLecture("1","11","111","111","1111");
        mainLectureArrayList.add(mainLecture);
        */

        mainLectureAdapter = new MainLectureAdapter();
        listLecture.setAdapter(mainLectureAdapter);
    }


>>>>>>> 30959068e9eaa29ccbea61c2ef9ada9163b93ee6
    class MainLectureAdapter extends BaseAdapter {

        @Override
        public int getCount() {
<<<<<<< HEAD
            return lectures.size();
=======
            return mainLectureArrayList.size();
>>>>>>> 30959068e9eaa29ccbea61c2ef9ada9163b93ee6
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

<<<<<<< HEAD
            //LinearLayout abc = findViewById(R.id.abc);
            //LinearLayout listMainLecture = findViewById(R.id.listMainLecture);
=======
            LinearLayout abc = findViewById(R.id.abc);
            LinearLayout listMainLecture = findViewById(R.id.listMainLecture);
>>>>>>> 30959068e9eaa29ccbea61c2ef9ada9163b93ee6
            TextView mainLectureName = convertView.findViewById(R.id.mainLectureName);//강좌명

            TextView mainLecturePeriod = convertView.findViewById(R.id.mainLecturePeriod);//교육기간
            TextView mainLectureProfessor = convertView.findViewById(R.id.mainLectureProfessor);//교수진
            TextView mainLectureTime = convertView.findViewById(R.id.mainLectureTime);//수업시간
            TextView mainLectureStudyfee = convertView.findViewById(R.id.mainLectureStudyfee);//학습비

            /*
            mainLectureArrayList.add(new MainLecture("1","11","111","1111","11111"));
            mainLectureArrayList.add(new MainLecture("2","21","211","2111","21111"));
             */

<<<<<<< HEAD
            //MainLecture mainLecture = mainLectureArrayList.get(position);
            MainLecture mainLecture = lectures.get(position);
            System.out.println("확인 : "+ mainLecture.getLectureName()+" "+mainLecture.getLecturePeriod());

            mainLectureName.setText(mainLecture.getLectureName());
            mainLecturePeriod.setText(mainLecture.getLecturePeriod());
            mainLectureProfessor.setText(mainLecture.getLectureProfessor());
            mainLectureTime.setText(mainLecture.getLectureTime());
            mainLectureStudyfee.setText(mainLecture.getLectureStudyfee());
=======
            MainLecture mainLecture = mainLectureArrayList.get(position);
            System.out.println("확인 : "+ mainLecture.getName()+" "+mainLecture.getPeriod());

            mainLectureName.setText(mainLecture.getName());
            mainLecturePeriod.setText(mainLecture.getPeriod());
            mainLectureProfessor.setText(mainLecture.getProfessor());
            mainLectureTime.setText(mainLecture.getTime());
            mainLectureStudyfee.setText(mainLecture.getStudyfee());
>>>>>>> 30959068e9eaa29ccbea61c2ef9ada9163b93ee6

            return convertView;
        }
    }

<<<<<<< HEAD

=======
>>>>>>> 30959068e9eaa29ccbea61c2ef9ada9163b93ee6
}