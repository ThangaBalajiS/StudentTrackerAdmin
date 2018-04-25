package com.thangabalajis.studenttrackeradmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText etUname,etPw;
    final String USER_NAME = "sankar";
    final String PASS_WORD = "admin123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUname = findViewById(R.id.un);
        etPw = findViewById(R.id.pw);

    }

    public void login(View view){
        if (etUname.getText().toString().equals(USER_NAME)&& etPw.getText().toString().equals(PASS_WORD)){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(this,"Username or password incorrect",Toast.LENGTH_LONG).show();
        }
    }
}
