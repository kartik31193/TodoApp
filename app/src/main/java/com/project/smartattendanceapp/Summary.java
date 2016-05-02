package com.project.smartattendanceapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;

public class Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);


        final Button ex2 = findViewById(R.id.export2);
        final Button ex3 = findViewById(R.id.export3);
        final TextView t1 = findViewById(R.id.batch);
        final TextView t2 = findViewById(R.id.subject);
        final TextView t3 = findViewById(R.id.students);
        final TextView t4 = findViewById(R.id.date);
        final TextView t5 = findViewById(R.id.present);
        final TextView t6 = findViewById(R.id.absent);
        final TextView t7 = findViewById(R.id.note);

        //getting details
        final String batch_name = getIntent().getStringExtra("batch_name");
        final String subject_name = getIntent().getStringExtra("subject_name");
        final String students = getIntent().getStringExtra("students");
        final String date = getIntent().getStringExtra("date");
        final String note = getIntent().getStringExtra("note");
        final String absent = getIntent().getStringExtra("absent");
        final String present = getIntent().getStringExtra("present");
        final String data = getIntent().getStringExtra("data file");

        // setting details

        t1.setText("Batch Name: "+batch_name);
        t2.setText("Subject: "+subject_name);
        t3.setText("No. of students: "+students);
        t4.setText("Date: "+date);
        t5.setText("Present Roll no. : "+present);
        t6.setText("Absent Roll no. : "+absent);
        t7.setText("Note: "+note);

        //build string
        StringBuilder mailText =new StringBuilder(0);
        mailText.append("Batch Name: "+batch_name);
        mailText.append("\n"+"Subject: "+subject_name);
        mailText.append("\n"+"No. of students: "+students);
        mailText.append("\n"+"Date: "+date);
        mailText.append("\n"+"Present Roll no. : "+present);
        mailText.append("\n"+"Absent Roll no. : "+absent);
        mailText.append("\n"+"Note: "+note);

        //Export file
        ex2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                export();
            }


            public void export() {


                try {
                    //saving the file into device
                    FileOutputStream out = openFileOutput("attendance.csv", Context.MODE_PRIVATE);
                    out.write((data.toString()).getBytes());
                    out.close();

                    //exporting
                    Context context = getApplicationContext();
                    File filelocation = new File(getFilesDir(), "attendance.csv");
                    Uri path = FileProvider.getUriForFile(context, "com.project.smartattendanceapp.fileprovider", filelocation);
                    Intent fileIntent = new Intent(Intent.ACTION_SEND);
                    //fileIntent.setPackage("com.google.android.gm");
                    fileIntent.setType("text/csv");
                    //fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Data");
                    fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    fileIntent.putExtra(Intent.EXTRA_STREAM, path);
                    startActivity(Intent.createChooser(fileIntent, "Export file"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        //send file
        ex3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }


            public void sendMail() {


                try {
                    //saving the file into device
                    FileOutputStream out = openFileOutput("attendance.csv", Context.MODE_PRIVATE);
                    out.write((data.toString()).getBytes());
                    out.close();

                    //exporting
                    Context context = getApplicationContext();
                    File filelocation = new File(getFilesDir(), "attendance.csv");
                    Uri path = FileProvider.getUriForFile(context, "com.project.smartattendanceapp.fileprovider", filelocation);
                    Intent fileIntent = new Intent(Intent.ACTION_SEND);
                    fileIntent.setPackage("com.google.android.gm");
                    fileIntent.setType("text/csv");
                    fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Attendance-"+date);
                    fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    fileIntent.putExtra(Intent.EXTRA_STREAM, path);
                    fileIntent.putExtra(Intent.EXTRA_TEXT, mailText.toString());
                    startActivity(Intent.createChooser(fileIntent, "Export file"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}