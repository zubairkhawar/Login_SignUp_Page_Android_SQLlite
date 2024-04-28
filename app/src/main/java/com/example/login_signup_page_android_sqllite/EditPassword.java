package com.example.login_signup_page_android_sqllite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

public class EditPassword extends AppCompatActivity{

    EditText email;
    Button reset;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_password);

        email = (EditText) findViewById(R.id.email_reset);
        reset = (Button) findViewById(R.id.reset_password);
        databaseHelper = new DatabaseHelper(this);

        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String cemail = email.getText().toString().trim();

                boolean checkemail = databaseHelper.checkEmail(cemail);
                if (checkemail){
                    Intent intent = new Intent(getApplicationContext(), ResetPassword.class);
                    intent.putExtra("Email", cemail);
                    startActivity(intent);
                    finish();
                }else
                {
                    Toast.makeText(EditPassword.this, "User does not exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
