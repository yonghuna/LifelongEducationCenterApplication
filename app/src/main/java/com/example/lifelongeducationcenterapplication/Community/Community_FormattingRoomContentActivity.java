package com.example.lifelongeducationcenterapplication.Community;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifelongeducationcenterapplication.Attachment;
import com.example.lifelongeducationcenterapplication.Image;
import com.example.lifelongeducationcenterapplication.Notice;
import com.example.lifelongeducationcenterapplication.R;
import com.example.lifelongeducationcenterapplication.RemoteService;

import java.io.File;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lifelongeducationcenterapplication.RemoteService.BASE_URL;

public class Community_FormattingRoomContentActivity  extends AppCompatActivity {
    TextView title, content, who, time, postedNumber, comment,textview, file;
    Button remove, modify;
    int number;
    Retrofit retrofit;
    RemoteService rs;

    Notice notice;
    ListView listview;
    List<Attachment> attachment;
    FileAdapter fileadapter;
    ListView fileList;
    Retrofit retrofit2;
    RemoteService rs2;
    private DownloadManager mDownloadManager;
    private Long mDownloadQueueId;
    String pathFile;
    String outputFilePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("첨부파일 게시글");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_community__bulletin_boardwriting);
        setRetrofit();
        //스크롤
        textview = findViewById(R.id.write_content_tv);
        textview.setMovementMethod(new ScrollingMovementMethod());

        pathFile = (this.getFilesDir()).toString();
        Intent intent = getIntent();
        number = intent.getIntExtra("number", 1); // pk로 구분


        title = (TextView) findViewById(R.id.write_title_tv);
        content = (TextView) findViewById(R.id.write_content_tv);

        postedNumber = (TextView) findViewById(R.id.number);
        who = (TextView) findViewById(R.id.who);
        time = (TextView) findViewById(R.id.time);
        comment = (TextView) findViewById(R.id.comment);

        remove = (Button) findViewById(R.id.remove);
        modify = (Button) findViewById(R.id.modfiy);
        listview = (ListView) findViewById(R.id.contentListView);


        fileList = (ListView) findViewById(R.id.notice_file); // 파일
        listview.setVisibility(View.GONE);
        remove.setVisibility(View.GONE);
        modify.setVisibility(View.GONE);
        comment.setVisibility(View.GONE);

        fileadapter = new FileAdapter(); // 파일


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
    public void setRetrofit() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs = retrofit.create(RemoteService.class);
        retrofit2 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
        rs2 = retrofit2.create(RemoteService.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter completeFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(downloadCompleteReceiver, completeFilter);
        Call<Notice> call = rs.format_attachment(number);//call객체
        call.enqueue(new Callback<Notice>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<Notice> call, Response<Notice> response) {
                if (response.isSuccessful()) {
                    notice = response.body();
                    postedNumber.setText(Integer.toString(notice.getNumber()));
                    time.setText(notice.getReportingdate());
                    content.setText(Html.fromHtml(notice.getContents()).toString());
                    who.setText("관리자");
                    title.setText(notice.getTitle());
                }
            }

            @Override
            public void onFailure(Call<Notice> call, Throwable t) {
                System.out.println("공지사항 글 들어갈떄 " + call + " " + t);

            }
        });

        // 첨부파일
        Call<List<Attachment>> call1 = rs2.formatAttachment(number);//call객체
        call1.enqueue(new Callback<List<Attachment>>() {//enqueue 메소드 실행
            @Override
            public void onResponse(Call<List<Attachment>> call, Response<List<Attachment>> response) {
                if (response.isSuccessful()) {
                    attachment = response.body();
                    fileadapter.notifyDataSetChanged();
                    fileList.setAdapter(fileadapter);
                }

            }

            @Override
            public void onFailure(Call<List<Attachment>> call, Throwable t) {
                System.out.println("첨부파일 불러오기 실패" + call + " " + t);

            }
        });



    }

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
                        Toast.makeText(getApplicationContext(), "다운로드를 완료하였습니다.", Toast.LENGTH_SHORT).show();
                        break;

                    case DownloadManager.STATUS_PAUSED :
                        Toast.makeText(getApplicationContext(), "다운로드가 중단되었습니다.", Toast.LENGTH_SHORT).show();
                        break;

                    case DownloadManager.STATUS_FAILED :
                        Toast.makeText(getApplicationContext(), "다운로드가 취소되었습니다.", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    };

    private void URLDownloading(Uri url) {
        if (mDownloadManager == null) {
            mDownloadManager = (DownloadManager) getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
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
