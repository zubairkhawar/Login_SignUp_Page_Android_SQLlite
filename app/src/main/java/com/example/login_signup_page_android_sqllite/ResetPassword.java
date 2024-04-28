package com.example.login_signup_page_android_sqllite;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

public class ResetPassword extends AppCompatActivity{
    TextView email;
    EditText pass, repass;
    Button confirm_btn;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);

        email = (TextView) findViewById(R.id.email_reset);
        pass = (EditText) findViewById(R.id.password_reset);
        repass = (EditText) findViewById(R.id.repassword_reset);
        confirm_btn = (Button) findViewById(R.id.confirm_button);
        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        email.setText(intent.getStringExtra("email"));

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email_check = email.getText().toString().trim();
                String password = pass.getText().toString().trim();
                String repassword = repass.getText().toString().trim();
                if (password.equals(repassword)) {


                    Boolean check_email = databaseHelper.updatePassword(email_check, password);
                    if (check_email == true) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(ResetPassword.this, "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ResetPassword.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(ResetPassword.this, "Passwords Do Not Match", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
