package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Calendar;
import java.util.Random;

public class RandomElementSelector {
    private static final String PREF_NAME = "RandomElementPrefs";
    private static final String KEY_SELECTED_ELEMENT = "selectedElement";
    private static final String KEY_SELECTED_DATE = "selectedDate";

    private Context context;
    private SharedPreferences prefs;
    private Random random;
    private int num;

    public RandomElementSelector(Context context, int num) {
        this.context = context;
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        random = new Random();
        this.num = num;
    }

    public int getSelectedElement() {
        int selectedElement;
        long selectedDateMillis = prefs.getLong(KEY_SELECTED_DATE, 0);
        Calendar savedDate = Calendar.getInstance();
        savedDate.setTimeInMillis(selectedDateMillis);

        Calendar currentDate = Calendar.getInstance();
        if (currentDate.get(Calendar.DAY_OF_YEAR) == savedDate.get(Calendar.DAY_OF_YEAR) &&
                currentDate.get(Calendar.YEAR) == savedDate.get(Calendar.YEAR)) {
            // If the stored date is today, retrieve the stored element
            selectedElement = prefs.getInt(KEY_SELECTED_ELEMENT, 0);
        } else {
            // Otherwise, generate a new random element
            selectedElement = random.nextInt(num); // Assuming the array contains 100 elements
            // Store the new selected element and today's date
            prefs.edit().putInt(KEY_SELECTED_ELEMENT, selectedElement)
                    .putLong(KEY_SELECTED_DATE, currentDate.getTimeInMillis())
                    .apply();
        }
        return selectedElement;
    }
}