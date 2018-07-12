package com.example.galax.a20180707_study_chapter4_7;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class ViewerFragment extends Fragment {

    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_viewer, container, false);

        imageView = (ImageView) rootview.findViewById(R.id.imageView);

        return rootview;
    }

    public void setImage(int index){
        if(index ==0){
            imageView.setImageResource(R.drawable.image1);
        } else if(index ==1){
            imageView.setImageResource(R.drawable.image2);
        } else if(index ==2){
            imageView.setImageResource(R.drawable.image3);
        }
    }
}
