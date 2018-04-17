package com.csc4210.royal.leagueinfo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by Royal on 4/15/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public String champName;

    public PagerAdapter(FragmentManager fm, int NumOfTabs, String champ) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.champName = champ;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag;
        Bundle bun = new Bundle();
        bun.putString("champ_name", champName);
        Log.println(Log.ERROR,"Position",Integer.toString(position));
        switch (position) {
            case 0:
                frag = new ChampFragment();
                frag.setArguments(bun);
                return frag;
            case 1:
                frag = new ChampStatsFragment();
                frag.setArguments(bun);
                return frag;
            case 2:
                frag = new AbilitiesFragment();
                frag.setArguments(bun);
                return frag;
            case 3:
                frag = new AllyFragment();
                frag.setArguments(bun);
                return frag;
            case 4:
                frag = new EnemyFragment();
                frag.setArguments(bun);
                return frag;
            default:
                return null;
        }

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
                return "Abilities";
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
