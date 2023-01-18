package com.example.cleanhome;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UserRegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner1;
    TextView textViewSecurity, userType;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button backBtn, registerBtn;
    EditText userName, password, confirmPassword, answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        MainDB dbh = new MainDB(this);

        spinner1 = findViewById(R.id.spinner);
        textViewSecurity = findViewById(R.id.textView6);
        userType = findViewById(R.id.textView9);
        radioGroup = findViewById(R.id.radioGroup);
        userName = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPassword);
        confirmPassword = findViewById(R.id.editTextTextPassword2);
        answer = findViewById(R.id.editTextTextPersonName2);
        backBtn = findViewById(R.id.button2);
        registerBtn = findViewById(R.id.button);



        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.questions, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter);
        spinner1.setOnItemSelectedListener(this);

        backBtn.setOnClickListener(v -> {
            finish();
        });

        registerBtn.setOnClickListener(v -> {
            String uName = userName.getText().toString();
            String pass = password.getText().toString();
            String conPass = confirmPassword.getText().toString();
            String ans = answer.getText().toString();
            String utype = userType.getText().toString();
            String secQuestion = textViewSecurity.getText().toString();

            if (uName.equals("") || pass.equals("") || conPass.equals("") || ans.equals("")) {
                Toast.makeText(UserRegistrationActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                if (pass.equals(conPass)) {
                    Boolean checkUser = dbh.checkUsername(uName);
                    if (checkUser == false) {
                        Boolean insert = dbh.insertUser(uName, pass, secQuestion, ans, utype);
                        if (insert == true) {
                            Toast.makeText(UserRegistrationActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(UserRegistrationActivity.this, "Registered Failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(UserRegistrationActivity.this, "User already exists!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UserRegistrationActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String choice = parent.getItemAtPosition(position).toString();
        textViewSecurity.setText(choice);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        userType.setText(radioButton.getText());
    }
}