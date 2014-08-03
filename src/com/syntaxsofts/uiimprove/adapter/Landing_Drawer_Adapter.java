/*
 * Custom adapter for the list view of the drawer menu on the main page layout
 * Extends BaseAdapter and all the functions are added automatically
 * Author: SJo	Created Date: 27-07-14	 Version: 1.0
 */

package com.syntaxsofts.uiimprove.adapter;

import java.util.ArrayList;

import com.syntaxsofts.uiimprove.R;
import com.syntaxsofts.uiimprove.model.Landing_Drawer_Item;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Landing_Drawer_Adapter extends BaseAdapter {
	
	private Context context;
	
	//Create a list of item of the landing drawer
	private ArrayList<Landing_Drawer_Item> landingDrawerItemList;

	public Landing_Drawer_Adapter(){ }
	
	public Landing_Drawer_Adapter(Context context, 
			ArrayList<Landing_Drawer_Item> landingDrawerItemList){
		this.context = context;
		this.landingDrawerItemList = landingDrawerItemList;
	}
	
	@Override
	public int getCount() {
		return landingDrawerItemList.size();
	}

	@Override
	public Object getItem(int position) {
		return landingDrawerItemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/*	Overriden method for getting the custom view of the item of the list
	*	Uses layout inflator to inflate the view according to the item specified in the layout
	*	Then sets the text and the icon to the values from the constructor
	*/
	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			LayoutInflater mInflater = (LayoutInflater)
					context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.drawer_list_item, null);
		}
		
		ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
		TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
		
		imgIcon.setImageResource(landingDrawerItemList.get(position).getIcon());
		txtTitle.setText(landingDrawerItemList.get(position).getTitle());
		
		return convertView;
	}
	
}
