package com.example.cleanhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewFeedActivity extends AppCompatActivity {

    TextView userName;
    Button back;
    ListView listView;
    ArrayList<Feed> arrayList;
    FeedAdapter adapter;
    MainDB dbh = new MainDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feed);

        back = findViewById(R.id.button24);
        userName = findViewById(R.id.textView57);
        listView = findViewById(R.id.list);



        String name = getIntent().getStringExtra("user");
        userName.setText(name);


        showFeedData();

        back.setOnClickListener(v -> {
            String uN = userName.getText().toString();
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("user", uN);
            startActivity(intent);
        });
    }

    private void showFeedData() {
        String user = userName.getText().toString();
        arrayList = dbh.getFeed(user);
        adapter = new FeedAdapter(this, arrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}