package com.csc4210.royal.leagueinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;

import com.csc4210.royal.leagueinfo.utilities.Champions_Enum;

public class CompareChampionsActivity extends AppCompatActivity {

    FrameLayout frame_1;
    FrameLayout frame_2;

    AutoCompleteTextView actv_1;
    AutoCompleteTextView actv_2;

    String[] champions = new String[Champions_Enum.getSize()];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_champions);

        frame_1 = (FrameLayout) findViewById(R.id.frame_layout_1);
        frame_2 = (FrameLayout) findViewById(R.id.frame_layout_2);

        actv_1 = (AutoCompleteTextView) findViewById(R.id.auto_champ_1);
        actv_2 = (AutoCompleteTextView) findViewById(R.id.auto_champ_2);

        int count = 0;
        for(Champions_Enum.Champ champ : Champions_Enum.Champ.values()){
            champions[count++] = champ.name();
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, champions );

        actv_1.setThreshold(1);
        actv_2.setThreshold(1);

        actv_1.setAdapter(adapter1);
        actv_2.setAdapter(adapter1);

    }
}
