package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsFragment extends Fragment {
    static final String PREFS_NAME = "PrayerPrefs";
    static final String ALARM_ENABLED_KEY = "alarmEnabled";
    static final String NOTIFICATION_ENABLED_KEY = "notificationEnabled";
    private static final int TOAST_DURATION = Toast.LENGTH_SHORT;
    private CheckBox alarmCheckBox;
    private CheckBox notificationCheckBox;
    private TextView nameTextView;
    private TextView cityTextView;
    private Button namebtn;
    private Button citybtn;
    private String userName;
    private String city;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        alarmCheckBox = view.findViewById(R.id.alarmCheckBox);
        notificationCheckBox = view.findViewById(R.id.notificationCheckBox);
        namebtn = view.findViewById(R.id.button_name);
        citybtn = view.findViewById(R.id.button_city);
        nameTextView = view.findViewById(R.id.name);
        cityTextView = view.findViewById(R.id.city);

        // Load user preferences and set checkboxes accordingly
        loadPreferences();

        // Save user preferences and show toast when checkboxes are clicked
        alarmCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = alarmCheckBox.isChecked();
                savePreferences(ALARM_ENABLED_KEY, isChecked);
                showToast("Alarm " + (isChecked ? "enabled" : "disabled"));
            }
        });

        notificationCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = notificationCheckBox.isChecked();
                savePreferences(NOTIFICATION_ENABLED_KEY, isChecked);
                showToast("Notification " + (isChecked ? "enabled" : "disabled"));
            }
        });

        nameTextView.setText("Name: " + userName);
        cityTextView.setText("City: " + city);

        namebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Edit Your Name");

                // Set up the input
                final EditText input = new EditText(requireContext());
                input.setHint("Enter your name");
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userName = input.getText().toString();
                        if (!userName.isEmpty()) {
                            // Save the user's name
                            SharedPreferences sp = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("userName", userName);
                            editor.apply();
                            // Show a toast message indicating that the name has been updated
                            Toast.makeText(requireContext(), "Close settings to reflect changes.", Toast.LENGTH_SHORT).show();
                        } else {
                            // If name is empty, show a toast message
                            Toast.makeText(requireContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                // Show the dialog
                builder.setCancelable(false); // Prevent dialog from being dismissed by clicking outside of it
                builder.show();
            }
        });

        citybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Edit Your City");

                // Set up the input
                final EditText input = new EditText(requireContext());
                input.setHint("Enter your city");
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        city = input.getText().toString();
                        if (!city.isEmpty()) {
                            // Save the user's city
                            SharedPreferences sp = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("cityName", city);
                            editor.apply();
                            // Show a toast message indicating that the city has been updated
                            Toast.makeText(requireContext(), "Close settings to reflect changes.", Toast.LENGTH_SHORT).show();
                        } else {
                            // If city is empty, show a toast message
                            Toast.makeText(requireContext(), "Please enter a city", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                // Show the dialog
                builder.setCancelable(false); // Prevent dialog from being dismissed by clicking outside of it
                builder.show();
            }
        });
        return view;
    }

    private void loadPreferences() {
        SharedPreferences prefs = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences sp = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        this.userName = sp.getString("userName", "");
        this.city = sp.getString("cityName", "");
        boolean isAlarmEnabled = prefs.getBoolean(ALARM_ENABLED_KEY, true); // Default is true
        boolean isNotificationEnabled = prefs.getBoolean(NOTIFICATION_ENABLED_KEY, true); // Default is true

        alarmCheckBox.setChecked(isAlarmEnabled);
        notificationCheckBox.setChecked(isNotificationEnabled);
    }

    private void savePreferences(String key, boolean value) {
        SharedPreferences prefs = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, TOAST_DURATION).show();
    }
}
