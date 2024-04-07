package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.List;

import data.Duas;

public class DuaCardFragment extends Fragment {
    private List<Duas> duas;
    private RecyclerView duaRecyclerView;
    private FragmentActivity lifecycleOwner;
    private DuaAdapter adapter;
    private FragmentManager fragmentManager;

    public DuaCardFragment() {
        // Required empty public constructor
    }

    public DuaCardFragment(List<Duas> duas) {
        this.duas = duas;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dua_card, container, false);
        view.setBackgroundColor(Color.parseColor("#FF000000"));
        duaRecyclerView = view.findViewById(R.id.recycler_view_dua_card);
        lifecycleOwner = this.getActivity();
        fragmentManager = this.getParentFragmentManager();

        adapter = new DuaAdapter(duas, lifecycleOwner);
        duaRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        duaRecyclerView.setAdapter(adapter);

        Button back = view.findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.popBackStack();
            }
        });
        return view;
    }

}