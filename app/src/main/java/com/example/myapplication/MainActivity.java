package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    
    ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home){
                replaceFragment(new HomeFragment());
                return true;
            } else if (item.getItemId() == R.id.supplications) {
                replaceFragment(new FragmentTab1());
                return true;
            } else if (item.getItemId() == R.id.favorites) {
                replaceFragment(new FragmentTab2());
                return true;
            } else if (item.getItemId() == R.id.more) {
                replaceFragment(new MoreFragment());
                return true;
            }
            return true;
        });

    }



    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}