package com.syro.photonote;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syro.photonote.models.PhotoModel;

public class PhotoInfo extends Fragment {

    private PhotoModel photoInfo;
    private TextView m_textviewInfo;

    public PhotoInfo(PhotoModel photoInfo)
    {
        this.photoInfo = photoInfo;
        System.out.println(this.photoInfo.getCameraModel());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photo_info, container, false);
        // Inflate the layout for this fragment
        this.m_textviewInfo = (TextView)v.findViewById(R.id.myPhotoInfo);
        m_textviewInfo.setText("Esta es la foto no: " + photoInfo.getNumberOfShoot());
        return v;
    }
}