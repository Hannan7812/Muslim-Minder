package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import data.Dua;

public class DuaCardFragment extends Fragment {
    private List<Dua> duas;
    private RecyclerView duaRecyclerView;
    private FragmentActivity lifecycleOwner;
    private DuaAdapter adapter;
    private FragmentManager fragmentManager;
    private Module1ViewModel viewModel;

    public DuaCardFragment() {
        // Required empty public constructor
    }

    public DuaCardFragment(List<Dua> duas, Module1ViewModel viewModel) {
        this.duas = duas;
        this.viewModel = viewModel;
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

        adapter = new DuaAdapter(duas, lifecycleOwner, viewModel);
        duaRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        duaRecyclerView.setAdapter(adapter);

        Button back = view.findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.popBackStack(null, fragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
        return view;
    }

}