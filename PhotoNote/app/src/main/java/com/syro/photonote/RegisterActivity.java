package com.syro.photonote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.syro.photonote.db.UsersManager;
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
        setContentView(R.layout.activity_register);

        nameEditText = findViewById(R.id.nameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
    }

    public void OpenLoginActivity(View view)
    {
        Intent myIntent = new Intent(RegisterActivity.this, MainActivity.class);
        RegisterActivity.this.startActivity(myIntent);
    }

    public void RegisterUser(View v)
    {
        UserModel userModel = new UserModel();
        userModel.setName(nameEditText.getText().toString());
        userModel.setLastName(lastNameEditText.getText().toString());
        userModel.setEmail(emailEditText.getText().toString());
        userModel.setPassword(passwordEditText.getText().toString());
        userModel.setConfirmPassword(confirmPasswordEditText.getText().toString());

        UsersManager usersManager = new UsersManager();
        usersManager.RegisterNewUser(this, userModel);

        AlertOnUserRegister(v);
    }

    void AlertOnUserRegister(View v)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Hurra!");
        alert.setMessage("Se ha registrado correctamente!");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = alert.create();
        dialog.show();
    }
}