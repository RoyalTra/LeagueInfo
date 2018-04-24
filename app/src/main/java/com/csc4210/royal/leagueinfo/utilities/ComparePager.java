package com.csc4210.royal.leagueinfo.utilities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.csc4210.royal.leagueinfo.champion_fragments.AbilitiesFragment;
import com.csc4210.royal.leagueinfo.champion_fragments.AllyFragment;
import com.csc4210.royal.leagueinfo.champion_fragments.ChampFragment;
import com.csc4210.royal.leagueinfo.champion_fragments.ChampStatsFragment;
import com.csc4210.royal.leagueinfo.champion_fragments.CompareChampionFragment;
import com.csc4210.royal.leagueinfo.champion_fragments.ComparedChampionsFragment;
import com.csc4210.royal.leagueinfo.champion_fragments.EnemyFragment;
import com.csc4210.royal.leagueinfo.champion_fragments.SearchForChampFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Royal on 4/21/2018.
 */

public class ComparePager extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    public ArrayList champs;

    private FragmentManager fragmentManager;

    public ComparePager(FragmentManager fm, int NumOfTabs, ArrayList champs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        Log.println(Log.ERROR, "Compare Adapter", Integer.toString(NumOfTabs));
        this.champs = champs;
        this.fragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag;
        Bundle bun = new Bundle();
        String[] data = { "winrate", "role", "wins", "totalDamageDealtToChampions", "deaths", "kills",};

        JSONObject champ = (JSONObject) champs.get(position);

        try {

            bun.putInt("champ_one_id", champ.getInt("champ1_id") );
            Log.println(Log.ERROR, "Champ one id",bun.getInt("champ2_id") + "");
            bun.putInt("champ_two_id", champ.getInt("champ2_id") );
            Log.println(Log.ERROR, "Champ two id",bun.getInt("champ2_id") + "" );

            JSONObject champ1 = (JSONObject) champ.getJSONObject("champ1");
            JSONObject champ2 = (JSONObject) champ.getJSONObject("champ2");

            for(String str :data) {
                Log.println(Log.ERROR,str + "1", champ1.getString(str));
                Log.println(Log.ERROR,str + "2", champ2.getString(str));
                bun.putString(str + "1", champ1.getString(str));
                bun.putString(str + "2", champ2.getString(str));
            }

        }catch(Exception e){
            Log.println(Log.ERROR, "CompareAdapter:", e.toString());
        }



        switch(position){
            case 0:  return frag = ComparedChampionsFragment.newInstance(bun);
            case 1: return frag = ComparedChampionsFragment.newInstance(bun);
            case 2: return frag = ComparedChampionsFragment.newInstance(bun);
            case 3: return frag = ComparedChampionsFragment.newInstance(bun);
            case 4:  return frag = ComparedChampionsFragment.newInstance(bun);
            case 5: return frag =ComparedChampionsFragment.newInstance(bun);
            case 6: return frag = ComparedChampionsFragment.newInstance(bun);
            case 7: return frag =ComparedChampionsFragment.newInstance(bun);
            case 8:  return frag =ComparedChampionsFragment.newInstance(bun);
            case 9: return frag = ComparedChampionsFragment.newInstance(bun);

            default: return null;
        }

    }

    @Override
    public CharSequence getPageTitle(int position)  {
        // Generate title based on item position
        JSONObject champ = (JSONObject) champs.get(position);
        String str;
        try {
             str = champ.getString("champ1_id");
        }catch(Exception e){
            return null;
        }
        return position + "";
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
