package com.csc4210.royal.leagueinfo.champion_fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.csc4210.royal.leagueinfo.utilities.JReader;
import com.csc4210.royal.leagueinfo.R;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class AbilitiesFragment extends Fragment {


    View view;
    //Declaring Vaiables
    public String champ_name;
    private static ImageView img_p;
    private static ImageView img_q;
    private static ImageView img_w;
    private static ImageView img_e;
    private static ImageView img_r;

    private static TextView txt_p;
    private static TextView txt_q;
    private static TextView txt_w;
    private static TextView txt_e;
    private static TextView txt_r;

    int test;
    int test2;
    public AbilitiesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Initializing View
        view = inflater.inflate(R.layout.fragment_abilities, container, false);

        //Initializing UI Components
        img_p = (ImageView) view.findViewById(R.id.img_passive);
        img_q = (ImageView) view.findViewById(R.id.img_q);
        img_w = (ImageView) view.findViewById(R.id.img_w);
        img_e = (ImageView) view.findViewById(R.id.img_e);
        img_r = (ImageView) view.findViewById(R.id.img_r);

        txt_p = (TextView) view.findViewById(R.id.txt_passive);
        txt_q = (TextView) view.findViewById(R.id.txt_q);
        txt_w = (TextView) view.findViewById(R.id.txt_w);
        txt_e = (TextView) view.findViewById(R.id.txt_e);
        txt_r = (TextView) view.findViewById(R.id.txt_r);

        //Initializing variable
        champ_name = getArguments().getString("champ_name");
        test = R.drawable.aatrox;

        test2 = view.getResources().getIdentifier(champ_name.toLowerCase(), "drawable", getActivity().getPackageName());

        img_p.setImageResource(view.getResources().getIdentifier(champ_name.toLowerCase(),"drawable", getActivity().getPackageName()));
        initPage();

        return view;
    }

    public void initPage(){

        ImageView[] img_array = {img_q, img_w, img_e, img_r, img_p };
        TextView[] txt_array = {txt_q, txt_w, txt_e, txt_r, txt_p};

        JReader jreader = new JReader(getActivity());
        JSONObject champ = jreader.getInfoForChamp(champ_name);


        try {
            JSONObject passive = (JSONObject) champ.get("passive");
            JSONArray abilities = champ.getJSONArray("spells");

            for(int i = 0; i < 5; i++){
                img_array[i].setPadding(4,4,4,4);
                if( i == 4){
                    JSONObject pImage = (JSONObject) passive.get("image");
                    img_array[i].setImageResource(view.getResources().getIdentifier(pImage.getString("full").toLowerCase().replace(".png",""), "drawable", getActivity().getPackageName()));
                    txt_array[i].setText(passive.getString("name") + " - " + passive.getString("description"));

                }else {
                    JSONObject ability = (JSONObject) abilities.get(i);
                    JSONObject aImage = (JSONObject) ability.get("image");
                    img_array[i].setImageResource(view.getResources().getIdentifier(aImage.getString("full").toLowerCase().replace(".png", ""), "drawable", getActivity().getPackageName()));
                    txt_array[i].setText(ability.getString("name") + " - " + ability.getString("description"));


                }
            }

        }catch(Exception e){
            Log.println(Log.ERROR, "Init-Abilities", e.toString());
        }
    }



}
