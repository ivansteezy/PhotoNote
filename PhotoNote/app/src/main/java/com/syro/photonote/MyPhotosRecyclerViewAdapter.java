package com.syro.photonote;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyPhotosRecyclerViewAdapter extends RecyclerView.Adapter<MyPhotosRecyclerViewAdapter.MyPhotoViewHolder> {
    private int []m_items;

    public MyPhotosRecyclerViewAdapter(int[] items) {
        this.m_items = items;
    }


    @NonNull
    @Override
    public MyPhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_photo_item, parent, false); // ojo
        MyPhotoViewHolder myPhotoViewHolder = new MyPhotoViewHolder(view);
        return myPhotoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyPhotoViewHolder holder, int position) {
        holder.m_imageView.setImageResource(m_items[position]);
        holder.m_textView.setText("Image No: " + position);
    }

    @Override
    public int getItemCount() {
        return m_items.length;
    }

    public class MyPhotoViewHolder extends RecyclerView.ViewHolder
    {
        ImageView m_imageView;
        TextView m_textView;
        public MyPhotoViewHolder(@NonNull View itemView)
        {
            super(itemView);
            m_imageView = itemView.findViewById(R.id.imageItemView);
            m_textView  = itemView.findViewById(R.id.imageItemText);
        }
    }
}
