package com.csc4210.royal.leagueinfo;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.csc4210.royal.leagueinfo.utilities.Champions_Enum;
import com.csc4210.royal.leagueinfo.utilities.PagerAdapter;

public class ChampionProfileActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    int position;
    String champName;
    int champId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_profile);

        //Getting variables from the last activity
        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();

        //Initializing Position
        position = bundle.getInt("position");
        champName = Champions_Enum.Champ.values()[position].name();


        //Initializing Frame and Tab layout
        viewPager = (ViewPager) findViewById(R.id.viewPage);
        tabLayout = (TabLayout) findViewById(R.id.tab_champ);

        //Creating each tabs, setting the text and then adding the tab to tabLayout
        TabLayout.Tab champ = tabLayout.newTab();
        tabLayout.addTab(champ);

        TabLayout.Tab stats = tabLayout.newTab();
        tabLayout.addTab(stats);

        TabLayout.Tab ability = tabLayout.newTab();
        tabLayout.addTab(ability);

        TabLayout.Tab ally = tabLayout.newTab();
        tabLayout.addTab(ally);

        TabLayout.Tab enemy = tabLayout.newTab();
        tabLayout.addTab(enemy);

        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), champName);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }


}
