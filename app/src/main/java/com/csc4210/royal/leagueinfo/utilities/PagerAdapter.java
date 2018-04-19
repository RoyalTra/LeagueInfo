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
import com.csc4210.royal.leagueinfo.champion_fragments.EnemyFragment;

/**
 * Created by Royal on 4/15/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public String champName;

    private FragmentManager fragmentManager;
    public PagerAdapter(FragmentManager fm, int NumOfTabs, String champ) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        Log.println(Log.ERROR, "Pageadapter", Integer.toString(NumOfTabs));
        this.champName = champ;
        this.fragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag;
        Bundle bun = new Bundle();
        bun.putString("champ_name", champName);
        switch (position) {
            case 0:
                frag = new ChampFragment();
                frag.setArguments(bun);
                break;
            case 1:
                frag = new ChampStatsFragment();
                frag.setArguments(bun);
                break;
            case 2:
                frag = new AbilitiesFragment();
                frag.setArguments(bun);
                break;
            case 3:
                frag = new AllyFragment();
                frag.setArguments(bun);
                break;
            case 4:
                frag = new EnemyFragment();
                frag.setArguments(bun);
                break;
            default:
                return null;
        }

        return frag;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return "Champ";
            case 1:
                return "Stats";
            case 2:
                return "Ability";
            case 3:
                return "Ally";
            case 4:
                return "Enemy";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
