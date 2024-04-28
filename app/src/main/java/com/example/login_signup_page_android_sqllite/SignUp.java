package com.example.login_signup_page_android_sqllite;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText spName, spEmail, spWebsite, spPassword, spConfirm;
    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        databaseHelper = new DatabaseHelper(this);

        spName = findViewById(R.id.username);
        spEmail = findViewById(R.id.email);
        spWebsite = findViewById(R.id.web_site);
        spPassword = findViewById(R.id.password_toggle);
        spConfirm = findViewById(R.id.repassword);
        signupButton = findViewById(R.id.signupbtn);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = spName.getText().toString().trim();
                String email = spEmail.getText().toString().trim();
                String website = spWebsite.getText().toString().trim();
                String password = spPassword.getText().toString().trim();
                String confirmPassword = spConfirm.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(SignUp.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    boolean checkUserEmail = databaseHelper.checkEmail(email);

                    if (!checkUserEmail) {
                        boolean insert = databaseHelper.insertData(name, email, website, password);

                        if (insert) {
                            Toast.makeText(SignUp.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(SignUp.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignUp.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
