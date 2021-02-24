package com.syro.photonote;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.UserManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syro.photonote.db.UsersManager;
import com.syro.photonote.models.UserModel;

public class MyProfileFragment extends Fragment {

    TextView displayName;
    TextView displayLastName;
    TextView displayEmail;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_profile, container, false);

        displayName = v.findViewById(R.id.displayUserName);
        displayLastName = v.findViewById(R.id.displayUserLastName);
        displayEmail = v.findViewById(R.id.displayUserEmail);

        GetUserInfo();
        return v;
    }

    public void GetUserInfo()
    {
        UsersManager userManager = new UsersManager();
        UserModel userModel = userManager.GetUserByEmail(getActivity(), "network.9961@gmail.com");

        displayName.setText(userModel.getName());
        displayLastName.setText(userModel.getLastName());
        displayEmail.setText(userModel.getEmail());
    }
}