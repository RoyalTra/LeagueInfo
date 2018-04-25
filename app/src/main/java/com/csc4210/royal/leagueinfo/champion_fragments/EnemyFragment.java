package com.csc4210.royal.leagueinfo.champion_fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csc4210.royal.leagueinfo.utilities.JReader;
import com.csc4210.royal.leagueinfo.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.LineNumberReader;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnemyFragment extends Fragment {

    View view;
    String champ_name;
    LinearLayout linearLayout;
    public EnemyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ally, container, false);

        linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout);


        champ_name = getArguments().getString("champ_name");

        init();
        return view;
    }

    //Initializing Page With retrieved json files contents
    public void init(){
        JReader jreader = new JReader(getContext());
        JSONObject champ = (JSONObject) jreader.getInfoForChamp(champ_name);

        try{
            JSONArray tips = champ.getJSONArray("enemytips");
            int i = 0;
            while(tips.get(i) != null){
                TextView tv = new TextView(getContext());
                tv.setText(tips.getString(i));
                tv.setPadding(10,30,10,10);
                tv.setGravity(View.TEXT_ALIGNMENT_CENTER);

                linearLayout.addView(tv);

                i++;
            }

        }catch(Exception e){
            Log.println(Log.ERROR, "Enemy", e.toString());

        }
    }


}
