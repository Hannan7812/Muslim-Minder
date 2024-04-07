package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import data.Duas;

public class Module1 extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module1);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
    }
}


class FeelingButton {
    private String text;
    private int id;

    public FeelingButton(String text, int id){
        this.text = text;
        this.id = id;
    }

    public String getText(){
        return text;
    }

    public int getId(){
        return id;
    }

    public void setText(String text){
        this.text = text;
    }

    public void setId(int id){
        this.id = id;
    }
}

class ButtonViewHolder extends RecyclerView.ViewHolder {
    Button dbutton;

    public ButtonViewHolder(@NonNull View itemView) {
        super(itemView);
        dbutton = itemView.findViewById(R.id.button_id);
    }
}

class FeelingButtonAdapter extends RecyclerView.Adapter<ButtonViewHolder> {
    private List<FeelingButton> feelingButtons;
    private Module1ViewModel viewModel;
    private FragmentTab1 lifecycleOwner;
    private TabLayout tabLayout;

    public FeelingButtonAdapter(List<FeelingButton> feelingButtons, Module1ViewModel viewModel, FragmentTab1 lifecycleOwner){
        this.feelingButtons = feelingButtons;
        this.viewModel = viewModel;
        this.lifecycleOwner = lifecycleOwner;
        this.tabLayout = lifecycleOwner.getActivity().findViewById(R.id.tabLayout);
    }

    @NonNull
    @Override
    public ButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.button_item, parent, false);
        return new ButtonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ButtonViewHolder holder, int position) {
        FeelingButton feelingButton = feelingButtons.get(position);
        holder.dbutton.setText(feelingButton.getText());
        holder.dbutton.setId(feelingButton.getId());

        holder.dbutton.setOnClickListener(v -> {
        viewModel.getDuasById(feelingButton.getId()).observe(lifecycleOwner, new Observer<List<Duas>>() {

            @Override
            public void onChanged(List<Duas> duas) {
                if (duas != null) {
                    FragmentManager fragmentManager = lifecycleOwner.getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment duaCardFragment = new DuaCardFragment(duas);
                    fragmentTransaction.replace(R.id.frameLayoutTab1, duaCardFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });
    });
    }

    @Override
    public int getItemCount() {
        return feelingButtons.size();
    }

}

class ViewPagerAdapter extends FragmentStateAdapter{
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new FragmentTab1();
            case 1:
                return new FragmentTab2();
            default:
                return new FragmentTab1();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

class DuaViewHolder extends RecyclerView.ViewHolder {
    TextView arabicText;
    TextView englishText;

    public DuaViewHolder(@NonNull View itemView) {
        super(itemView);
        arabicText = itemView.findViewById(R.id.dua_arabic);
        englishText = itemView.findViewById(R.id.dua_translation);
    }
}

class DuaAdapter extends RecyclerView.Adapter<DuaViewHolder> {
    private List<Duas> duas;

    public DuaAdapter(List<Duas> duas, LifecycleOwner lifecycleOwner){
        this.duas = duas;
    }

    @NonNull
    @Override
    public DuaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dua_item, parent, false);
        return new DuaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DuaViewHolder holder, int position) {
        Duas dua = duas.get(position);
        holder.arabicText.setText(dua.toString());
        holder.englishText.setText(dua.getId());
    }

    @Override
    public int getItemCount() {
        return duas.size();
    }
}