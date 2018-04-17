package com.csc4210.royal.leagueinfo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnemyFragment extends Fragment {

    View view;
    String champ_name;
    TextView txt_tip1;
    TextView txt_tip2;
    TextView txt_tip3;

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


        //Initialzing variables
        txt_tip1 = (TextView) view.findViewById(R.id.txt_tip1);
        txt_tip2 = (TextView) view.findViewById(R.id.txt_tip2);
        txt_tip3 = (TextView) view.findViewById(R.id.txt_tip3);

        champ_name = getArguments().getString("champ_name");

        init();
        return view;
    }

    //Initializing Page With retrieved json files contents
    public void init(){
        TextView txt[] = {txt_tip1, txt_tip2, txt_tip3};
        JReader jreader = new JReader(getContext());
        JSONObject champ = (JSONObject) jreader.getInfoForChamp(champ_name);

        try{
            JSONArray tips = champ.getJSONArray("enemytips");

            for(int i = 0; i < 3 ; i++ ){
                txt[i].setText(tips.getString(i));
            }

        }catch(Exception e){

        }
    }


}
