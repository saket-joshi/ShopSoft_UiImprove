/*
 * Class controlling the main landing page
 * Contains the methods for creating the list of all the side
 * menu options on the main page
 * 
 * Author: SJo	Created Date: 27-07-14	Version: 1.0
 */

package com.syntaxsofts.uiimprove;

import java.util.ArrayList;

import com.syntaxsofts.uiimprove.adapter.Landing_Drawer_Adapter;
import com.syntaxsofts.uiimprove.model.Landing_Drawer_Item;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

	private DrawerLayout mDrawerLayout;
	
	//ListView that contains all the menu items. Will be used to handle onItemClick calls
	private ListView mDrawerList;

	//Used to handle the drawer menu toggle so that settings menu can be hidden
	private ActionBarDrawerToggle mDrawerToggle;
	
	@SuppressWarnings("unused")
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	
	//Create a list of the items of the drawer. The item is a model which is described in another package
	private ArrayList<Landing_Drawer_Item> landingDrawerItems;
	private Landing_Drawer_Adapter drawerAdapter;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mTitle = mDrawerTitle = getTitle();
		
		//Get the list of icon resource and titles of the menu from the resource
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
		navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
		
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		
		//Add all the items to the list
		landingDrawerItems = new ArrayList<Landing_Drawer_Item>();
		landingDrawerItems.add(new Landing_Drawer_Item(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		landingDrawerItems.add(new Landing_Drawer_Item(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		landingDrawerItems.add(new Landing_Drawer_Item(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
		landingDrawerItems.add(new Landing_Drawer_Item(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
		
		navMenuIcons.recycle();
		
		//Create a new adapter for the drawer list
		drawerAdapter = new Landing_Drawer_Adapter(getApplicationContext(), landingDrawerItems);
		mDrawerList.setAdapter(drawerAdapter);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
        //Create the toggle so that settings option is hidden when the menu is open
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, 
        		R.string.app_name, R.string.app_name) {
        	
        	public void onDrawerClosed(View view) {
        		getActionBar().setTitle(mTitle);
        		invalidateOptionsMenu();
        	}
        	
        	public void onDrawerOpened(View view) {
        		getActionBar().setTitle(mTitle);
        		invalidateOptionsMenu();
        	}
        };
        
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        
        mDrawerList.setOnItemClickListener(new DrawerOnclickListener());
	}
	
	//Class for implementing the OnItemClicks for the list of the drawer
	private class DrawerOnclickListener implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int position,
				long id) {
			displayFragment(position);
		}
		
		private void displayFragment(int position) {
			Fragment mFragment = null;
			
			switch (position) {
			case 0:			
				mFragment = new ShopFragment(getApplicationContext());
				break;

			default:
				break;
			}
			
			if(mFragment != null) {
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction().replace(R.id.frame_container, mFragment).commit();
				
				mDrawerList.setItemChecked(position, true);
				mDrawerList.setSelection(position);
				setTitle(navMenuTitles[position]);
				mDrawerLayout.closeDrawer(mDrawerList);				
			}
			else {
				Log.e("MainActivity", "Error in creating fragment");
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	//Hide the settings menu
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean isDrawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!isDrawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
}
