package com.syro.photonote;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AddPhotosFragment extends Fragment {

    String[] shutterSpeed = new String[]{"Velocidad", "1/1000", "1/500", "1/250", "1/125", "1/60",
                                         "1/30", "1/15", "1/8", "1/4", "1/2", "1'", "Bulb"};

    String[] apertures = new String[]{"Apertura", "f/32", "f/22",  "f/16",  "f/11",
                                     "f/8",  "f/5.6", "f/4",   "f/2.8",
                                     "f/2",  "f/1.4", "f/1.2", "f/1" };

    String[] lens = new String[]{"Distancia foc.", "12mm", "24mm", "50mm",
                                 "70mm", "135mm", "200mm"};

    String[] isos = new String[]{"ISO", "6400", "3200", "1600", "800",
                                 "400", "200", "100", "50", "25"};
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
        return view;
    }
}