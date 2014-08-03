/*
 * Class for the adapter of the cards for the shops list
 * Contains the method for showing the popup menu when the user clicks on the three dots
 * Also contains other methods implemented by BaseAdapter class
 * 
 * Author: SJo	Created Date: 29-07-14	Version: 2.0
 * 
 * Version History: 1.0 - Created the basic implementation. No options menu
 * 					1.1 - Implemented the code for the click of the card
 * 					1.2 - Code for rendering the image of the shop on the webview
 * 					1.3 - Removed webview as the card did not look good
 * 					2.0 - Completely new UI and added the popup menu for options
 * 					2.1 - Again a new UI (probably the final one) 
 */

package com.syntaxsofts.uiimprove.adapter;

import java.util.ArrayList;

import com.syntaxsofts.uiimprove.R;
import com.syntaxsofts.uiimprove.connectivity.Get_Bitmap_From_Url;
import com.syntaxsofts.uiimprove.model.Shop_Card_Item;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class Shop_Card_Adapter extends BaseAdapter {

	private Context context;
	private ArrayList<Shop_Card_Item> listShopCards;
	
	public Shop_Card_Adapter() { }
	
	// Constructor that accepts the context of the activity and the list of the cards
	public Shop_Card_Adapter(Context context, 
			ArrayList<Shop_Card_Item> listShopCards) {
		this.context = context;
		this.listShopCards = listShopCards;
	}
	
	@Override
	public int getCount() {
		return listShopCards.size();
	}

	@Override
	public Object getItem(int position) {
		return listShopCards.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	
	/*
	 * The method that renders the card and also handles
	 * the onclick events of the options image and the card itself
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null){
			LayoutInflater mInflater = (LayoutInflater)
					context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.list_item_card_shoplist, parent, false);
		}
		
		//Get the values from the arraylist and set it to the specific card 
		final TextView txtName = (TextView) convertView.findViewById(R.id.txtShopName);
		final ImageView imgIcon = (ImageView) convertView.findViewById(R.id.imgShop);
		TextView txtDescription = (TextView) convertView.findViewById(R.id.txtShopDescription);
		final ImageButton btnOptions = (ImageButton) convertView.findViewById(R.id.imgBtnOptions);
		
		txtName.setText(listShopCards.get(position).getTitle());
		txtDescription.setText(listShopCards.get(position).getDescription());
		new Get_Bitmap_From_Url(imgIcon).execute(listShopCards.get(position).getIconUrl());
		
		Typeface fontShopName = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
		Typeface fontShopDescription = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
		
		txtName.setTypeface(fontShopName);
		txtDescription.setTypeface(fontShopDescription);
		
		//Set the onclick listener for creating the popup menu for the card.
		btnOptions.setOnClickListener(new OnClickListener() {			
			
			@Override
			public void onClick(View v) {
				
				//Create the menu from the menu layout
				PopupMenu optionsMenu = new PopupMenu(context, btnOptions);
				optionsMenu.getMenuInflater().inflate(R.menu.options_click_shop_list, optionsMenu.getMenu());
				optionsMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
				
					//OnItemClick for the menu options
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						
						switch (item.getItemId()) {
						case R.id.showOnMap:
							
							//Open the map for searching the shop using its name
							Intent mapIntent = new Intent(Intent.ACTION_VIEW);
							mapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							mapIntent.setData(Uri.parse("http://maps.google.com/maps?q=" + txtName.getText().toString()));
							context.startActivity(mapIntent);
							break;

						default:
							break;
						}
						return true;
					}
				});
				optionsMenu.show();
			}
		});
		
		// The most important part of creating the card UI. If you do not set this as false
		// The card cannot be clicked 
		txtName.setFocusable(false);
		txtDescription.setFocusable(false);
		btnOptions.setFocusable(false);
		
		return convertView;
	}	
}
