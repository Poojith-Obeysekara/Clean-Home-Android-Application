package com.example.cleanhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HousePostDetailsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView user, hId, address, rooms, bathRooms, floor, price, date, invisibleName, clientName;
    Button back, done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_post_details);

        MainDB dbh = new MainDB(this);

        imageView = findViewById(R.id.imageView7);
        user = findViewById(R.id.textView38);
        hId = findViewById(R.id.textView41);
        address = findViewById(R.id.textView42);
        rooms = findViewById(R.id.textView43);
        bathRooms = findViewById(R.id.textView44);
        floor = findViewById(R.id.textView45);
        price = findViewById(R.id.textView46);
        date = findViewById(R.id.textView48);
        done = findViewById(R.id.button18);
        invisibleName = findViewById(R.id.textView50);
        clientName = findViewById(R.id.textView51);



        String vName = getIntent().getStringExtra("name");
        invisibleName.setText(vName);
        String visible = invisibleName.getText().toString();

        ArrayList<HousePost> arrayList = dbh.getPost();
        Intent intent = getIntent();
        String pos = intent.getStringExtra("POSITION");
        int position = Integer.parseInt(pos);
        HousePost housePost = arrayList.get(position);

        byte[] image = housePost.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        String uN = housePost.getUserName();
        String houseId = housePost.getHouseId();
        String addrs = housePost.getAddress();
        String room = housePost.getNoOfRooms();
        String bath = housePost.getNoOfBathRooms();
        String floorType = housePost.getFloorType();
        String pce = housePost.getPrice();
        String d = housePost.getDate();

        imageView.setImageBitmap(bitmap);
        user.setText("Owner: "+uN);
        hId.setText("house Id: "+houseId);
        address.setText("Address: "+addrs);
        rooms.setText("Room(s): "+room);
        bathRooms.setText("Bathroom(s): "+bath);
        floor.setText("Floor Type: "+floorType);
        price.setText("Price: "+pce);
        date.setText("Date: "+d);

        done.setOnClickListener(v -> {
            clientName.setText(uN);
            String name = clientName.getText().toString();
            int deletePost = dbh.deletePost(name);
            Toast.makeText(this, "Job Accepted and Done", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(this, ViewUserPostActivity.class);
            intent1.putExtra("user", visible);
            startActivity(intent1);
        });


    }
}