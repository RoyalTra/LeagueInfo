package com.csc4210.royal.leagueinfo.champion_fragments;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.Constraints;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.csc4210.royal.leagueinfo.utilities.ApiConnectivity;
import com.csc4210.royal.leagueinfo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.cert.Extension;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChampStatsFragment extends Fragment {

    //Initializing Variables
    View view;
    LinearLayout linear_layout;
    JSONObject champ;
    //Champname
    String champ_name;

    ApiConnectivity api = new ApiConnectivity();

    public ChampStatsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_champ_stats, container, false);
        linear_layout = (LinearLayout) view.findViewById(R.id.linear_layout);

        champ_name = getArguments().getString("champ_name");
        // Inflate the layout for this fragment
        new AsyncTaskRunner().execute(0);
        return view;
    }



    private class AsyncTaskRunner extends AsyncTask<Integer, String, ArrayList> {

        ProgressBar loading;

        @Override
        protected void onPreExecute(){
            loading = new ProgressBar(getContext());
            linear_layout.addView(loading);
            loading.setIndeterminate(true);
        }

        protected ArrayList doInBackground(Integer... params) {

            String[] data = {"role", "winRate", "kills", "assists", "playRate", "gamesPlayed", "deaths", "banRate"};

            JSONArray champions;
            JSONObject champion = null;

            try {
                champions = api.getChampStats(champ_name, "champData=winRate,kda,positions", getActivity());

                champion = champions.getJSONObject(params[0]);
            } catch (JSONException e) {

                Log.println(Log.ERROR, "json Ex", e.toString());

                e.printStackTrace();
            }

            ArrayList list = new ArrayList();

            ViewGroup.LayoutParams lParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            for (String name : data) {


                try {
                    LinearLayout horizontal = new LinearLayout(view.getContext());
                    horizontal.setOrientation(LinearLayout.HORIZONTAL);

                    if (name.equals("role")) {

                    }

                    TextView textView = new TextView(view.getContext());
                    textView.setPadding(20, 20, 20, 20);
                    textView.setLayoutParams(lParams);
                    textView.setText(name.toUpperCase() + ":\t" + Double.toString(champion.getDouble(name)).substring(0, 5
                    ) + "%");

                    ProgressBar progressBar = new ProgressBar(view.getContext(), null, android.R.attr.progressBarStyleHorizontal);
                    progressBar.setMax(1000);
                    progressBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50));
                    progressBar.setProgress((int) ((champion.getDouble(name) * 1000)));
                    progressBar.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);

                    horizontal.addView(textView);
                    horizontal.addView(progressBar);

                    list.add(horizontal);


                } catch (Exception e) {
                    Log.println(Log.ERROR, "While loop", name);

                }
            }
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList result) {
            loading.setIndeterminate(false);
            linear_layout.removeView(loading);

            if(result.get(0) == null){
                Toast toast = Toast.makeText(getContext(),"Please make sure your connected to internet.\nStats and Comparision wont work other wise.", Toast.LENGTH_LONG);
                toast.show();

            }else {
                for (Object tv : result.toArray())
                    linear_layout.addView((LinearLayout) tv);
            }
        }

    }
}