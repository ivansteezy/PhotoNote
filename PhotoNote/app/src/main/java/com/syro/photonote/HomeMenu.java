package com.syro.photonote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeMenu extends AppCompatActivity {
    private TextView m_infoTextView;
    private BottomNavigationView m_bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);

        m_bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavigationMenu);
        m_infoTextView = (TextView)findViewById(R.id.infoTextView);

        m_bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.myPhotosItem:
                        m_infoTextView.setText("Mis fotos");
                        item.setChecked(true);
                    break;

                    case R.id.addPhotoItem:
                        m_infoTextView.setText("Agregar foto");
                        item.setChecked(true);
                    break;

                    case R.id.myProfileItem:
                        m_infoTextView.setText("Mi perfil");
                        item.setChecked(true);
                    break;
                }
                return false;
            }
        });
    }
}