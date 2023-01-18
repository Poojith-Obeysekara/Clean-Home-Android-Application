package com.example.cleanhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView userName, userType;

    Button houseInfo, createPost, customerFeedback, viewPost, feedback, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MainDB dbh = new MainDB(this);

        userName = findViewById(R.id.textView8);

        userType = findViewById(R.id.textView14);

        houseInfo = findViewById(R.id.button6);

        createPost = findViewById(R.id.button7);

        customerFeedback = findViewById(R.id.button8);

        viewPost = findViewById(R.id.button10);

        feedback = findViewById(R.id.button11);

        back = findViewById(R.id.button9);



        String name = getIntent().getStringExtra("user");
        userName.setText(name);

        String user = userName.getText().toString();

        Cursor cursor = dbh.viewUserType(user);
        cursor.moveToNext();
        userType.setText(cursor.getString(4));

        String uTName = userType.getText().toString();
        if (uTName.equals("Customer")) {

            viewPost.setVisibility(View.GONE);
            feedback.setVisibility(View.GONE);

        }

        else if (uTName.equals("Cleaner")) {

            houseInfo.setVisibility(View.GONE);
            createPost.setVisibility(View.GONE);
            customerFeedback.setVisibility(View.GONE);
        }

        back.setOnClickListener(v -> {

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        });

        houseInfo.setOnClickListener(v -> {

            Intent intent = new Intent(this, ViewHouseInformationActivity.class);
            intent.putExtra("name", user);
            startActivity(intent);

        });

        createPost.setOnClickListener(v -> {

            Intent intent = new Intent(this, HousePostActivity.class);
            intent.putExtra("name", user);
            startActivity(intent);

        });

        viewPost.setOnClickListener(v -> {

            Intent intent = new Intent(this, ViewUserPostActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);

        });

        feedback.setOnClickListener(v -> {

            Intent intent = new Intent(this, FeedbackActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);

        });

        customerFeedback.setOnClickListener(v -> {

            Intent intent = new Intent(this, FeedbackActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);

        });
    }
}