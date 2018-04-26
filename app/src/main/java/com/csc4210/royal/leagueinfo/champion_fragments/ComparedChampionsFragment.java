package com.csc4210.royal.leagueinfo.champion_fragments;


import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.csc4210.royal.leagueinfo.R;
import com.csc4210.royal.leagueinfo.utilities.Champions_Enum;
import com.csc4210.royal.leagueinfo.utilities.ComparePager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComparedChampionsFragment extends Fragment {

    private ImageView img_champ_one;
    private ImageView img_champ_two;
    private LinearLayout linearLayout;
    String[] data = { "winrate", "wins", "deaths", "kills",};

    // , "totalDamageDealtToChampions"
    View view;

    public ComparedChampionsFragment() {
        // Required empty public constructor
    }

    public static ComparedChampionsFragment newInstance(Bundle bundle){
        ComparedChampionsFragment compared = new ComparedChampionsFragment();

        compared.setArguments(bundle);
        Log.println(Log.ERROR, "Compared Fragment", "Hitting");
        return compared;

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_compared_champions, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout);
        img_champ_one = (ImageView) view.findViewById(R.id.img_champ_1);
        img_champ_two = (ImageView) view.findViewById(R.id.img_champ_2);



      new AsyncTaskRunner().execute();

        return view;
    }

    public class AsyncTaskRunner extends AsyncTask<String, String, ArrayList> {
        ProgressBar loading;

        @Override
        protected void onPreExecute(){
            loading = new ProgressBar(getContext());
            linearLayout.addView(loading);
            loading.setIndeterminate(true);
        }

        //Process Thats done in the back ground
        @Override
        protected ArrayList doInBackground(String... params){


            ArrayList list = new ArrayList();

            ViewGroup.LayoutParams lParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            //Create the text view

            LinearLayout horizontalRole = new LinearLayout(view.getContext());
            horizontalRole.setOrientation(LinearLayout.HORIZONTAL);
            horizontalRole.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            horizontalRole.setHorizontalGravity(View.TEXT_ALIGNMENT_CENTER);
            horizontalRole.setHorizontalGravity(View.TEXT_ALIGNMENT_GRAVITY);

            TextView role_1 = new TextView(view.getContext());
            role_1.setPadding(20, 20, 20, 20);
            role_1.setLayoutParams(lParams);
            role_1.setText(getArguments().getString("role1"));
            role_1.setGravity(View.TEXT_ALIGNMENT_CENTER);


            horizontalRole.addView(role_1);

            list.add(horizontalRole);


            for(String str : data){

                LinearLayout horizontal = new LinearLayout(view.getContext());
                horizontal.setOrientation(LinearLayout.HORIZONTAL);
                horizontal.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                horizontal.setHorizontalGravity(View.TEXT_ALIGNMENT_CENTER);

                TextView tv_head = new TextView(view.getContext());
                tv_head.setPadding(20, 20, 20, 20);
                tv_head.setLayoutParams(lParams);
                tv_head.setText(str.toUpperCase());
                tv_head.setGravity(View.TEXT_ALIGNMENT_CENTER);

                TextView tv_champ_1 = new TextView(view.getContext());
                tv_champ_1.setLayoutParams(lParams);
                tv_champ_1.setText(getArguments().getString(str + "1"));

                TextView tv_champ_2 = new TextView(view.getContext());
                tv_champ_2.setLayoutParams(lParams);
                tv_champ_2.setText(getArguments().getString(str + "2"));


                ProgressBar progressBar1 = new ProgressBar(view.getContext(), null, android.R.attr.progressBarStyleHorizontal);
                progressBar1.setRotation(180);
                progressBar1.setMax(1000);
                progressBar1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 50));
                progressBar1.setProgress(  (int) (Double.parseDouble( getArguments().getString(str + "1")) * 1000) );
                progressBar1.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);

                ProgressBar progressBar2 = new ProgressBar(view.getContext(), null, android.R.attr.progressBarStyleHorizontal);
                progressBar2.setMax(1000);
                progressBar2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 50));
                progressBar2.setProgress( (int) (Double.parseDouble( getArguments().getString(str + "2")) * 1000));
                progressBar2.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);

                horizontal.addView(tv_champ_1);
                horizontal.addView(progressBar1);
                horizontal.addView(progressBar2);
                horizontal.addView(tv_champ_2);


                list.add(tv_head);
                list.add(horizontal);


            }

            return list;

        }

        @Override
        protected void onPostExecute(ArrayList result){
            loading.setIndeterminate(false);
            linearLayout.removeView(loading);

            String champ_one = Champions_Enum.getNameById(getArguments().getInt("champ_one_id"));
            img_champ_one.setImageResource(getActivity().getResources().getIdentifier(champ_one.toLowerCase(),"drawable",getActivity().getPackageName()));
            Log.println(Log.ERROR,"Champ_one_id", champ_one);

            String champ_two = Champions_Enum.getNameById(getArguments().getInt("champ_two_id"));
            Log.println(Log.ERROR,"Champ_two_id", champ_two);

            img_champ_two.setImageResource(getActivity().getResources().getIdentifier(champ_two.toLowerCase(),"drawable",getActivity().getPackageName()));


            int count = 1;
            for (Object tv : result.toArray()) {
                Log.println(Log.ERROR,"Adding Views", "hitting");
                if (count % 2 == 0)
                    linearLayout.addView((TextView) tv);

                else {

                    LinearLayout linTV = (LinearLayout) tv;
                    if(linTV.getParent() != null)
                        ((ViewGroup)linTV.getParent()).removeView(linTV);

                    linearLayout.addView((LinearLayout) tv);

                }
                count++;
            }
        }

    }

}
