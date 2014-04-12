package de.yanniks.app;

import de.yanniks.app.menu.NavDrawerListAdapter;

import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import org.sliit.SplashScreenActivity;
import org.sliit.service.SharedPreferencesHelper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v4.widget.DrawerLayout;

import java.util.ArrayList;
import java.util.Arrays;

import de.yanniks.app.R;
import de.yanniks.app.menu.NavDrawerItem;

public class Start extends FragmentActivity {
    String device;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private String[] navMenuTitles2;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        mTitle = mDrawerTitle = getTitle();

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuTitles2 = getResources().getStringArray(R.array.nav_drawer_items_title);

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        if (Arrays.asList("htc_ace", "yakju").contains(device)) {
            navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(4, -1), true, getString(R.string.newmark)));
        } else {
            if (adbenabled() == true){
                navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(4, -1), true, getString(R.string.newmark)));
                Log.i(getString(R.string.cyandream), "Device not supported but ADB debugging is enabled.");
            } else
                Log.i(getString(R.string.cyandream), "Device " + device + " is not supported :(");
        }


        // Recycle the typed array
        navMenuIcons.recycle();

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);

        // enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ){
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(getString(R.string.navigation));
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            try {
                Intent intent = getIntent();
                String fragment = intent.getStringExtra("fragment");
                if (fragment.equals("imprint")) {
                    displayView(2);
                }
            } catch(NullPointerException e) {
                displayView(0);
            }
            // on first time display view for first nav item
        }
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
	}
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else if(item.getItemId() == R.id.menu_opt_about){
        	showDialog(SharedPreferencesHelper.DIALOG_ABOUT);
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected Dialog onCreateDialog(int id) {
    	Dialog dialog = null;
    	CharSequence title = null;
    	LayoutInflater inflater = null;
    	View dialogLayout = null;
    	AlertDialog.Builder builder = null;
        switch (id) {
        	case SharedPreferencesHelper.DIALOG_ABOUT:
        		title = getResources().getText(R.string.app_name) + " - " + getResources().getText(R.string.version) + " " + SharedPreferencesHelper.getVersionName(this);
        		inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        		dialogLayout = inflater.inflate(R.layout.dialog_about, null);
        		builder = new AlertDialog.Builder(this);
        		builder.setView(dialogLayout)
        			   .setTitle(title)
        			   .setIcon(R.drawable.ic_launcher)
        			   .setNeutralButton(R.string.close, new DialogInterface.OnClickListener() {
        		           public void onClick(DialogInterface dialog, int id) {
        		                dialog.cancel();
        		           }
        		       });
        		dialog = builder.create();
	        	break;
        	case SharedPreferencesHelper.DIALOG_NO_CONNECTION:
        		title = getString(R.string.error);
        		inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        		dialogLayout = inflater.inflate(R.layout.dialog_no_connection, null);
        		builder = new AlertDialog.Builder(this);
        		builder.setView(dialogLayout)
        			   .setTitle(title)
        			   .setIcon(R.drawable.ic_launcher)
        			   .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
        		           public void onClick(DialogInterface dialog, int id) {
        		                dialog.cancel();
        		           }
        		       });
        		dialog = builder.create();
        		break;
            default:
            	dialog = null;
        }
        return dialog;
    }
    public void cyandream (final View view) {
        startActivity (new Intent (this, cyandream.class));
    }
    public void blog (final View view) {
        startActivity(new Intent(this, SplashScreenActivity.class));
    }
    protected boolean adbenabled() {
        return (Settings.Secure.getInt(getContentResolver(), Settings.Secure.ADB_ENABLED, 0) == 1);
    }
    public void online (final View view) {
        Intent webview = new Intent(this, webview.class);
        webview.putExtra ("title",getString(R.string.impress));
        webview.putExtra("url", "http://yanniks.de/cms/api.php?lang=" + getString(R.string.lang) + "&launch=app&page=impressum");
        startActivity(webview);
    }
    public void more (final View view) {
        Intent select = new Intent(this, select.class);
        select.putExtra("yanniksdepath", "");
        startActivity(select);
    }
    public void social (final View view) {
        startActivity (new Intent (this, social.class));
    }
    private void displayView(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Start2();
                break;
            case 1:
                startActivity(new Intent(this, SplashScreenActivity.class));
                break;
            case 2:
                fragment = new impress();
                break;
            case 3:
                fragment = new cydia();
                break;
            case 4:
                fragment = new cdporter();
                break;
            case 5:
                fragment = new cyandream();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            if (adbenabled() == true) {
                if (position == 0) {
                    setTitle(navMenuTitles2[position] + " - " + getString(R.string.devmode));
                } else
                    setTitle(navMenuTitles2[position]);
            } else {
                setTitle(navMenuTitles2[position]);
                mDrawerLayout.closeDrawer(mDrawerList);
            }
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
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
}