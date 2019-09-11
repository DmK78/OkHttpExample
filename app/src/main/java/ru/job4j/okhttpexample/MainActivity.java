package ru.job4j.okhttpexample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextPassword;
    private String userName;
    private String userPassword;
    private Button buttonLogin;
    OkHttpClient client = new OkHttpClient();
    public static final String LOGIN_RESULT = "loginResult";
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = findViewById(R.id.editTextLogin);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
    }


    public void startLogin(View view) {

        RequestBody formBody = new FormBody.Builder()
                .add("method", "login")
                .add("login", editTextName.getText().toString())
                .add("password", editTextPassword.getText().toString())
                .build();

        RequestBody formBody1 = new FormBody.Builder()
                .add("userId", "dima")
                .add("title", editTextName.getText().toString())
                .add("body", editTextPassword.getText().toString())
                .build();

        String url = "https://job4j.ru/TrackStudio/LoginAction.do/";
        String url1 = "https://jsonplaceholder.typicode.com/posts/";

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String strResponse = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                            intent.putExtra(LOGIN_RESULT, strResponse);
                            startActivity(intent);
                        }
                    });

                }

            }
        });


        // Do something with the response.


    }


}