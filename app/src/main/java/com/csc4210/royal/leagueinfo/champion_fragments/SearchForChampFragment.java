package com.csc4210.royal.leagueinfo.champion_fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;

import com.csc4210.royal.leagueinfo.CompareChampionsActivity;
import com.csc4210.royal.leagueinfo.R;
import com.csc4210.royal.leagueinfo.utilities.Champions_Enum;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchForChampFragment extends Fragment {


    AutoCompleteTextView actv;
    Button btn_champ;

    String[] champions = new String[Champions_Enum.getSize()];
    FrameLayout frame;

    FragmentManager fragMang;

    public SearchForChampFragment( ) {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_for_champ, container, false);
        actv = (AutoCompleteTextView) view.findViewById(R.id.auto_champ);
        btn_champ = (Button) view.findViewById(R.id.btn_compare);

        int count = 0;

        for(Champions_Enum.Champ champ : Champions_Enum.Champ.values()){
            champions[count++] = champ.name();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, champions );
        actv.setThreshold(1);
        actv.setAdapter(adapter);
        final CompareChampionFragment champ = new CompareChampionFragment();


        btn_champ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("champ_name", actv.getText().toString());
                champ.setArguments(bundle);
                CompareChampionsActivity.fragmentManager.beginTransaction().replace(getArguments().getInt("frame"), champ ).commit();
            }

        });
        // Inflate the layout for this fragment
        return view;
    }


    public class RunTaskAsync extends AsyncTask<FrameLayout, Void, FrameLayout> {
        //Process Thats done in the back ground
        @Override
        protected FrameLayout doInBackground(FrameLayout... params){


            return params[0];
        }

        @Override
        protected void onPostExecute(FrameLayout result){


            getChildFragmentManager().beginTransaction().replace( result.getId(), new CompareChampionFragment()).commit();

        }

    }

}
