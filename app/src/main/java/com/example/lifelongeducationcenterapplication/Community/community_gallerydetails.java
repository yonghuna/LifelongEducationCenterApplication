package com.example.lifelongeducationcenterapplication.Community;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lifelongeducationcenterapplication.Attachment;
import com.example.lifelongeducationcenterapplication.Image;
import com.example.lifelongeducationcenterapplication.Notice;
import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RemoteService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class community_gallerydetails extends AppCompatActivity {
    TextView title, content, who, time, postedNumber, comment,textview;
    ImageView image;
    Button list;
    int number;
    Notice notice;
    Retrofit retrofit1;
    RemoteService rs1;

    Retrofit retrofit2;
    RemoteService rs2;

    Retrofit retrofit3;
    RemoteService rs3;

    ListView listview;
    MyAdapter adapter;
    List<Attachment> attachment;
    List<Image> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_gallerydetails);

        textview = findViewById(R.id.write_content_tv);
        textview.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();
        number = intent.getIntExtra("number", 1); // pk로 구분

        title = (TextView) findViewById(R.id.write_title_tv);
        content = (TextView) findViewById(R.id.write_content_tv);
        postedNumber = (TextView) findViewById(R.id.number);
        who = (TextView) findViewById(R.id.who);
        time = (TextView) findViewById(R.id.time);
        list = (Button) findViewById(R.id.list);
        listview = (ListView) findViewById(R.id.image);

        adapter = new MyAdapter();
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void setRetrofit(){
        retrofit1 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs1 = retrofit1.create(RemoteService.class);

        retrofit2 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs2 = retrofit2.create(RemoteService.class);

        retrofit3 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs3 = retrofit3.create(RemoteService.class);


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

    @Override
    protected void onResume() {

        Call<Notice> call = rs1.galleryContent();//call객체
        call.enqueue(new Callback<Notice>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<Notice> call, Response<Notice> response) {
                if (response.isSuccessful()) {
                    notice = response.body();
                    postedNumber.setText(Integer.toString(notice.getNumber()));
                    time.setText(notice.getReportingdate());
                    who.setText("관리자");
                    title.setText(notice.getTitle());
                }
            }

            @Override
            public void onFailure(Call<Notice> call, Throwable t) {
                System.out.println("갤러리 글 들어갈떄 " + call + " " + t);

            }
        });

        Call<List<Attachment>> call1 = rs2.imageFile(number);//call객체
        call1.enqueue(new Callback<List<Attachment>>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<List<Attachment>> call, Response<List<Attachment>> response) {
                if(response.isSuccessful()){
                    attachment = response.body();
                    adapter.notifyDataSetChanged();
                    listview.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<List<Attachment>> call, Throwable t) {
                System.out.println("JSON 불러오기 실패" +call +" " + t);

            }
        });
        super.onResume();

        Call<List<Image>> call2 = rs3.imageGet(number);//call객체
        call1.enqueue(new Callback<List<Image>>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                if(response.isSuccessful()){
                    attachment = response.body();
                    adapter.notifyDataSetChanged();
                    listview.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<List<Image>> call, Throwable t) {
                System.out.println("JSON 불러오기 실패" +call +" " + t);

            }
        });
        super.onResume();
    }




    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return attachment.size();
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
            convertView = getLayoutInflater().inflate(R.layout.item_community_gallerylist1,null);

            Attachment attachment1 =  attachment.get(position);
            image = (ImageView) convertView.findViewById(R.id.galleryimg);


            return convertView;
        }
    }
}