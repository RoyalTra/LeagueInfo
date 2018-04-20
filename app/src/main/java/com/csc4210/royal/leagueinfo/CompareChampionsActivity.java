package com.csc4210.royal.leagueinfo;

import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;

import com.csc4210.royal.leagueinfo.champion_fragments.CompareChampionFragment;
import com.csc4210.royal.leagueinfo.champion_fragments.SearchForChampFragment;
import com.csc4210.royal.leagueinfo.utilities.Champions_Enum;

public class CompareChampionsActivity extends AppCompatActivity {

    FrameLayout frame_1;
    FrameLayout frame_2;

    SearchForChampFragment search_one;
    public static FragmentManager fragmentManager;
    public static FragmentManager fragmentManager_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_champions);

        frame_1 = (FrameLayout) findViewById(R.id.frame_layout_1);
        frame_2 = (FrameLayout) findViewById(R.id.frame_layout_2);


        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        SearchForChampFragment search_one = new SearchForChampFragment();
        SearchForChampFragment search_two = new SearchForChampFragment();

        Bundle bundle_one = new Bundle();
        bundle_one.putInt("frame", frame_1.getId());
        search_one.setArguments(bundle_one);

        Bundle bundle_two = new Bundle();
        bundle_two.putInt("frame", frame_2.getId());
        search_two.setArguments(bundle_two);

        fragmentManager.beginTransaction().add(frame_1.getId(), search_one, null).commit();
        fragmentManager.beginTransaction().add(frame_2.getId(), search_two, null).commit();

    }







}
