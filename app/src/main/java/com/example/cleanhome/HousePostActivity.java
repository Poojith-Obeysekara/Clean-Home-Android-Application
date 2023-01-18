package com.example.cleanhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;

public class HousePostActivity extends AppCompatActivity {

    TextView userName, noOfRooms, noOfBathrooms, floorType, address, houseId, room, bathRoom, floor, price, date;
    Button backBtn, createBtn;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_post);

        MainDB dbh = new MainDB(this);

        userName = findViewById(R.id.textView25);
        backBtn = findViewById(R.id.button16);
        noOfRooms = findViewById(R.id.textView29);
        noOfBathrooms = findViewById(R.id.textView31);
        floorType = findViewById(R.id.textView33);
        address = findViewById(R.id.textView26);
        houseId = findViewById(R.id.textView28);
        imageView = findViewById(R.id.imageView6);
        room = findViewById(R.id.textView30);
        bathRoom = findViewById(R.id.textView32);
        floor = findViewById(R.id.textView34);
        price = findViewById(R.id.textView36);
        date = findViewById(R.id.textView39);
        createBtn = findViewById(R.id.button15);

        long d = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd, yyyy");
        String dateString = sdf.format(d);
        date.setText(dateString);


        String user = getIntent().getStringExtra("name");
        userName.setText(user);
        room.setText("Room(s)");
        bathRoom.setText("Bathroom(s)");
        floor.setText("Floor type");
        String uN = userName.getText().toString();

        Cursor cursor = dbh.viewHouse(uN);
        if (cursor.moveToNext()){
            noOfRooms.setText(cursor.getString(2));
            noOfBathrooms.setText(cursor.getString(3));
            floorType.setText(cursor.getString(4));
            address.setText(cursor.getString(5));
            houseId.setText(cursor.getString(0));
            byte[] image = cursor.getBlob(6);
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            imageView.setImageBitmap(bmp);

            String fT = floorType.getText().toString();
            int nR = Integer.parseInt(noOfRooms.getText().toString());
            int nBr = Integer.parseInt(noOfBathrooms.getText().toString());
            switch (fT) {
                case "Hardwood": {
                    int p1 = nR * 500;
                    int p2 = nBr * 300;
                    int pF = p1 + p2;
                    price.setText(String.valueOf(pF));
                    break;
                }
                case "Vinyl Flooring": {
                    int p1 = nR * 450;
                    int p2 = nBr * 220;
                    int pF = p1 + p2;
                    price.setText(String.valueOf(pF));
                    break;
                }
                case "Tile Flooring": {
                    int p1 = nR * 400;
                    int p2 = nBr * 200;
                    int pF = p1 + p2;
                    price.setText(String.valueOf(pF));
                    break;
                }
                case "Laminate Flooring": {
                    int p1 = nR * 300;
                    int p2 = nBr * 100;
                    int pF = p1 + p2;
                    price.setText(String.valueOf(pF));
                    break;
                }
            }
        }  else {
            Toast.makeText(this, "Cannot find House details. So please enter house details", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, HouseInformationActivity.class);
            intent.putExtra("name", uN);
            startActivity(intent);
        }

        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("user", uN);
            startActivity(intent);
        });


            createBtn.setOnClickListener(v -> {

                String name = userName.getText().toString();
                String hId = houseId.getText().toString();
                String rooms = noOfRooms.getText().toString();
                String bathRooms = noOfBathrooms.getText().toString();
                String floor = floorType.getText().toString();
                String adrs = address.getText().toString();
                byte[] img = imageViewToByte(imageView);
                String pR = price.getText().toString();
                String dateText = date.getText().toString();

                int deletePost = dbh.deletePost(name);
                Boolean insert = dbh.insertPost(name, hId, rooms, bathRooms, floor, adrs, img, pR, dateText);
                if (insert == true) {
                    Toast.makeText(this, "Post added Successfully, a cleaner will respond and complete the job soon", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to add the post, please try again", Toast.LENGTH_SHORT).show();
                }
            });

    }

    private byte[] imageViewToByte(@NonNull ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}