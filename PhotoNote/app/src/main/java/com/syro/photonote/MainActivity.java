package com.syro.photonote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}