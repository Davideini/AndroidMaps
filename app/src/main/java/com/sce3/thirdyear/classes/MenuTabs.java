package com.sce3.thirdyear.classes;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.widget.TabHost;

import com.sce3.thirdyear.androidmaps.R;
import com.sce3.thirdyear.androidmaps.fragments.ResultFragment;
import com.sce3.thirdyear.androidmaps.fragments.frag;
import com.sce3.thirdyear.androidmaps.fragments.test;

/**
 * Created by win7 on 01/06/2015.
 */
public class MenuTabs {
    private TabHost tabHost;
    private FragmentManager fm;
    private Context context;
    final static String [] names ={"Liked","Unliked","ALL"};
    final static String [] searchTabs={"By Address","Custom Search"};
    Fragment fragment = null;
    public MenuTabs(TabHost tabhost,FragmentManager fm)
    {
        this.tabHost=tabhost;
        this.fm=fm;
    }

    public void createHistoryTabs() {
        for (int i = 0; i < names.length; i++) {
            String tabName = names[i];
            TabHost.TabSpec spec = tabHost.newTabSpec(tabName);
            spec.setContent(R.id.fakeTabContent);
            spec.setIndicator(tabName);
            tabHost.addTab(spec);
        }
//the first fragment
        fm.beginTransaction().replace(R.id.content_frame, new frag()).commit();
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {
              /*  Fragment fragment = null;
                if (tabHost.getCurrentTabTag().equals(names[0]))
                    fragment = new frag(); //change to like or unlike or all
                else if (tabHost.getCurrentTabTag().equals(names[1]))
                    fragment = new test(); //change to like or unlike or all
                else if (tabHost.getCurrentTabTag().equals(names[2]))
                    fragment = new ResultFragment(); //change to like or unlike or all
                if (fragment != null) {
                    fm.beginTransaction().replace(R.id.content_frame, fragment).commit();
                }*/
                setContent(tabId);
            }
        });
    }

    public void createSearchTabs() {
        for (int i = 0; i < searchTabs.length; i++) {
            String tabName = searchTabs[i];
            TabHost.TabSpec spec = tabHost.newTabSpec(tabName);
            spec.setContent(R.id.fakeTabContent);
            spec.setIndicator(tabName);
            tabHost.addTab(spec);
        }
///the first fragment
        fm.beginTransaction().replace(R.id.content_frame, new frag()).commit();
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {
               /*
                if (tabHost.getCurrentTabTag().equals(searchTabs[0]))
                    fragment = new frag(); //change to like or unlike or all
                else if (tabHost.getCurrentTabTag().equals(searchTabs[1]))
                    fragment = new test(); //change to like or unlike or all
                if (fragment != null) {
                    fm.beginTransaction().replace(R.id.content_frame, fragment).commit();
                }*/
                setContent(tabId);
            }
        });
    }

    public void setContent(String tag){

        if (tag.equals(searchTabs[0]))
            fragment = new frag(); //change to like or unlike or all
        else if (tabHost.getCurrentTabTag().equals(searchTabs[1]))
            fragment = new test(); //change to like or unlike or all
        if (tag.equals(names[0]))
            fragment = new frag(); //change to like or unlike or all
        else if (tag.equals(names[1]))
            fragment = new test(); //change to like or unlike or all
        else if (tag.equals(names[2]))
            fragment = new ResultFragment(); //change to like or unlike or all
        if (fragment != null) {
            fm.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }
    }


}
