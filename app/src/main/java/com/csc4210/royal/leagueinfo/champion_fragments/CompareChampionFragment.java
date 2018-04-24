package com.csc4210.royal.leagueinfo.champion_fragments;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.csc4210.royal.leagueinfo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompareChampionFragment extends Fragment {

    View view;
    String champ_name;
    LinearLayout linearLayout;

    String[] data = {"winrate", "role", "wins", "totalDamageDealtToChampions"};
    String[] dataValue = new String[4];

    public CompareChampionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_compare_champion, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout);

//        champ_name = getArguments().getString("champ_name");
//
//        new AsyncTaskRunner().execute();

        return view;
    }

    private class AsyncTaskRunner extends AsyncTask<Integer, String, ArrayList> {


        //Process Thats done in the back ground
        @Override
        protected ArrayList doInBackground(Integer... params){

            ArrayList list = new ArrayList();

            ViewGroup.LayoutParams lParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            for(int i = 0;i < 4; i++){
                dataValue[i] = getArguments().getString(data[i] + "1");

            }

            for(int i = 0; i < 4; i++){


                try {
                    LinearLayout horizontal = new LinearLayout(view.getContext());
                    horizontal.setOrientation(LinearLayout.HORIZONTAL);

                    TextView textView = new TextView(view.getContext());
                    textView.setPadding(20,20,20,20);
                    textView.setLayoutParams(lParams );
                    textView.setText(data[i].toUpperCase() + ":\t" + dataValue[i] );

                    horizontal.addView(textView);

                    list.add(horizontal);

                }catch(Exception e){
                    Log.println(Log.ERROR, "While loop", e.toString());
                }
            }

            return list;

        }

        @Override
        protected void onPostExecute(ArrayList result){

            for(Object tv:  result.toArray())
                linearLayout.addView((LinearLayout)tv);
        }

    }

}
