package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MoreFragment extends Fragment {
    private String userName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);

        ImageButton names_btn = view.findViewById(R.id.button);
        ImageButton tesbeeh_btn = view.findViewById(R.id.button2);
        ImageButton editname_btn = view.findViewById(R.id.imageButton2);
        ImageButton times_btn = view.findViewById(R.id.button3);

        names_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new NamesFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, fragment, fragment.getClass().getSimpleName())
                        .addToBackStack(null)
                        .commit();
            }
        });
        editname_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SettingsFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, fragment, fragment.getClass().getSimpleName())
                        .addToBackStack(null)
                        .commit();
            }
        });

        tesbeeh_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TabeehCounter.class);
                startActivity(intent);
            }
        });

        times_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new TimesFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, fragment, fragment.getClass().getSimpleName())
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }
}