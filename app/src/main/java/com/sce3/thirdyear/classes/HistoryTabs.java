package com.sce3.thirdyear.classes;

import android.content.Context;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.sce3.thirdyear.androidmaps.R;

import java.util.ArrayList;

/**
 * Created by win7 on 31/05/2015.
 */
public class HistoryTabs  {
    private TabHost tabHost;
    private TabWidget tabWidget;
    private Context context;
    final static String [] names ={"Liked","Unliked","ALL"};
    public HistoryTabs(Context context,TabHost tabhost,TabWidget tabWidget)
    {
        this.context=context;
        this.tabHost=tabhost;
        this.tabWidget=tabWidget;

    }

    public boolean createTabs()
    {
        for(int i=0;i<names.length;i++)
        {
            String tabName = names[i];
            TabHost.TabSpec spec=tabHost.newTabSpec(names[i]);
            spec.setContent(R.id.fakeTabContent);
            spec.setIndicator(tabName);
            tabHost.addTab(spec);

        }
        return false;
    }
}
