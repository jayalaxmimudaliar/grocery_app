package com.example.myapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapp.R;

public class logout extends AppCompatActivity {
Button logout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences =getSharedPreferences(loginIn.newPref,0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("hasLoggedIn",false);
                editor.commit();

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                finish();

                editor1.clear();
                editor1.apply();
                finish();

                startActivity(new Intent(logout.this, loginIn.class));
            }
        });
    }
}