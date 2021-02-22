package com.syro.photonote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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
        m_bottomNavigationView.setSelectedItemId(R.id.myPhotosItem);
        OpenFragment(new MyPhotosFragment());
        m_bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.myPhotosItem:
                        OpenFragment(new MyPhotosFragment());
                        item.setChecked(true);
                    break;

                    case R.id.addPhotoItem:
                        OpenFragment(new AddPhotosFragment());
                        item.setChecked(true);
                    break;

                    case R.id.myProfileItem:
                        OpenFragment(new MyProfileFragment());
                        item.setChecked(true);
                    break;
                }
                return false;
            }
        });
    }

    void OpenFragment(Fragment fragment)
    {
        Fragment mFragment = null;
        mFragment = fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.contentFrame, mFragment).commit();
    }
}