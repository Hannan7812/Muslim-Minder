package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentTab1 extends Fragment {
    private RecyclerView buttonRecyclerView;
    private FragmentTab1 lifecycleOwner;
    private FeelingButtonAdapter adapter;
    private Module1ViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);

        buttonRecyclerView = view.findViewById(R.id.recyclerView);
        viewModel = new Module1ViewModel(getActivity().getApplication());
        lifecycleOwner = this;

        // Create a list of FeelingButtons with your desired text and IDs
        List<FeelingButton> buttons = new ArrayList<>();
        buttons.add(new FeelingButton("Happy", 1));
        buttons.add(new FeelingButton("Sad", 2));
        buttons.add(new FeelingButton("Angry", 3));
        // ... add more buttons as needed

        adapter = new FeelingButtonAdapter(buttons, viewModel, lifecycleOwner);
        buttonRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        buttonRecyclerView.setAdapter(adapter);

        return view;
    }
}