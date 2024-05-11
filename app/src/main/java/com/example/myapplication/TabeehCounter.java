package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TabeehCounter extends AppCompatActivity {
    public Button buttonAdd, buttonSubtract, buttonReset;
    public TextView tvCount, tvLimit;
    int count = 0;
    int limit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabeeh_counter);
        buttonAdd = findViewById(R.id.btnAdd);
        buttonSubtract = findViewById(R.id.btnSubtract);
        buttonReset = findViewById(R.id.btnReset);
        tvCount = findViewById(R.id.tvResult);
        tvLimit = findViewById(R.id.tvLimit);

        showLimitDialog(); // setting limit as soon as activity starts

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count < limit || limit == 0) {
                    count++;
                    checkLimit(); // Checking if the limit count is reached
                    updateLimitDisplay();
                    tvCount.setText(String.valueOf(count));
                }
            }
        });
        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 0) {
                    count--;
                    updateLimitDisplay();
                }
                tvCount.setText(String.valueOf(count));
            }
        });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLimitDialog(); // Show the limit picker dialog again
                count = 0; // Reset count to zero
                tvCount.setText(String.valueOf(count)); // Update the count display
                updateLimitDisplay(); // Update the limit display
                checkLimit(); // Check the limit status after resetting
            }
        });

        // Updating the TextView with the loaded count
        tvCount.setText(String.valueOf(count));
    }

    public void showLimitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Set Limit Counter");
        EditText input = new EditText(this);
        input.setInputType(android.text.InputType.TYPE_CLASS_NUMBER); // for input of numbers only
        builder.setView(input);

        builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { // pointing to the Set button
                try {
                    limit = Integer.parseInt(input.getText().toString());
                    if (limit == 0) {
                        // If the limit is set to zero, show an error message and keep the dialog open
                        Toast.makeText(TabeehCounter.this, "Please enter a valid limit counter", Toast.LENGTH_SHORT).show();
                        showLimitDialog();
                    } else {
                        // If the limit is valid, proceed
                        tvLimit.setText("Limit: " + limit);
                    }
                } catch (NumberFormatException e) {
                    // If input is not a number, show an error message and keep the dialog open
                    Toast.makeText(TabeehCounter.this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
                    showLimitDialog();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();  // to show the dialog on screen
    }
    public void updateLimitDisplay() {
        tvLimit.setText(count + "/" + limit);

    }
    public void checkLimit () {
        if (count == limit && limit != 0) {
            vibrateDevice();
        }
    }
    // Method to vibrate the device
    public void vibrateDevice() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) { // checking vibrator object for null reference
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // checking version of the app
                vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
            }
        }
    }
}