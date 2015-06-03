package com.sce3.thirdyear.androidmaps;


import android.app.Fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.google.android.gms.internal.cl;
import com.sce3.thirdyear.androidmaps.fragments.HistoryFragment;
import com.sce3.thirdyear.androidmaps.fragments.ResultFragment;
import com.sce3.thirdyear.androidmaps.fragments.frag;
import com.sce3.thirdyear.androidmaps.fragments.test;
import com.sce3.thirdyear.androidmaps.maps.AddressActivity;
import com.sce3.thirdyear.classes.MenuAdapter;
import com.sce3.thirdyear.classes.MenuItemTemplate;
import com.sce3.thirdyear.classes.MenuTabs;
import com.sce3.thirdyear.classes.User;
import java.util.ArrayList;


public class MenuActivity extends ActionBarActivity {
    /////////////////////////////////////////////////////////
    //static keys
    public  final static String SER_KEY = "com.sce3.thirdyear.HouseDetailsActivity";
    ////////////////////static Drawer category
    final static String [] historyTabs ={"Liked","Unliked","ALL"};
    final static String [] searchTabs={"By Address","Custom Search"};
    final static String [] profileTabs={"Personal Details" ,"My Ads"};
    public final static  int Profile =0;
    public final static  int Search = 1;
    public final static  int History = 2;
    public final static  int About = 3;
    public final static  int Logout = 4;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] menuTitles;
    private TypedArray menuIcons;
    private ArrayList<MenuItemTemplate> menuitems;
    ResultFragment resf;
    Fragment fragment=null;
    private int chosenMenuItem=-1;
    Bitmap b;
    ///////////////////////////////////////////////////////////
    private User user; //important for profile fragment
    ////////////////////////////////////////////////////////////
    private  TabHost myTabHost;
    private MenuTabs mt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        menuitems = new ArrayList<MenuItemTemplate>();
        mTitle = mDrawerTitle = getTitle();
        menuTitles = getResources().getStringArray(R.array.menu_array);
        menuIcons =getResources().obtainTypedArray(R.array.menu_icons);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        for(int i=0;i<menuTitles.length;i++)
        {
            menuitems.add(new MenuItemTemplate(menuTitles[i], menuIcons.getResourceId(i,-1)));
        }

        menuIcons.recycle();


        mDrawerList.setAdapter(new MenuAdapter(getApplicationContext(),menuitems));
                mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        
        //Object a=getSupportActionBar();
        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        //define the action of drawer(open,close)
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
               // getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                getSupportActionBar().setIcon(R.drawable.ic_home);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

////////////////////////////////////////////////////////////////
        myTabHost=(TabHost)findViewById(android.R.id.tabhost);
        myTabHost.setup();
        mt=new MenuTabs(myTabHost,getFragmentManager(),this);
////////////////////////////////////////////////////
        if (savedInstanceState == null) {
            ///here we can put the automatic search
//            Toast.makeText(this,"sfsf",Toast.LENGTH_LONG);
            selectItem(0);
        }




    }

    public void showFullscreen(View view) {

       resf.sentToFullscreenActivity(this);

    }

    public void moreDetailsFromRes(View view) {
        ((ResultFragment)getFragmentManager().findFragmentById(R.id.content_frame)).moreDetails();
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
        fragment=null;
        TabWidget t=(TabWidget)findViewById(android.R.id.tabs);
        int count=t.getTabCount();
       if(t.getTabCount()!=0) {
            //t.removeAllViews();
            myTabHost.setCurrentTab(0);
            myTabHost.clearAllTabs();
        }
            //myTabHost.getTabWidget().removeAllViews();
        if(position==MenuActivity.Profile) {
            mt.createTabs(MenuActivity.profileTabs);
            //fragment = new ResultFragment();
           // resf = new ResultFragment();
            //mt.createProfileTabs();
             //resf = new ResultFragment();
            //getFragmentManager().beginTransaction().replace(R.id.content_frame, resf).commit();
            // = (ResultFragment) fragment;
            //fragment=resf;
        }
        else if(position==MenuActivity.Search){
            mt.createTabs(MenuActivity.searchTabs);
        }
        else if(position==MenuActivity.History){
            mt.createTabs(MenuActivity.historyTabs);

        }
        else {
            fragment=new ResultFragment();
            getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();}


        //Bundle args = new Bundle();
        //args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        //fragment.setArguments(args);

        //FragmentManager fragmentManager = getFragmentManager();
        //fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        //resf=(ResultFragment) fragmentManager.findFragmentById(R.id.content_frame);
        if(position==MenuActivity.History||position==MenuActivity.Search) {
            myTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

                @Override
                public void onTabChanged(String tabId) {
                    setContent(tabId);
                }
            });
            myTabHost.setCurrentTab(0);

        }
        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(menuitems.get(position).getIcon());
        getSupportActionBar().setTitle(menuitems.get(position).getTitle());
        mDrawerLayout.closeDrawer(mDrawerList);


    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id ==R.id.action_addApartment)
        {
            Intent i=new Intent(this, AddApartment.class);
            startActivity(i);
        }
        if(id==R.id.action_AddLocation)
        {
            Intent i=new Intent(this, AddressActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    public void okClick(View view) {

        resf.updateTextView();
    }

    public void setContent(String tag){

        if (tag.equals(searchTabs[0]))
            fragment = new frag(); //change to like or unlike or all
        else if (tag.equals(searchTabs[1]))
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
                getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
            }

        }
    }


}
