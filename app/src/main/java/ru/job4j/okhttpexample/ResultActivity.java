package ru.job4j.okhttpexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView textViewLoginResult;
    String loginResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewLoginResult=findViewById(R.id.textViewLoginResult);
        Intent intent = getIntent();
        if(intent!=null){
            loginResult=intent.getStringExtra(MainActivity.LOGIN_RESULT);
        }
        textViewLoginResult.setText(loginResult);
    }

}
