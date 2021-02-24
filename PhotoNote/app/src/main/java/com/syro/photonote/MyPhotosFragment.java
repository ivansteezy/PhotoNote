package com.syro.photonote;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syro.photonote.db.PhotoManager;
import com.syro.photonote.models.PhotoModel;

import java.util.List;

public class MyPhotosFragment extends Fragment {
    RecyclerView m_recyclerView;
    RecyclerView.LayoutManager m_layoutManager;
    MyPhotosRecyclerViewAdapter recyclerViewAdapter;
    List<PhotoModel> photosList;

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
        m_recyclerView.setAdapter(recyclerViewAdapter);
        m_recyclerView.setHasFixedSize(true);

        GetAllPhotoInfo();
        return view;
    }

    public void GetAllPhotoInfo()
    {
        PhotoManager photoManager = new PhotoManager();
        photosList = photoManager.GetAllPhotos(getActivity());
    }
}