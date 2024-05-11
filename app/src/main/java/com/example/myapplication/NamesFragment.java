package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NamesFragment extends Fragment {
    public static final String[][] data = {
            {"الرَّحْمَنُ", "AR-RAHMAAN", "The Beneficent"},
            {"الرَّحِيمُ", "AR-RAHEEM", "The Merciful"},
            {"الْمَلِكُ", "AL-MALIK", "The King"},
            {"الْقُدُّوسُ", "AL-QUDDUS", "The Most Sacred"},
            {"السَّلاَمُ", "AS-SALAM", "The Source of Peace, The Flawless"},
            {"الْمُؤْمِنُ", "AL-MU’MIN", "The Infuser of Faith"},
            {"الْمُهَيْمِنُ", "AL-MUHAYMIN", "The Preserver of Safety"},
            {"الْعَزِيزُ", "AL-AZIZ", "All Mighty"},
            {"الْجَبَّارُ", "AL-JABBAR", "The Compeller, The Restorer"},
            {"الْمُتَكَبِّر", "AL-MUTAKABBIR", "The Supreme, The Majestic"},
            {"الْخَالِقُ", "AL-KHAALIQ", "The Creator, The Maker"},
            {"الْبَارِئُ", "AL-BAARI", "The Evolver"},
            {"الْمُصَوِّرُ", "AL-MUSAWWIR", "The Fashioner"},
            {"الْغَفَّارُ", "AL-GHAFFAR", "The Constant Forgiver"},
            {"الْقَهَّارُ", "AL-QAHHAR", "The All-Prevailing One"},
            {"الْوَهَّابُ", "AL-WAHHAAB", "The Supreme Bestower"},
            {"الرَّزَّاقُ", "AR-RAZZAAQ", "The Provider"},
            {"الْفَتَّاحُ", "AL-FATTAAH", "The Supreme Solver"},
            {"اَلْعَلِيْمُ", "AL-‘ALEEM", "The All-Knowing"},
            {"الْقَابِضُ", "AL-QAABID", "The Withholder"},
            {"الْبَاسِطُ", "AL-BAASIT", "The Extender"},
            {"الْخَافِضُ", "AL-KHAAFIDH", "The Reducer"},
            {"الرَّافِعُ", "AR-RAAFI’", "The Exalter, The Elevator"},
            {"الْمُعِزُّ", "AL-MU’IZZ", "The Honourer, The Bestower"},
            {"ٱلْمُذِلُّ", "AL-MUZIL", "The Dishonourer"},
            {"السَّمِيعُ", "AS-SAMEE’", "The All-Hearing"},
            {"الْبَصِيرُ", "AL-BASEER", "The All-Seeing"},
            {"الْحَكَمُ", "AL-HAKAM", "The Impartial Judge"},
            {"الْعَدْلُ", "AL-‘ADL", "The Utterly Just"},
            {"اللَّطِيفُ", "AL-LATEEF", "The Subtle One, The Most Gentle"},
            {"الْخَبِيرُ", "AL-KHABEER", "The All-Aware"},
            {"الْحَلِيمُ", "AL-HALEEM", "The Most Forbearing"},
            {"الْعَظِيمُ", "AL-‘AZEEM", "The Magnificent"},
            {"الْغَفُور", "AL-GHAFOOR", "The Great Forgiver"},
            {"الشَّكُورُ", "ASH-SHAKOOR", "The Most Appreciative"},
            {"الْعَلِيُّ", "AL-‘ALEE", "The Most High"},
            {"الْكَبِيرُ", "AL-KABEER", "The Most Great"},
            {"الْحَفِيظُ", "AL-HAFEEDH", "The Preserver"},
            {"المُقيِت", "AL-MUQEET", "The Sustainer"},
            {"اﻟْﺣَسِيبُ", "AL-HASEEB", "The Reckoner"},
            {"الْجَلِيلُ", "AL-JALEEL", "The Majestic"},
            {"الْكَرِيمُ", "AL-KAREEM", "The Most Generous"},
            {"الرَّقِيبُ", "AR-RAQEEB", "The Watchful"},
            {"ٱلْمُجِيبُ", "AL-MUJEEB", "The Responsive One"},
            {"الْوَاسِعُ", "AL-WAASI’", "The All-Encompassing"},
            {"الْحَكِيمُ", "AL-HAKEEM", "The All-Wise"},
            {"الْوَدُودُ", "AL-WADUD", "The Most Loving"},
            {"الْمَجِيدُ", "AL-MAJEED", "The Glorious"},
            {"الْبَاعِثُ", "AL-BA’ITH", "The Infuser of New Life"},
            {"الشَّهِيدُ", "ASH-SHAHEED", "The All Observing Witnessing"},
            {"الْحَقُ", "AL-HAQQ", "The Absolute Truth"},
            {"الْوَكِيلُ", "AL-WAKEEL", "The Trustee"},
            {"الْقَوِيُ", "AL-QAWIYY", "The All-Strong"},
            {"الْمَتِينُ", "AL-MATEEN", "The Firm"},
            {"الْوَلِيُّ", "AL-WALIYY", "The Protecting Associate"},
            {"الْحَمِيدُ", "AL-HAMEED", "The Praiseworthy"},
            {"الْمُحْصِي", "AL-MUHSEE", "The All-Enumerating"},
            {"الْمُبْدِئُ", "AL-MUBDI", "The Originator"},
            {"ٱلْمُعِيدُ", "AL-MUEED", "The Restorer"},
            {"الْمُحْيِي", "AL-MUHYI", "The Giver of Life"},
            {"اَلْمُمِيتُ", "AL-MUMEET", "The Creator of Death"},
            {"الْحَيُّ", "AL-HAYY", "The Ever-Living"},
            {"الْقَيُّومُ", "AL-QAYYOOM", "The Sustainer"},
            {"الْوَاجِدُ", "AL-WAAJID", "The Perceiver"},
            {"الْمَاجِدُ", "AL-MAAJID", "The Illustrious"},
            {"الْواحِدُ", "AL-WAAHID", "The One"},
            {"اَلاَحَدُ", "AL-AHAD", "The Unique"},
            {"الصَّمَدُ", "AS-SAMAD", "The Eternal"},
            {"الْقَادِرُ", "AL-QADEER", "The Omnipotent One"},
            {"الْمُقْتَدِرُ", "AL-MUQTADIR", "The Powerful"},
            {"الْمُقَدِّمُ", "AL-MUQADDIM", "The Expediter"},
            {"الْمُؤَخِّرُ", "AL-MU’AKHKHIR", "The Delayer"},
            {"الأوَّلُ", "AL-AWWAL", "The First"},
            {"الآخِرُ", "AL-AAKHIR", "The Last"},
            {"الظَّاهِرُ", "AZ-ZAAHIR", "The Manifest"},
            {"الْبَاطِنُ", "AL-BAATIN", "The Hidden One"},
            {"الْوَالِي", "AL-WAALI", "The Patron"},
            {"الْمُتَعَالِي", "AL-MUTA’ALI", "The Self Exalted"},
            {"الْبَرُّ", "AL-BARR", "The Source of All Goodness"},
            {"التَّوَابُ", "AT-TAWWAB", "The Ever-Pardoning"},
            {"الْمُنْتَقِمُ", "AL-MUNTAQIM", "The Avenger"},
            {"العَفُوُّ", "AL-‘AFUWW", "The Pardoner"},
            {"الرَّؤُوفُ", "AR-RA’OOF", "The Most Kind"},
            {"مَالِكُ ٱلْمُلْكُ", "MAALIK-UL-MULK", "Master of the Kingdom"},
            {"ذُوالْجَلاَلِ وَالإكْرَامِ", "DHUL-JALAALI WAL-IKRAAM", "Lord of Glory and Honour"},
            {"الْمُقْسِطُ", "AL-MUQSIT", "The Just One"},
            {"الْجَامِعُ", "AL-JAAMI’", "The Gatherer"},
            {"ٱلْغَنيُّ", "AL-GHANIYY", "The Self-Sufficient"},
            {"ٱلْمُغْنِيُّ", "AL-MUGHNI", "The Enricher"},
            {"اَلْمَانِعُ", "AL-MANI’", "The Withholder"},
            {"الضَّارَ", "AD-DHARR", "The Distresser"},
            {"النَّافِعُ", "AN-NAFI’", "The Benefactor"},
            {"النُّورُ", "AN-NUR", "The Light"},
            {"الْهَادِي", "AL-HAADI", "The Guide"},
            {"الْبَدِيعُ", "AL-BADEE’", "The Incomparable Originator"},
            {"اَلْبَاقِي", "AL-BAAQI", "The Everlasting"},
            {"الْوَارِثُ", "AL-WAARITH", "The Inheritor"},
            {"الرَّشِيدُ", "AR-RASHEED", "The Guide, Infallible Teacher"},
            {"الصَّبُورُ", "AS-SABOOR", "The Forbearing"}
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_names, container, false);

        // Initializing RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        // setting linear layout for recycler view

        NameAdapter adapter = new NameAdapter(data);
        recyclerView.setAdapter(adapter);

        return view;
    }
}

// Custom Adapter for RecyclerView
class NameAdapter extends RecyclerView.Adapter<NameAdapter.NameViewHolder> {

    public final String[][] data;  // Array to hold the data

    // Constructor to initialize the adapter
    public NameAdapter(String[][] data) {
        this.data = data;
    }

    @Override
    public NameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flashcard_view, parent, false);
        // inflating view in order to display the meaning
        return new NameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NameViewHolder holder, int position) {
        holder.bind(data[position], position);
        // assigning values from data array to views when scrolled
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    static class NameViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumber, tvArabicName, tvEnglishName, tvMeaning;
        public NameViewHolder(View itemView) {
            super(itemView);
            // Initializing the views
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvArabicName = itemView.findViewById(R.id.tvArabicName);
            tvEnglishName = itemView.findViewById(R.id.tvEnglishName);
            tvMeaning = itemView.findViewById(R.id.tvMeaning);

            itemView.setOnClickListener(new View.OnClickListener() {
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
        }

        public void bind(String[] nameData, int position) {
            tvNumber.setText(String.valueOf(position + 1));
            tvArabicName.setText(nameData[0]);
            tvEnglishName.setText(nameData[1]);
            tvMeaning.setText(nameData[2]);

            // Initially, set the meaning text view to invisible
            tvMeaning.setVisibility(View.GONE);
        }
    }
}