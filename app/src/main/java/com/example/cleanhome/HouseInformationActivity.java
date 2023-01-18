package com.example.cleanhome;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.ByteArrayOutputStream;

public class HouseInformationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView uN, floorType;
    EditText address, Location, noOfRooms, noOfBathrooms, houseId;
    Spinner spinner;
    Button back, add;
    Bitmap img;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_information);

        MainDB dbh = new MainDB(this);

        uN = findViewById(R.id.textView17);
        floorType = findViewById(R.id.textView23);
        spinner = findViewById(R.id.spinner3);
        back = findViewById(R.id.button13);
        imageView = findViewById(R.id.imageViewmain2);
        add = findViewById(R.id.button14);
        address = findViewById(R.id.editTextTextPersonName6);
        Location = findViewById(R.id.editTextLocation);
        noOfRooms = findViewById(R.id.editTextNumber);
        noOfBathrooms = findViewById(R.id.editTextNumber2);
        houseId = findViewById(R.id.editTextNumber4);




        String name = getIntent().getStringExtra("name");
        uN.setText(name);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.floors, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

        back.setOnClickListener(v -> {
            String uName = uN.getText().toString();
            Intent intent = new Intent(this, ViewHouseInformationActivity.class);
            intent.putExtra("name", uName);
            startActivity(intent);
        });

        ActivityResultLauncher<Intent> launcher=
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                        new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result) {
                                if(result.getResultCode() == Activity.RESULT_OK)
                                {
                                    Intent intent= result.getData();
                                    LatLng latLng= intent.getParcelableExtra("location");
                                    if(latLng != null)
                                    {
                                        Location.setText(String.valueOf(latLng));
                                    }
                                }
                            }
                        });
        Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                launcher.launch(intent);
            }
        });


        ActivityResultLauncher camlauncher= registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent intent= result.getData();
                        img = (Bitmap) intent.getExtras().get("data");
                        imageView.setImageBitmap(img);
                    }
                }
        );

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                camlauncher.launch(intent);
            }
        });

        add.setOnClickListener(v -> {
            String id = houseId.getText().toString();
            String user = uN.getText().toString();
            String rooms = noOfRooms.getText().toString();
            String bathRooms = noOfBathrooms.getText().toString();
            String floor = floorType.getText().toString();
            String adrs = address.getText().toString();
            byte[] img = imageViewToByte(imageView);

            int deleteHouse = dbh.deleteHouse(user);

            if (id.equals("") || user.equals("") || adrs.equals("") || rooms.equals("")) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                Boolean checkHouse = dbh.checkHouse(user);
                if (checkHouse == false) {
                    Boolean insert = dbh.insertHouseInfo(id, user, rooms, bathRooms, floor, adrs, img);
                    if (insert == true) {
                        Toast.makeText(this, "House Registered Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(this, ViewHouseInformationActivity.class);
                        intent.putExtra("name", user);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Registered Failed", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "User's House already exists!", Toast.LENGTH_SHORT).show();
                }
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




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String choice = parent.getItemAtPosition(position).toString();
        floorType.setText(choice);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}