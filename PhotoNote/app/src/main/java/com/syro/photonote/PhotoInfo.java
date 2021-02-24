package com.syro.photonote;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.syro.photonote.models.PhotoModel;

public class PhotoInfo extends Fragment {

    private PhotoModel photoInfo;
    private ImageView displayTakenPhoto;
    private TextView displayCameraModel;
    private TextView displayNumberOfShoot;
    private TextView displayIso;
    private TextView displayeShutterSpeed;
    private TextView displayAperture;
    private TextView displaylens;
    private TextView displayFocalLenght;
    private TextView displayComments;
    private TextView displayLocation;

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

        displayTakenPhoto = v.findViewById(R.id.displayTakenPhoto);
        displayCameraModel = v.findViewById(R.id.displayCameraModel);
        displayNumberOfShoot = v.findViewById(R.id.displayNumberOfShoot);
        displayIso = v.findViewById(R.id.displayIso);
        displayeShutterSpeed = v.findViewById(R.id.displayeShutterSpeed);
        displayAperture = v.findViewById(R.id.displayAperture);
        displaylens = v.findViewById(R.id.displaylens);
        displayFocalLenght = v.findViewById(R.id.displayFocalLenght);
        displayComments = v.findViewById(R.id.displayComments);
        displayLocation = v.findViewById(R.id.displayLocation);

        BindAllData();
        return v;
    }

    void BindAllData()
    {
        Bitmap photoTakenBitmap = BitmapFactory.decodeFile(photoInfo.getPhotoReference());
        displayTakenPhoto.setImageBitmap(photoTakenBitmap);
        displayCameraModel.setText(photoInfo.getCameraModel());
        displayNumberOfShoot.setText(photoInfo.getNumberOfShoot());
        displayIso.setText(photoInfo.getIso());
        displayeShutterSpeed.setText(photoInfo.getSpeedShutter());
        displayAperture.setText(photoInfo.getAperture());
        displaylens.setText(photoInfo.getLens());
        displayFocalLenght.setText(photoInfo.getFocalLenght());
        displayComments.setText(photoInfo.getLightComments());
        displayLocation.setText(photoInfo.getPhotoLocation());
    }
}