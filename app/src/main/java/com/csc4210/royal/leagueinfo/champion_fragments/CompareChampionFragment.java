package com.csc4210.royal.leagueinfo.champion_fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csc4210.royal.leagueinfo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompareChampionFragment extends Fragment {

    View view;
    public CompareChampionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_compare_champion, container, false);
        return view;
    }

}
