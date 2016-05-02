package com.project.smartattendanceapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailsActivity extends AppCompatActivity {

    String s1, s2, s3, s4, s5, date;
    public EditText e1, e2, e3, e4, e5;
    Button bt;
    int flag = 2;

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        bt = findViewById(R.id.button);
        e1 = findViewById(R.id.editText1);
        e2 = findViewById(R.id.editText2);
        e3 = findViewById(R.id.editTextNumber);
        e4 = findViewById(R.id.editTextDate);
        e5 = findViewById(R.id.editTextMultiLine);



        //set date
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        date = sdf.format(new Date());
        e4.setText(date);


    }

    public void next(View view) {

        s1 = e1.getText().toString();
        s2 = e2.getText().toString();
        s3 = e3.getText().toString();
        s4 = e4.getText().toString();
        s5 = e5.getText().toString();




                  if(flag==2) {
                      Toast.makeText(DetailsActivity.this,"Please choose one option", Toast.LENGTH_SHORT).show();
                     }
                  else if(flag==1) {
                    Toast.makeText(getApplicationContext(), "Mark Present Students",
                            Toast.LENGTH_SHORT).show();
                    Intent i1 =new Intent(DetailsActivity.this, MarkPresentActivity.class );
                    i1.putExtra("batch_name", s1);
                    i1.putExtra("subject_name", s2);
                    i1.putExtra("students", s3);
                    i1.putExtra("date", s4);
                    i1.putExtra("note", s5);
                    //Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
                    startActivity(i1);

                } else if(flag==0) {
                    Toast.makeText(getApplicationContext(), "Mark Absent Students",
                            Toast.LENGTH_SHORT).show();

                    Intent i2 =new Intent(DetailsActivity.this, MarkAbsentActivity.class );
                    i2.putExtra("batch_name", s1);
                    i2.putExtra("subject_name", s2);
                    i2.putExtra("students", s3);
                    i2.putExtra("date", s4);
                    i2.putExtra("note", s5);
                    //Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
                    startActivity(i2);
                }






    }
    public void change(View view) {
        flag=1;
    }

    public void rechange(View view) {
        flag=0;
    }


}