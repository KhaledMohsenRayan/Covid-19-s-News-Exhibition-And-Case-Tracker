package com.example.coronareport.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.coronareport.R;


public class SymptomsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        // Inflate the layout for this fragment
//        ImageView closeBtn = findViewById(R.id.symptoms_close);
//        closeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//
//
//
//        ImageView github = findViewById(R.id.sgithub);
//        github.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("")));
//            }
//        });
//
//
//        ImageView facebook = findViewById(R.id.sfacebook);
//        facebook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("")));
//            }
//        });

        return inflater.inflate(R.layout.fragment_symptoms, container, false);
    }
}