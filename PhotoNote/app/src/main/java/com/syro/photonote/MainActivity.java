package com.syro.photonote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.UserManager;
import android.view.View;
import android.widget.EditText;

import com.syro.photonote.db.SQLiteConnectionHelper;
import com.syro.photonote.db.UsersManager;
import com.syro.photonote.models.UserModel;

public class MainActivity extends AppCompatActivity {

    EditText emailLogin;
    EditText passwordLogin;
    UserModel userResult;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailLogin = findViewById(R.id.emailLogin);
        passwordLogin = findViewById(R.id.passwordLogin);

        SQLiteConnectionHelper conn = new SQLiteConnectionHelper(this, "db_photoNote", null, 1);
    }

    public void OpenRegisterActivity(View view)
    {
        Intent myIntent = new Intent(MainActivity.this, RegisterActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void InputErrorDialog(View view)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Error");
        alert.setMessage("Datos ingresados incorrectos");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void LoginError(View view)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Error");
        alert.setMessage("El usuario no existe en la base de datos");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void PasswordError(View view)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Error");
        alert.setMessage("La constraseña es incorrecta");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void LogIn(View view)
    {
        UsersManager userManager = new UsersManager();
        userResult = userManager.GetUserByEmail(this, emailLogin.getText().toString());
        ValidateLogin(view);
    }

    public void ValidateLogin(View view)
    {
        if(userResult.getEmail() == null)
        {
            LoginError(view);
        }
        else
        {
            ValidatePassword(view);
        }
    }

    public void OpenHomeActivity(View view)
    {
        Intent myIntent = new Intent(MainActivity.this, HomeMenu.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void ValidatePassword(View view)
    {
        if(userResult.getPassword().equals(passwordLogin.getText().toString()))
        {
            OpenHomeActivity(view);
        }
        else
        {
            PasswordError(view);
        }
    }
}