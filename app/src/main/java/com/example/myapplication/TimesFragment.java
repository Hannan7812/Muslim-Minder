package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.databinding.FragmentTimesBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TimesFragment extends Fragment {

    private FragmentTimesBinding binding;
    private PrayerTimesManager namazAdapter;
    TextView scity;
    private boolean alarmsEnabled = true;
    private boolean notificationsEnabled = true;
    private String calc_method;
    ImageButton imageButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTimesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize RecyclerView and adapter
        namazAdapter = new PrayerTimesManager(requireContext());
        binding.namazRecycler.setAdapter(namazAdapter);
        binding.namazRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        String city = loadUserPreferences();
        loadData(city);


    }

    private String loadUserPreferences() {
        SharedPreferences prefs = requireActivity().getSharedPreferences("PrayerPrefs", Context.MODE_PRIVATE);
        SharedPreferences sp = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        alarmsEnabled = prefs.getBoolean(SettingsFragment.ALARM_ENABLED_KEY, true);
        notificationsEnabled = prefs.getBoolean(SettingsFragment.NOTIFICATION_ENABLED_KEY, true);

        return sp.getString("cityName", "Mecca");
    }

    private void loadData(String city) {
        String url = "https://muslimsalat.com/"+city+".json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    namazAdapter.clear();
                    String status = response.getString("status_description");
                    calc_method = response.getString("prayer_method_name");
                    if (status.equals("Success.")) {
                        JSONArray itemsArray = response.getJSONArray("items");
                        for (int i = 0; i < itemsArray.length(); i++) {
                            JSONObject itemObject = itemsArray.getJSONObject(i);

                            String fajr = itemObject.getString("fajr");
                            String zuhar = itemObject.getString("dhuhr");
                            String asar = itemObject.getString("asr");
                            String maghrib = itemObject.getString("maghrib");
                            String isha = itemObject.getString("isha");
                            scity = binding.textViewSelectedCity;
                            scity.setText("City: " + city);
                            binding.textView13.setText("Calculation Method: " + calc_method);
                            String date = formatDate(itemObject.getString("date_for"));

                            PrayerTimes namazModel = new PrayerTimes(fajr, zuhar, asar, maghrib, isha, date);
                            namazAdapter.add(namazModel);

                            // Set alarms for each prayer time
                            setAlarmsForPrayerTimes(itemObject);
                        }
                    } else {
                        scity = binding.textViewSelectedCity;
                        scity.setText("City: " + city);
                        binding.textView13.setText("Error fetching data. Please make sure that you have spelled the city name correctly.");
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //Toast.makeText(requireContext(), "Volley Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(requireContext(), "Error. Please check your connection and try again.", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        requestQueue.add(jsonObjectRequest);
    }

    private PrayerTimes parseNamazModel(JSONObject itemObject) throws JSONException {
        String fajr = itemObject.getString("fajr");
        String zuhar = itemObject.getString("dhuhr");
        String asar = itemObject.getString("asr");
        String maghrib = itemObject.getString("maghrib");
        String isha = itemObject.getString("isha");
        String date = itemObject.getString("date_for");
        // Parse other fields as needed
        return new PrayerTimes(fajr, zuhar, asar, maghrib, isha, date);
    }

    // Method to format date to "May 4th, 2024"
    private String formatDate(String inputDate) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = inputFormat.parse(inputDate);
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM d', 'yyyy", Locale.getDefault());
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void setAlarmsForPrayerTimes(JSONObject prayerObject) {
        try {
            Calendar calendar = Calendar.getInstance();

            // Parse prayer times
            String fajrTime = prayerObject.getString("fajr");
            String zuharTime = prayerObject.getString("dhuhr");
            String asarTime = prayerObject.getString("asr");
            String maghribTime = prayerObject.getString("maghrib");
            String ishaTime = prayerObject.getString("isha");

            // Format prayer times to 24-hour format
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
            Date fajrDate = sdf.parse(fajrTime);
            Date zuharDate = sdf.parse(zuharTime);
            Date asarDate = sdf.parse(asarTime);
            Date maghribDate = sdf.parse(maghribTime);
            Date ishaDate = sdf.parse(ishaTime);

            // Set alarms for each prayer time
//            setAlarm(this, fajrDate.getTime());
//            setAlarm(this, zuharDate.getTime());
//            setAlarm(this, asarDate.getTime());
//            setAlarm(this, maghribDate.getTime());
//            setAlarm(this, ishaDate.getTime());

        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
    }


}

class PrayerTimesManager extends RecyclerView.Adapter<PrayerTimesManager.MyViewHolder>{
    private Context context;
    private List<PrayerTimes> namazModelList;

    public PrayerTimesManager(Context context) {
        this.context = context;
        this.namazModelList = new ArrayList<>();
    }

    public void add(PrayerTimes namazModel){
        namazModelList.add(namazModel);
        notifyDataSetChanged();
    }
    public void clear(){
        namazModelList.clear();
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.day_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PrayerTimes namazModel=namazModelList.get(position);
        holder.bindViews();
    }

    @Override
    public int getItemCount() {
        return namazModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView fajr,zuhar,asar,maghrib,isha,date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fajr=itemView.findViewById(R.id.textViewfajr);
            zuhar=itemView.findViewById(R.id.textViewzuhar);
            asar=itemView.findViewById(R.id.textViewasar);
            maghrib=itemView.findViewById(R.id.textViewmaghrib);
            isha=itemView.findViewById(R.id.textViewisha);
            date= itemView.findViewById(R.id.textViewdate);
        }

        public void bindViews() {
            fajr.setText(PrayerTimes.getFajar());
            zuhar.setText(PrayerTimes.getZuhar());
            asar.setText(PrayerTimes.getAsar());
            maghrib.setText(PrayerTimes.getMaghrib());
            isha.setText(PrayerTimes.getIsha());
            date.setText(PrayerTimes.getDate());
        }
    }
}
class PrayerTimes {
    private static String fajar;
    private static String zuhar;
    private static String asar;
    private static String maghrib;
    private static String isha;
    private static String date;
    private static String hijriDate;

    public PrayerTimes(String fajar, String zuhar, String asar, String maghrib, String isha, String date) {
        this.fajar = fajar;
        this.zuhar = zuhar;
        this.asar = asar;
        this.maghrib = maghrib;
        this.isha = isha;
        this.date = date;
        this.hijriDate = hijriDate;
    }

    public void setFajar(String fajar) {
        this.fajar = fajar;
    }

    public void setZuhar(String zuhar) {
        this.zuhar = zuhar;
    }

    public void setAsar(String asar) {
        this.asar = asar;
    }

    public void setMaghrib(String maghrib) {
        this.maghrib = maghrib;
    }

    public void setIsha(String isha) {
        this.isha = isha;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHijriDate(String hijriDate) {
        this.hijriDate = hijriDate;
    }

    public static String getFajar() {
        return fajar;
    }

    public static String getZuhar() {
        return zuhar;
    }

    public static String getAsar() {
        return asar;
    }

    public static String getMaghrib() {
        return maghrib;
    }

    public static String getIsha() {
        return isha;
    }

    public static String getDate() {
        return date;
    }

    public String getHijriDate() {
        return hijriDate;
    }
}
