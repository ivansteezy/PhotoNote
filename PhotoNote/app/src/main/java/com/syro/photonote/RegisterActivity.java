package com.syro.photonote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void OpenLoginActivity(View view)
    {
        Intent myIntent = new Intent(RegisterActivity.this, MainActivity.class);
        RegisterActivity.this.startActivity(myIntent);
    }
}