package com.syro.photonote;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class AddPhotosFragment extends Fragment implements LocationListener {

    String[] shutterSpeed = new String[]{"Velocidad", "1/1000", "1/500", "1/250", "1/125", "1/60",
                                         "1/30", "1/15", "1/8", "1/4", "1/2", "1'", "Bulb"};

    String[] apertures = new String[]{"Apertura", "f/32", "f/22",  "f/16",  "f/11",
                                     "f/8",  "f/5.6", "f/4",   "f/2.8",
                                     "f/2",  "f/1.4", "f/1.2", "f/1" };

    String[] lens = new String[]{"Distancia foc.", "12mm", "24mm", "50mm",
                                 "70mm", "135mm", "200mm"};

    String[] isos = new String[]{"ISO", "6400", "3200", "1600", "800",
                                 "400", "200", "100", "50", "25"};

    Button buttonLocation;
    TextView textViewLocation;
    LocationManager locationManager;

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
        Spinner isoSpinner = view.findViewById(R.id.isoSpinner);
        Spinner speedSpinner = view.findViewById(R.id.speedSpinner);
        Spinner apertureSpinner = view.findViewById(R.id.apertureSpinner);
        Spinner lensSpinner = view.findViewById(R.id.focalLenghtSpinner);
        Button saveButton = view.findViewById(R.id.savePhotoDataButton);

        buttonLocation = view.findViewById(R.id.ubicationPhotoButton);
        textViewLocation = view.findViewById(R.id.locationTextView);

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

        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetLocation();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Here build a model and send it to the database
                //SendDataToDB();
                System.out.println(isoSpinner.getSelectedItem().toString());
            }
        });

        return view;
    }

    @SuppressLint("MissingPermission")
    private void GetLocation()
    {
        try
        {
            locationManager = (LocationManager)getActivity().getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 200, 5, AddPhotosFragment.this);
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
    public void onStatusChanged(String provider, int status, Bundle extras)
    {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider)
    {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider)
    {

    }
}