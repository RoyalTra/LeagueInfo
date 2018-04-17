package com.csc4210.royal.leagueinfo;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.InputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChampFragment extends Fragment {
    private  String champ_name;
    private static ImageView img_champ;
    private static TextView txt_champ_name;
    private static TextView txt_lore_desc;
    private static TextView txt_blurb_desc;

    public ChampFragment() {
        // Required empty public constructor

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_champ,container,false);
        //Initializing the different views
        img_champ =  (ImageView) view.findViewById(R.id.img_champ);
        txt_champ_name = (TextView) view.findViewById(R.id.txt_champ_name);
        txt_lore_desc = (TextView) view.findViewById(R.id.txt_lore_desc);
        txt_blurb_desc = (TextView) view.findViewById(R.id.txt_blurb_desc);


        champ_name = getArguments().getString("champ_name");

        //Initialize page
        init();

        // Inflate the layout for this fragment
        return view;
    }


    public void init(){
        JReader jread = new JReader(getActivity());
        JSONObject champ = jread.getInfoForChamp(champ_name);
        img_champ.setImageResource(getActivity().getResources().getIdentifier(champ_name.toLowerCase(),"drawable",getActivity().getPackageName()));
        txt_champ_name.setText(champ_name);
        try {
            txt_lore_desc.setText(champ.getString("lore").replaceAll("<br>" ,"\n"));
            txt_blurb_desc.setText(champ.getString("blurb").replaceAll("<br>", "\n"));
            Log.println(Log.ERROR, "Champ Name", champ.getString("lore"));

        }catch(Exception e){
            Log.println(Log.ERROR, "Init Page:", e.toString());
        }

    }



}
