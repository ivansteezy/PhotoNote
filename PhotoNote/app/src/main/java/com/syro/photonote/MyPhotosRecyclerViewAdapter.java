package com.syro.photonote;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.syro.photonote.models.PhotoModel;

import java.io.Console;
import java.io.File;
import java.util.List;

public class MyPhotosRecyclerViewAdapter extends RecyclerView.Adapter<MyPhotosRecyclerViewAdapter.MyPhotoViewHolder> {
    private List<PhotoModel> photosInfo;
    Context m_context;

    public MyPhotosRecyclerViewAdapter(List<PhotoModel> photosInfo, Context context) {
        this.m_context = context;
        this.photosInfo = photosInfo;
    }


    @NonNull
    @Override
    public MyPhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_photo_item, parent, false); // ojo
        MyPhotoViewHolder myPhotoViewHolder = new MyPhotoViewHolder(view);

        myPhotoViewHolder.m_photoContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Se ha presionado un item: " + myPhotoViewHolder.getAdapterPosition());

                Fragment mFragment = new PhotoInfo(photosInfo.get(myPhotoViewHolder.getAdapterPosition()));         //Here pass de model of a photo
                FragmentManager fragmentManager = ((AppCompatActivity)m_context).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contentFrame, mFragment).commit();
            }
        });

        return myPhotoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyPhotoViewHolder holder, int position) {
        try
        {
            File imgFile = new File(photosInfo.get(position).getPhotoReference());
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            holder.m_imageView.setImageBitmap(myBitmap); //each element of array has an path to image
            holder.m_textView.setText("Image No: " + position);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return photosInfo.size();
    }

    public class MyPhotoViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout m_photoContainer;
        ImageView m_imageView;
        TextView m_textView;
        public MyPhotoViewHolder(@NonNull View itemView)
        {
            super(itemView);
            m_photoContainer = itemView.findViewById(R.id.myPhotoItemContainer);
            m_imageView      = itemView.findViewById(R.id.imageItemView);
            m_textView       = itemView.findViewById(R.id.imageItemText);
        }
    }
}
