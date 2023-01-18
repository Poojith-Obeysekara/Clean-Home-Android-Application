package com.example.cleanhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView register, forgotPass;
    Button login;
    EditText userName, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        MainDB dbh = new MainDB(this);

        register = findViewById(R.id.textView4);
        userName = findViewById(R.id.editTextTextPersonName3);
        password = findViewById(R.id.editTextTextPassword3);
        login = findViewById(R.id.button3);
        forgotPass = findViewById(R.id.textView3);


        register.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserRegistrationActivity.class);
            startActivity(intent);
        });

        login.setOnClickListener(v -> {
            String user = userName.getText().toString();
            String pass = password.getText().toString();

            if (user.equals("") || pass.equals("")) {
                Toast.makeText(LoginActivity.this, "You are missing username/password field", Toast.LENGTH_SHORT).show();
            } else {
                Boolean checkUserPass = dbh.checkUsernamePassword(user, pass);

                if (checkUserPass == true) {
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Username or Password. Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        forgotPass.setOnClickListener(v -> {
            String user = userName.getText().toString();
            Intent intent = new Intent(getApplicationContext(), RetrievePasswordActivity.class);
            startActivity(intent);
        });
    }
}