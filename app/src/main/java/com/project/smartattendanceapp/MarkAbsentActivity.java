package com.project.smartattendanceapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MarkAbsentActivity extends AppCompatActivity {
    int n;
    StringBuffer absent = new StringBuffer(0);
    StringBuffer present =new StringBuffer(0);

    String batch_name;
    String subject_name;
    String students ;
    String date;
    String note ;

    StringBuilder data = new StringBuilder();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);
        final ScrollView scrollView = findViewById(R.id.scrollview);
        final LinearLayout l = findViewById(R.id.linearlayout);
        //final Button ex = findViewById(R.id.export);
        final Button summary = findViewById(R.id.summarybutton);
        final TextView t = findViewById(R.id.mark);
        t.setText("Mark Absent Students");

        //getting details
        batch_name = getIntent().getStringExtra("batch_name");
        subject_name = getIntent().getStringExtra("subject_name");
        students = getIntent().getStringExtra("students");
        date = getIntent().getStringExtra("date");
        note = getIntent().getStringExtra("note");


        //no. of students
        if(!students.isEmpty())
            n = Integer.parseInt(students);
        else
        {
            Toast.makeText(this, "Please Enter a Valid Number of Students", Toast.LENGTH_SHORT).show();
            finish();
        }
        if(n==0)
        {
            Toast.makeText(this, "Please Enter a Valid Number of Students", Toast.LENGTH_SHORT).show();
            finish();
        }

        //create n checkboxes
        for(int i=1;i<=n;i++) {
            CheckBox cb = new CheckBox(this);
            cb.setId(i);
            cb.setTextSize(20);
            cb.setText("   Roll No - " + i);
            l.addView(cb);
        }







        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSummary();
            }


            public void showSummary() {
                generateData();

                Intent i =new Intent(MarkAbsentActivity.this, Summary.class );
                i.putExtra("batch_name", batch_name);
                i.putExtra("subject_name", subject_name);
                i.putExtra("students", students);
                i.putExtra("date", date);
                i.putExtra("note", note);
                i.putExtra("absent", absent.toString());
                i.putExtra("present", present.toString());
                i.putExtra("data file", data.toString());
                //Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
                startActivity(i);


            }
        });


    }
    public void generateData(){
        //generate data

        data.append("Roll no. , P/A");
        for (int i = 1; i <= n; i++) {
            //data.append("\n"+String.valueOf(i)+","+String.valueOf(i*i));
            CheckBox c = findViewById(i);
            boolean checked = c.isChecked();
            if (checked) {
                data.append("\n" + String.valueOf(i) + ",A");
                absent.append(i).append(",");

            } else {
                data.append("\n" + String.valueOf(i) + ",P");
                present.append(i).append(",");
            }
        }
        data.append("\n"+"Details");
        data.append("\n"+"Batch name ,").append(batch_name);
        data.append("\n"+"Subject name ,").append(subject_name);
        data.append("\n"+"Students ,").append(students);
        data.append("\n"+"date ,").append(date);
        data.append("\n"+"self-note ,").append(note);
    }
}

