package com.sce3.thirdyear.classes;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.sce3.thirdyear.androidmaps.R;
import com.sce3.thirdyear.androidmaps.fragments.HistoryFragment;
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
    Fragment fragment = null;
    public MenuTabs(TabHost tabhost,FragmentManager fm,Context context)
    {
        this.tabHost=tabhost;
        this.fm=fm;
        this.context=context;
    }
    public void createTabs(String [] tabsnames) {
        for (int i = 0; i < tabsnames.length; i++) {
            String tabName = tabsnames[i];
            TabHost.TabSpec spec = tabHost.newTabSpec(tabName);
            spec.setContent(new TabHost.TabContentFactory() {
                @Override
                public View createTabContent(String tag) {
                    final TextView tv = new TextView(context);
                    tv.setText("Content for tab with tag " + tag);
                    return tv;
                }

            });
            spec.setIndicator(tabName);
            tabHost.addTab(spec);
        }
    }
/*
    public void createHistoryTabs() {
        for (int i = 0; i < historyTabs.length; i++) {
            String tabName = historyTabs[i];
            TabHost.TabSpec spec = tabHost.newTabSpec(tabName);
            //spec.setContent(R.id.fakeTabContent);

            spec.setContent(new TabHost.TabContentFactory() {
                @Override
                public View createTabContent(String tag) {
                    final TextView tv = new TextView(context);
                    tv.setText("Content for tab with tag " + tag);
                    return tv;
                }

            });
            spec.setIndicator(tabName);
            tabHost.addTab(spec);

        }
        fm.beginTransaction().replace(R.id.content_frame, new frag()).commit();
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {
                setContent(tabId);
            }
        });
        tabHost.setCurrentTab(0);
    }


    public void createProfileTabs() {
        for (int i = 0; i < profileTabs.length; i++) {
            String tabName = profileTabs[i];
            TabHost.TabSpec spec = tabHost.newTabSpec(tabName);
            spec.setContent(R.id.fakeTabContent);

            spec.setIndicator(tabName);
            tabHost.addTab(spec);
        }
        fm.beginTransaction().replace(R.id.content_frame, new frag()).commit();
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {
                setContent(tabId);
            }
        });
    }
    public void createSearchTabs() {

        for (int i = 0; i < searchTabs.length; i++) {
            String tabName = searchTabs[i];
            TabHost.TabSpec spec = tabHost.newTabSpec(tabName);
            //spec.setContent(R.id.fakeTabContent);
            spec.setContent(new TabHost.TabContentFactory() {
                @Override
                public View createTabContent(String tag) {
                    final TextView tv = new TextView(context);
                    tv.setText("Content for tab with tag " + tag);
                    return tv;
                }

            });
            spec.setIndicator(tabName);
            tabHost.addTab(spec);
        }
        fm.beginTransaction().replace(R.id.content_frame, new frag()).commit();
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {
                setContent(tabId);
            }
        });
        tabHost.setCurrentTab(0);
    }

    public void setContent(String tag){

        if (tag.equals(searchTabs[0]))
            fragment = new frag(); //change to like or unlike or all
        else if (tabHost.getCurrentTabTag().equals(searchTabs[1]))
            fragment = new test(); //change to like or unlike or all
        else if (tag.equals(historyTabs[0]))
            fragment = new HistoryFragment(); //change to like or unlike or all
        else if (tag.equals(historyTabs[1]))
            fragment = new test(); //change to like or unlike or all
        else if (tag.equals(historyTabs[2]))
            fragment = new ResultFragment(); //change to like or unlike or all
        else if (tag.equals(profileTabs[0]))
            fragment = new frag(); //change to like or unlike or all
        else if (tag.equals(profileTabs[1]))
            fragment = new test(); //change to like or unlike or all
        if (fragment != null) {
            {
                fm.beginTransaction().replace(R.id.content_frame, fragment).commit();
            }

        }
    }
*/

}
