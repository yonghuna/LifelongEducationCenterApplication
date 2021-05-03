package com.example.lifelongeducationcenterapplication.MyPage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lifelongeducationcenterapplication.Enrollment;
import com.example.lifelongeducationcenterapplication.R;

import java.util.List;

public class MyPage_GradesVerificationActivity extends AppCompatActivity {
    //마이페이지 성적확인

    TextView year, name, result;
    ListView listView;
    List<Enrollment> enrollments;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("성적확인");
        setContentView(R.layout.activity_my_page__grades_verification);
    }
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return enrollments.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.item_mypagecoursedetailslist, null);
            Enrollment enrollment = enrollments.get(position);
            name = convertView.findViewById(R.id.Coursenamelist);
            year = convertView.findViewById(R.id.gragdsYearsemesterlist);
            result = convertView.findViewById(R.id.resultlist);

            if(enrollment.getGrade() != "" || enrollment.getGrade() != null){
                name.setText(enrollment.getName());
                year.setText(enrollment.getSubjectyear() + " / " + enrollment.getSubjectsemester());
                result.setText(enrollment.getGrade());
            }else{
                name.setText("조회 결과가 없습니다.");
                year.setVisibility(View.GONE);
                result.setVisibility(View.GONE);
            }

            return convertView;
        }
    }
}