package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import data.Duas;

public class FragmentTab2 extends Fragment {
    private RecyclerView duaRecyclerView;
    private FragmentTab2 lifecycleOwner;
    private DuaAdapter adapter;
    private Module1ViewModel viewModel;
    List<Duas> buttons;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);

        duaRecyclerView = view.findViewById(R.id.recyclerViewTab2);
        viewModel = new Module1ViewModel(getActivity().getApplication());
        lifecycleOwner = this;

        // Create a list of FeelingButtons with your desired text and IDs
        viewModel = new Module1ViewModel(getActivity().getApplication());
        viewModel.getAllDuas().observe(getViewLifecycleOwner(), new Observer<List<Duas>>() {
            @Override
            public void onChanged(List<Duas> duas) {
                buttons = duas;
                adapter = new DuaAdapter(buttons, lifecycleOwner);
                duaRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                duaRecyclerView.setAdapter(adapter);


            }
        });
        // ... add more buttons as needed

        return view;
    }
}