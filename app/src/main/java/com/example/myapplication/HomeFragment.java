package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import androidx.appcompat.app.AlertDialog;

import org.w3c.dom.Text;


public class HomeFragment extends Fragment {
    private TextView NameTextView;
    private TextView timeTextView;

    private Handler handler = new Handler();
    private TextView dateTextView;
    public static String userName;
    private SharedPreferences sp;
    private String cityName = "Islamabad";
    private TextView textView;


    private void updateTimeAndDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timesdf = new SimpleDateFormat("HH:mm");
        SimpleDateFormat datesdf = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
        String currentTime = timesdf.format(calendar.getTime());
        String currentDate = datesdf.format(calendar.getTime());
        textView.setText(currentTime);
        dateTextView.setText(currentDate);
    }

    private Runnable timeAndDateUpdater = new Runnable() {
        @Override
        public void run() {
            updateTimeAndDate();
            handler.postDelayed(this, 1000);
        }
    };

    protected void onDestory() {
        super.onDestroy();
        handler.removeCallbacks(timeAndDateUpdater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        textView = view.findViewById(R.id.ciytTextView);
        dateTextView = view.findViewById(R.id.textView6);
        NameTextView = view.findViewById(R.id.tvName);
        updateTimeAndDate();
        handler.post(timeAndDateUpdater);

        sp = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        userName = sp.getString("userName", "");
        if (userName.isEmpty()) {
            showNameDialog();
        } else {
            editName();
        }

        cityName = sp.getString("cityName", "");


        Button favorites = view.findViewById(R.id.fav_button);
        TextView arabic = view.findViewById(R.id.dua_arabic);
        TextView translation = view.findViewById(R.id.dua_translation);
        TextView reference = view.findViewById(R.id.reference);

        favorites.setVisibility(View.GONE);
        arabic.setText("اللَّهُمَّ اسْتُرْ عَوْرَاتِهِم وَآمِنْ رَوْعَاتِهِم وَاحْفَظْهُم مِنْ بَيْنِ أَيْدِيهِم وَمِنْ خَلْفِهِم وَعَنْ أَيمَانِهِم وَعَنْ شَمَائلِهِم وَمِنْ فَوْقِهِم");
        translation.setText("O Allah, conceal their faults, calm their fears, and protect them from before them and behind them, from their right and from their left, and from above them.");
        reference.setVisibility(View.GONE);

        RandomElementSelector elementSelector = new RandomElementSelector(requireContext(), 99);
        int selectedElement = elementSelector.getSelectedElement();
        String[] name_element = NamesFragment.data[selectedElement];
        TextView tvNumber = view.findViewById(R.id.tvNumber);
        TextView tvArabicName = view.findViewById(R.id.tvArabicName);
        TextView tvEnglishName = view.findViewById(R.id.tvEnglishName);
        TextView tvMeaning = view.findViewById(R.id.tvMeaning);

        tvNumber.setText(String.valueOf(selectedElement));
        tvArabicName.setText(name_element[0]);
        tvEnglishName.setText(name_element[1]);
        tvMeaning.setText(name_element[2]);
        view.findViewById(R.id.name_of_the_day).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvMeaning.getVisibility() == View.GONE) {
                    tvMeaning.setVisibility(View.VISIBLE);
                    v.setBackgroundColor(Color.TRANSPARENT);
                } else {
                    tvMeaning.setVisibility(View.GONE);
                    v.setBackgroundColor(Color.DKGRAY);
                }
            }
        });

        return view;
    }
    private void showNameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Enter Your Name");

        final EditText input = new EditText(requireContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userName = input.getText().toString().trim();
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("userName", userName);
                editor.apply();
                editName();
            }
        });
        builder.setNegativeButton("Skip", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NameTextView.setText("Salam!");
            }
        });

        builder.setCancelable(false);
        builder.show();
    }
    public void editName() {
        NameTextView.setText("Salam, " + userName + "!");
        NameTextView.setVisibility(View.VISIBLE);
    }


    public void showCityDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Enter Your City");

        final EditText input = new EditText(requireContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cityName = input.getText().toString().trim();
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("cityName", cityName);
                editor.apply();
                editName();
            }
        });
    }
}