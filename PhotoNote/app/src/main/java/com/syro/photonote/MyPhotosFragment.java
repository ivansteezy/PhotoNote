package com.syro.photonote;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyPhotosFragment extends Fragment {
    RecyclerView m_recyclerView;
    RecyclerView.LayoutManager m_layoutManager;
    MyPhotosRecyclerViewAdapter recyclerViewAdapter;

    int[] arr = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
                 R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};

    public MyPhotosFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_photos, container, false);
        m_recyclerView = (RecyclerView)view.findViewById(R.id.myPhotosRecyclerView);
        m_layoutManager = new GridLayoutManager(getActivity(), 2);
        m_recyclerView.setLayoutManager(m_layoutManager);
        recyclerViewAdapter = new MyPhotosRecyclerViewAdapter(arr, getActivity()); // in this constructor we should put strings with path to images given by sqlite instead of ints ids
        m_recyclerView.setAdapter(recyclerViewAdapter); //here crash
        m_recyclerView.setHasFixedSize(true);
        return view;
    }
}