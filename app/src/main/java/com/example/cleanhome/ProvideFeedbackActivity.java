package com.example.cleanhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class ProvideFeedbackActivity extends AppCompatActivity {

    TextView userNAme, receiver, feedBack, date;
    Button back, add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provide_feedback);

        MainDB dbh = new MainDB(this);

        userNAme = findViewById(R.id.textView55);
        back = findViewById(R.id.button22);
        receiver = findViewById(R.id.editTextTextPersonName7);
        feedBack = findViewById(R.id.editTextTextMultiLine);
        add = findViewById(R.id.button23);
        date = findViewById(R.id.textView56);

        long d = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd, yyyy");
        String dateString = sdf.format(d);
        date.setText(dateString);



        String user = getIntent().getStringExtra("user");
        userNAme.setText(user);
        String name = userNAme.getText().toString();

        back.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("user", name);
            startActivity(intent);
        });

        add.setOnClickListener(v -> {
            String fR = receiver.getText().toString();
            String feed = feedBack.getText().toString();
            String dateText = date.getText().toString();

            if (fR.equals("") || feed.equals("")) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                Boolean insert = dbh.insertFeedback(name, feed, fR, dateText);
                if (insert == true) {
                    Toast.makeText(this, "Feedback added Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}