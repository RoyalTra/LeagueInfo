package com.csc4210.royal.leagueinfo.champion_fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.csc4210.royal.leagueinfo.ChampionProfileActivity;
import com.csc4210.royal.leagueinfo.ChampionsActivity;
import com.csc4210.royal.leagueinfo.MainActivity;
import com.csc4210.royal.leagueinfo.R;
import com.csc4210.royal.leagueinfo.utilities.Champions_Enum;
import com.csc4210.royal.leagueinfo.utilities.ImageAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChampionListFragment extends Fragment {


    GridView grid;
    ImageView[] img_array = new ImageView[Champions_Enum.getSize()];
    View view;
    public ChampionListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_champion_list, container, false);

        grid = (GridView) view.findViewById(R.id.gridView);
        ImageAdapter imgadapter = new ImageAdapter(getActivity());
        grid.setAdapter(imgadapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Toast.makeText(MainActivity.this , "" + position,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), ChampionProfileActivity.class);

                //Using bundle to pass variable to the next activity
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
