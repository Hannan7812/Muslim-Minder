package com.example.myapplication;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FragmentTab1 extends Fragment {
    private RecyclerView buttonRecyclerView;
    private FragmentTab1 lifecycleOwner;
    private FeelingButtonAdapter adapter;
    private Module1ViewModel viewModel;
    private TextView positive;
    private TextView negative;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);

        buttonRecyclerView = view.findViewById(R.id.recyclerView);
        positive = view.findViewById(R.id.textView10);
        negative = view.findViewById(R.id.textView11);
        viewModel = new Module1ViewModel(getActivity().getApplication());
        lifecycleOwner = this;

        // Create a list of FeelingButtons with your desired text and IDs
        List<FeelingButton> pos_buttons = new ArrayList<>();
        pos_buttons.add(new FeelingButton("Content", 4, 2));
        pos_buttons.add(new FeelingButton("Confident", 5, 2));
        pos_buttons.add(new FeelingButton("Happy", 7, 2));
        pos_buttons.add(new FeelingButton("Grateful", 8, 2));
        pos_buttons.add(new FeelingButton("Excited", 23, 2));
        pos_buttons.add(new FeelingButton("Hopeful", 24, 2));


        List<FeelingButton> neg_buttons = new ArrayList<>();
        neg_buttons.add(new FeelingButton("Anxious", 1, 1));
        neg_buttons.add(new FeelingButton("Confused", 2, 1));
        neg_buttons.add(new FeelingButton("Angry", 3, 1));
        neg_buttons.add(new FeelingButton("Bored", 6, 1));
        neg_buttons.add(new FeelingButton("Depressed", 9, 1));
        neg_buttons.add(new FeelingButton("Lost", 10, 1));
        neg_buttons.add(new FeelingButton("Nervous", 11, 1));
        neg_buttons.add(new FeelingButton("Overwhelmed", 12, 1));
        neg_buttons.add(new FeelingButton("Scared", 13, 1));
        neg_buttons.add(new FeelingButton("Sad", 14, 1));
        neg_buttons.add(new FeelingButton("Unloved", 15, 1));
        neg_buttons.add(new FeelingButton("Suicidal", 16, 1));
        neg_buttons.add(new FeelingButton("Tired", 17, 1));
        neg_buttons.add(new FeelingButton("Jealous", 18, 1));
        neg_buttons.add(new FeelingButton("Insecure", 19, 1));
        neg_buttons.add(new FeelingButton("Irritated", 20, 1));
        neg_buttons.add(new FeelingButton("Uncertain", 21, 1));
        neg_buttons.add(new FeelingButton("Desperate", 22, 1));


        TypedValue typedValue = new TypedValue();
        requireActivity().getTheme().resolveAttribute(android.R.attr.colorPrimary, typedValue, true);
        int colorPrimary = typedValue.data;

        TypedValue typedValue2 = new TypedValue();
        requireActivity().getTheme().resolveAttribute(android.R.attr.textColor, typedValue2, true);
        int textColor = typedValue2.data;


        adapter = new FeelingButtonAdapter(pos_buttons, viewModel, lifecycleOwner);
        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(2, 20, true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        buttonRecyclerView.setLayoutManager(gridLayoutManager);
        buttonRecyclerView.addItemDecoration(itemDecoration);
        buttonRecyclerView.setAdapter(adapter);
        positive.setTextColor(colorPrimary);

        negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setButtons(neg_buttons);
                buttonRecyclerView.setAdapter(adapter);
                negative.setTextColor(colorPrimary);
                positive.setTextColor(textColor);
            }
        });

        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setButtons(pos_buttons);
                buttonRecyclerView.setAdapter(adapter);
                positive.setTextColor(colorPrimary);
                negative.setTextColor(textColor);
            }
        });


        return view;
    }
}

class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
    private int spanCount;
    private int spacing;
    private boolean includeEdge;

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int column = position % spanCount;

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount;
            outRect.right = (column + 1) * spacing / spanCount;

            if (position < spanCount) {
                outRect.top = spacing;
            }
            outRect.bottom = spacing;
        } else {
            int spacingPerItem = (spanCount - 1) * spacing / spanCount;
            int remainder = parent.getWidth() - spanCount * spacing - spacingPerItem * (spanCount - 1);
            int edgeSpacing = spacingPerItem + remainder / spanCount;

            outRect.left = column * spacingPerItem - column * edgeSpacing;
            outRect.right = edgeSpacing - (column + 1) * spacingPerItem + column * edgeSpacing;

            if (position >= spanCount) {
                outRect.top = spacing; // item top
            }
        }
    }
}