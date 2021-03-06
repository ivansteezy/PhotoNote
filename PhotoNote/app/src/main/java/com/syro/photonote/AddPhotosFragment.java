package com.syro.photonote;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.syro.photonote.db.PhotoManager;
import com.syro.photonote.models.PhotoModel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Locale;

public class AddPhotosFragment extends Fragment implements LocationListener {
    private static final int GALLERY_REQUEST = 100;
    private static final int CAMERA_REQUEST  = 200;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    String imageFilePath;
    String[] shutterSpeed = new String[]{"Velocidad", "1/1000", "1/500", "1/250", "1/125", "1/60",
                                         "1/30", "1/15", "1/8", "1/4", "1/2", "1'", "Bulb"};

    String[] apertures = new String[]{"Apertura", "f/32", "f/22",  "f/16",  "f/11",
                                     "f/8",  "f/5.6", "f/4",   "f/2.8",
                                     "f/2",  "f/1.4", "f/1.2", "f/1" };

    String[] lens = new String[]{"Distancia foc.", "12mm", "24mm", "50mm",
                                 "70mm", "135mm", "200mm"};

    String[] isos = new String[]{"ISO", "6400", "3200", "1600", "800",
                                 "400", "200", "100", "50", "25"};

    CheckBox useLocationCheckBox;
    TextView textViewLocation;
    LocationManager locationManager;
    Spinner isoSpinner;
    Spinner speedSpinner;
    Spinner apertureSpinner;
    Spinner lensSpinner;
    Button saveButton;
    EditText cameraModelEditText;
    EditText numberOfShootEditText;
    EditText lensEditText;
    EditText commentsEditText;

    private ImageView selectedImageView;
    Bitmap image;


    public AddPhotosFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_photos, container, false);
        isoSpinner = view.findViewById(R.id.isoSpinner);
        speedSpinner = view.findViewById(R.id.speedSpinner);
        apertureSpinner = view.findViewById(R.id.apertureSpinner);
        lensSpinner = view.findViewById(R.id.focalLenghtSpinner);
        saveButton = view.findViewById(R.id.savePhotoDataButton);

        useLocationCheckBox = view.findViewById(R.id.useLocationCheck);
        textViewLocation = view.findViewById(R.id.locationTextView);
        selectedImageView = view.findViewById(R.id.selectedImageview);
        cameraModelEditText  = view.findViewById(R.id.cameraModelEditText);
        numberOfShootEditText = view.findViewById(R.id.numberOfShootEditText);
        lensEditText = view.findViewById(R.id.lensEditText);
        commentsEditText = view.findViewById(R.id.commentsEditText);

        //permissions
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
           != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, isos);
        isoSpinner.setAdapter(adapter);

        adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, shutterSpeed);
        speedSpinner.setAdapter(adapter);

        adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, apertures);
        apertureSpinner.setAdapter(adapter);

        adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, apertures);
        apertureSpinner.setAdapter(adapter);

        adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, lens);
        lensSpinner.setAdapter(adapter);

        useLocationCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    GetLocation();
                }
                else {
                    textViewLocation.setText("Ubicación desactivada");
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Here build a model and send it to the database
                SaveImage(image);
                ConsolidateData();
                DisplayAlertOnSavedData(v);
            }
        });

        selectedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetImage(v);
            }
        });
        return view;
    }

    void DisplayAlertOnSavedData(View v)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Hurra!");
        alert.setMessage("Se ha guardado correctamente tu foto!");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = alert.create();
        dialog.show();
    }

    @SuppressLint("MissingPermission")
    private void GetLocation()
    {
        try
        {
            locationManager = (LocationManager)getActivity().getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 5, AddPhotosFragment.this);
        }
        catch (Exception e)
        {
            System.out.println("Exception trying to get location");
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location)
    {
        System.out.println("Location is: "+location.getLatitude()+", "+location.getLongitude());
        try
        {
            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String address = addresses.get(0).getAddressLine(0);
            textViewLocation.setText(address);
        }
        catch (Exception e)
        {
            System.out.println("Exception tryin to geocode");
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) { }
    @Override
    public void onProviderEnabled(@NonNull String provider) { }
    @Override
    public void onProviderDisabled(@NonNull String provider) { }

    public void GetImage(View view)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getActivity().getPackageManager()) != null)
        {
            startActivityForResult(intent, CAMERA_REQUEST);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {
            Bundle extras = data.getExtras();
            image = (Bitmap)extras.get("data");
            selectedImageView.setImageBitmap(image);
        }
    }

    private void SaveImage(Bitmap image)
    {
        verifyStoragePermissions(getActivity());
        File filepath = Environment.getExternalStorageDirectory();
        File dir = new File(filepath.getAbsolutePath()+"/DCIM/PhotoNote/");

        if(!dir.exists())
            dir.mkdirs();

        File file = new File(dir, System.currentTimeMillis()+".jpg");

        try
        {
            FileOutputStream out = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

            imageFilePath = file.getPath();
            System.out.println("Image saved in: " + file.getPath());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ConsolidateData()
    {
        PhotoModel photoModel = new PhotoModel();

        photoModel.setPhotoReference(imageFilePath);
        photoModel.setCameraModel(cameraModelEditText.getText().toString());
        photoModel.setIso(isoSpinner.getSelectedItem().toString());
        photoModel.setSpeedShutter(speedSpinner.getSelectedItem().toString());
        photoModel.setAperture(apertureSpinner.getSelectedItem().toString());
        photoModel.setLightComments(commentsEditText.getText().toString());
        photoModel.setLens(lensEditText.getText().toString());
        photoModel.setFocalLenght(lensSpinner.getSelectedItem().toString());
        photoModel.setNumberOfShoot(numberOfShootEditText.getText().toString());
        photoModel.setPhotoLocation(textViewLocation.getText().toString());

        PhotoManager photoManager = new PhotoManager();
        photoManager.SavePhotoInDb(getActivity(), photoModel);
    }


    public static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}