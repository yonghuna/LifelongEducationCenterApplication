package com.example.lifelongeducationcenterapplication.Community;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.Toast;

import com.example.lifelongeducationcenterapplication.Attachment;
import com.example.lifelongeducationcenterapplication.Image;
import com.example.lifelongeducationcenterapplication.Notice;
import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RemoteService;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.URI;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.DownloadManager.Request.VISIBILITY_VISIBLE;
import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class community_gallerydetails extends AppCompatActivity {
    TextView title, who, time, postedNumber,  file;
    ImageView image;
    Button list;
    int number;
    Notice notice;
    Retrofit retrofit1;
    RemoteService rs1;

    Retrofit retrofit2;
    RemoteService rs2;
    private DownloadManager mDownloadManager;
    private Long mDownloadQueueId;
    Retrofit retrofit3;
    RemoteService rs3;

    ListView listview; // 이미지
    ListView listview2; // 첨부파일
    MyAdapter adapter;
    FileAdapter fileadapter;
    List<Attachment> attachment;
    List<Image> images;
    String pathFile;
    String outputFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_gallerydetails);
        getSupportActionBar().setTitle("갤러리");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        number = intent.getIntExtra("number", 1); // pk로 구분
        setRetrofit();
        title = (TextView) findViewById(R.id.write_title_tv);
        postedNumber = (TextView) findViewById(R.id.number);
        who = (TextView) findViewById(R.id.who);
        time = (TextView) findViewById(R.id.time);
        list = (Button) findViewById(R.id.list);
        listview = (ListView) findViewById(R.id.image);
        listview2 = (ListView) findViewById(R.id.file);

        pathFile = (this.getFilesDir()).toString();


        adapter = new MyAdapter(); // 이미지
        fileadapter = new FileAdapter(); // 파일
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void setRetrofit() {
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
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter completeFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(downloadCompleteReceiver, completeFilter);
        // 내용
        Call<Notice> call = rs1.galleryContent(number);//call객체
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

        // 이미지 첨부파일
        Call<List<Attachment>> call1 = rs2.imageFile(number);//call객체
        call1.enqueue(new Callback<List<Attachment>>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<List<Attachment>> call, Response<List<Attachment>> response) {
                if (response.isSuccessful()) {
                    attachment = response.body();
                    fileadapter.notifyDataSetChanged();
                    listview.setAdapter(fileadapter);

                }

            }

            @Override
            public void onFailure(Call<List<Attachment>> call, Throwable t) {
                System.out.println("첨부파일 불러오기 실패" + call + " " + t);

            }
        });


        //이미지
        Call<List<Image>> call2 = rs3.imageGet(number);//call객체
        call2.enqueue(new Callback<List<Image>>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                if (response.isSuccessful()) {
                    images = response.body();
                    adapter.notifyDataSetChanged();
                    listview2.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<List<Image>> call, Throwable t) {
                System.out.println("이미지 불러오기 실패" + call + " " + t);

            }
        });

    }


    //이미지
    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return images.size();
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
            convertView = getLayoutInflater().inflate(R.layout.item_community_gallerylist1, null);

            Image image1 = images.get(position);
            image = (ImageView) convertView.findViewById(R.id.galleryimg);
            // 이미지 세팅

            Picasso.get()
                    .load(image1.getPath())
                    .error(R.drawable.error)
                    .into(image);

            return convertView;
        }
    }

    //파일
    class FileAdapter extends BaseAdapter {
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
            convertView = getLayoutInflater().inflate(R.layout.item_mainlecture, null);

            Attachment attachment1 = attachment.get(position);
            file = (TextView) convertView.findViewById(R.id.file);

            file.setText(attachment1.getRandomname());
            file.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 클릭시 다운로드
                    outputFilePath = Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOWNLOADS + pathFile) + "/" + attachment1.getRealname();
                    URLDownloading(Uri.parse(attachment1.getPath()));

                }
            });


            return convertView;
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        unregisterReceiver(downloadCompleteReceiver);
    }

    private BroadcastReceiver downloadCompleteReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

            if(mDownloadQueueId == reference){
                DownloadManager.Query query = new DownloadManager.Query();  // 다운로드 항목 조회에 필요한 정보 포함
                query.setFilterById(reference);
                Cursor cursor = mDownloadManager.query(query);

                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                int columnReason = cursor.getColumnIndex(DownloadManager.COLUMN_REASON);

                int status = cursor.getInt(columnIndex);
                int reason = cursor.getInt(columnReason);

                cursor.close();

                switch (status) {
                    case DownloadManager.STATUS_SUCCESSFUL :
                        Toast.makeText(community_gallerydetails.this, "다운로드를 완료하였습니다.", Toast.LENGTH_SHORT).show();
                        break;

                    case DownloadManager.STATUS_PAUSED :
                        Toast.makeText(community_gallerydetails.this, "다운로드가 중단되었습니다.", Toast.LENGTH_SHORT).show();
                        break;

                    case DownloadManager.STATUS_FAILED :
                        Toast.makeText(community_gallerydetails.this, "다운로드가 취소되었습니다.", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    };

    private void URLDownloading(Uri url) {
        if (mDownloadManager == null) {
            mDownloadManager = (DownloadManager) community_gallerydetails.this.getSystemService(Context.DOWNLOAD_SERVICE);
        }
        File outputFile = new File(outputFilePath);
        if (!outputFile.getParentFile().exists()) {
            outputFile.getParentFile().mkdirs();
        }

        Uri downloadUri = url;
        DownloadManager.Request request = new DownloadManager.Request(downloadUri);
        List<String> pathSegmentList = downloadUri.getPathSegments();
        request.setTitle("다운로드 항목");
        request.setDestinationUri(Uri.fromFile(outputFile));
        request.setAllowedOverMetered(true);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        mDownloadQueueId = mDownloadManager.enqueue(request);
    }
}