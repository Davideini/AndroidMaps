package com.sce3.thirdyear.androidmaps;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.Toast;

import com.sce3.thirdyear.androidmaps.fragments.About;
import com.sce3.thirdyear.androidmaps.fragments.CustomSearchFragment;
import com.sce3.thirdyear.androidmaps.fragments.ForMapsFregment;
import com.sce3.thirdyear.androidmaps.fragments.HistoryFragment;
import com.sce3.thirdyear.androidmaps.fragments.ResultFragment;
import com.sce3.thirdyear.androidmaps.fragments.UserDetailsFragment;
import com.sce3.thirdyear.androidmaps.fragments.test;
import com.sce3.thirdyear.androidmaps.maps.AddressActivity;
import com.sce3.thirdyear.classes.Ad;
import com.sce3.thirdyear.classes.JSONRequest;
import com.sce3.thirdyear.classes.MenuAdapter;
import com.sce3.thirdyear.classes.MenuItemTemplate;
import com.sce3.thirdyear.classes.MenuTabs;
import com.sce3.thirdyear.classes.SQLiteDB;
import com.sce3.thirdyear.classes.User;
import com.sce3.thirdyear.maps.data.tools.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MenuActivity extends ActionBarActivity {
    /////////////////////////////////////////////////////////
    //static keys
    public final static String SER_KEY = "com.sce3.thirdyear.HouseDetailsActivity";
    ////////////////////static Drawer category
    final static String[] historyTabs = {"Liked", "Unliked", "ALL"};
    final static String[] searchTabs = {"By Address", "Custom Search"};
    final static String[] profileTabs = {"Personal Details", "My Ads"};
    public final static int Profile = 0;
    public final static int Search = 1;
    public final static int History = 2;
    public final static int About = 3;
    public final static int Logout = 4;
    public final static int RESULTS = 5;
    ////////////////////////////////////////////////////////////////////////
    //public ArrayList<Ad> results;
    public static ArrayList<Ad> resultsAds;
    public static int resultIndex = 0;
    /////////////////////////////////////////////////////////////////////////
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] menuTitles;
    private TypedArray menuIcons;
    private ArrayList<MenuItemTemplate> menuitems;
    ResultFragment resf;
    Fragment fragment = null;
    private int chosenMenuItem = -1;
    Bitmap b;
    ///////////////////////////////////////////////////////////
    private User user; //important for profile fragment
    ////////////////////////////////////////////////////////////
    private TabHost myTabHost;
    private MenuTabs mt;
    public static int historyTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SQLiteDB db = new SQLiteDB(getApplicationContext());
        String session = db.getSavedSession();

        //dbg
        System.out.println(session);
        //TextView txt = (TextView) findViewById(R.id.textView);
        //txt.setVisibility(View.INVISIBLE);

        if (!session.equals("")) {
//            String address = String.format("https://%s/JavaWeb/api?action=Main&session=%s", JSONRequest.SERVER, session);
            String address = String.format("https://%s/JavaWeb/api?action=Main&session=%s", JSONRequest.SERVER, session);
            JSONRequest json = new JSONRequest(address);
            System.out.println(address);
            try {
                JSONObject jobj = new JSONObject(json.getJSON());
                if (jobj.getString("result").equals("success")) {
                    //txt.setVisibility(View.VISIBLE); //logged by session.
                    /////////////////////////////////////////////////////////
                } else {
                    Intent i = new Intent(this, LoginActivity.class);
                    startActivity(i);
                }
            } catch (JSONException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                Toast.makeText(this, "Error receiving data.", Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
            }
        } else {
            //no session in local db or session expired on server
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
        setContentView(R.layout.activity_menu);
        Utility.SetupUIKeyboard(findViewById(R.id.drawer_layout), this);

        menuitems = new ArrayList<MenuItemTemplate>();
        mTitle = mDrawerTitle = getTitle();
        menuTitles = getResources().getStringArray(R.array.menu_array);
        menuIcons = getResources().obtainTypedArray(R.array.menu_icons);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        resultsAds = new ArrayList<Ad>();
        for (int i = 0; i < menuTitles.length; i++) {
            menuitems.add(new MenuItemTemplate(menuTitles[i], menuIcons.getResourceId(i, -1)));
        }

        menuIcons.recycle();


        mDrawerList.setAdapter(new MenuAdapter(getApplicationContext(), menuitems));
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
        myTabHost = (TabHost) findViewById(android.R.id.tabhost);
        myTabHost.setup();
        mt = new MenuTabs(myTabHost, getFragmentManager(), this);
////////////////////////////////////////////////////
        if (savedInstanceState == null) {
            ///here we can put the automatic search
//            Toast.makeText(this,"sfsf",Toast.LENGTH_LONG);
            selectItem(MenuActivity.Search);
        }


    }

    public void showFullscreen(View view) {
        try {
            resf.sentToFullscreenActivity(this);
        } catch (Exception e) {
        }
    }

    public void moreDetailsFromRes(View view) {
        ((ResultFragment) getFragmentManager().findFragmentById(R.id.content_frame)).moreDetails();
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
        fragment = null;
        TabWidget t = (TabWidget) findViewById(android.R.id.tabs);
        int count = t.getTabCount();
        if (t.getTabCount() != 0) {
            //t.removeAllViews();
            myTabHost.setCurrentTab(0);
            myTabHost.clearAllTabs();
        }
        //myTabHost.getTabWidget().removeAllViews();
        if (position == MenuActivity.Profile) {
            mt.createTabs(MenuActivity.profileTabs);
            fragment = new UserDetailsFragment();
            //fragment = new ResultFragment();
            // resf = new ResultFragment();
            //mt.createProfileTabs();
            //resf = new ResultFragment();
            //getFragmentManager().beginTransaction().replace(R.id.content_frame, resf).commit();
            // = (ResultFragment) fragment;
            //fragment=resf;
        } else if (position == MenuActivity.Search) {
            mt.createTabs(MenuActivity.searchTabs);
            fragment = new ForMapsFregment();
        } else if (position == MenuActivity.History) {
            mt.createTabs(MenuActivity.historyTabs);
            fragment = new HistoryFragment();
        } else if (position == MenuActivity.About) {

            fragment = new About();
        } else if (position == MenuActivity.RESULTS) {
            fragment = new ResultFragment();
        } else if (position == MenuActivity.Logout) {
            logOut();
            Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
            startActivity(intent);
            finish();
            return;
        }
        getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
///////////////this the way how to move things to Fragments
        //Bundle args = new Bundle();
        //args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        //fragment.setArguments(args);

        //FragmentManager fragmentManager = getFragmentManager();
        //fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        //resf=(ResultFragment) fragmentManager.findFragmentById(R.id.content_frame);
        if (position == MenuActivity.History || position == MenuActivity.Search || position == MenuActivity.Profile) {
            myTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

                @Override
                public void onTabChanged(String tabId) {
                    setContent(tabId);
                }
            });
            myTabHost.setCurrentTab(3);

        }

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
        inflater.inflate(R.menu.menu_menu, menu);
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
        if (id == R.id.add_appartment) {
            Intent i = new Intent(this, AddApartment.class);
            startActivity(i);
        }
        if (id == R.id.add_location) {
            Intent i = new Intent(this, AddressActivity.class);
            startActivity(i);
        }
        if (id == R.id.search_result) {
            //selectItem(MenuActivity.RESULTS);
            if (myTabHost.getTabWidget().getTabCount() != 0) {
                //t.removeAllViews();
                myTabHost.setCurrentTab(0);
                myTabHost.clearAllTabs();
            }
            getFragmentManager().beginTransaction().replace(R.id.content_frame, new ResultFragment()).commit();
            //((MenuItem)findViewById(R.id.search_result)).setIcon(R.drawable.ic_resultsfilled);
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
    /*public void okClick(View view) {

        resf.updateTextView();
    }*/

    public void setContent(String tag) {

        if (tag.equals(searchTabs[0]))
            fragment = new ForMapsFregment(); //change to like or unlike or all
        else if (tag.equals(searchTabs[1])) {
            fragment = new CustomSearchFragment();
            /*Bundle args = new Bundle();
            args.putSerializable("Results", results);
            fragment.setArguments(args);//change to like or unlike or all
            */
        } else if (tag.equals(historyTabs[0])) {
            historyTab = 2;
            fragment = new HistoryFragment(); //change to like or unlike or all
        } else if (tag.equals(historyTabs[1])) {
            historyTab = 1;
            fragment = new HistoryFragment(); //change to like or unlike or all
        } else if (tag.equals(historyTabs[2])) {
            historyTab = 0;
            fragment = new HistoryFragment(); //change to like or unlike or all
        } else if (tag.equals(profileTabs[0]))
            fragment = new UserDetailsFragment(); //change to like or unlike or all
        else if (tag.equals(profileTabs[1]))
            fragment = new test(); //change to like or unlike or all
        if (fragment != null) {
            {
                getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
            }

        }
    }

    private void logOut() {
        SQLiteDB db = new SQLiteDB(getApplicationContext());
        String session = db.getSavedSession();
        String address = String.format("https://%s/JavaWeb/api?action=Logout&session=%s", JSONRequest.SERVER, session);
        System.out.println(address);
        JSONRequest json = new JSONRequest(address);
        JSONObject jobj;
        try {
            jobj = new JSONObject(json.getJSON());
            if (!jobj.getString("result").equals("success")) {
                Toast.makeText(getApplicationContext(), "Error logging out.", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
