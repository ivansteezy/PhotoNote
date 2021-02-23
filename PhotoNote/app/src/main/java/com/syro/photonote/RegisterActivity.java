package com.syro.photonote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.syro.photonote.models.UserModel;

public class RegisterActivity extends AppCompatActivity {
    EditText nameEditText;
    EditText lastNameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    EditText confirmPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nameEditText = findViewById(R.id.nameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);

        setContentView(R.layout.activity_register);
    }

    public void OpenLoginActivity(View view)
    {
        Intent myIntent = new Intent(RegisterActivity.this, MainActivity.class);
        RegisterActivity.this.startActivity(myIntent);
    }

    public void RegisterUser(View v)
    {
        UserModel userModel = new UserModel();
        userModel.setNameEdit(nameEditText.getText().toString());
        userModel.setLastNameEdit(lastNameEditText.getText().toString());
        userModel.setEmailEdit(emailEditText.getText().toString());
        userModel.setPasswordEdit(passwordEditText.getText().toString());
        userModel.setConfirmPasswordEdit(confirmPasswordEditText.getText().toString());


    }
}