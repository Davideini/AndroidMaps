package com.sce3.thirdyear.classes;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.Toast;

import com.sce3.thirdyear.androidmaps.R;
import com.sce3.thirdyear.androidmaps.fragments.ResultFragment;
import com.sce3.thirdyear.androidmaps.fragments.frag;
import com.sce3.thirdyear.androidmaps.fragments.test;

import java.util.ArrayList;

/**
 * Created by win7 on 31/05/2015.
 */
public class HistoryTabs  {
    private TabHost tabHost;
    private FragmentManager fm;
    private Context context;
    final static String [] names ={"Liked","Unliked","ALL"};
    public HistoryTabs(TabHost tabhost,FragmentManager fm)
    {
        this.tabHost=tabhost;
        this.fm=fm;
        createTabs();
    }

    public void createTabs()
    {
        for(int i=0;i<names.length;i++)
        {
            String tabName = names[i];
            TabHost.TabSpec spec=tabHost.newTabSpec(tabName);
            spec.setContent(R.id.fakeTabContent);
            spec.setIndicator(tabName);
            tabHost.addTab(spec);
        }

        fm.beginTransaction().replace(R.id.content_frame, new frag()).commit();
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {
                Fragment fragment=null;
                if(tabHost.getCurrentTabTag().equals(names[0]))
                fragment = new frag(); //change to like or unlike or all
                else if(tabHost.getCurrentTabTag().equals(names[1]))
                    fragment = new test(); //change to like or unlike or all
                else if(tabHost.getCurrentTabTag().equals(names[2]))
                    fragment = new ResultFragment(); //change to like or unlike or all
                if(fragment!=null) {
                    fm.beginTransaction().replace(R.id.content_frame, fragment).commit();
                }
            }
        });


    }
}
