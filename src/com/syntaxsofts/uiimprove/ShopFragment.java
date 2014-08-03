/*
 * Class for the fragment of the shop list
 * Contains methods for calling the getList of all shops
 * Populates it in a card list and implements the onclick methods for the card
 * 
 * Author: SJo	Created Date: 29-07-14	Version: 2.0
 * Version History: 1.0 - Basic implementation of the list
 * 					1.1 - Implemented the onclick of the cards
 * 					1.2 - Minor changes
 * 					1.3 - Implementation of the callback function
 * 					1.4 - First implementation of the popup menu
 * 					2.0 - Moved the code of the popup menu to the card adapter
 */

package com.syntaxsofts.uiimprove;

import java.util.ArrayList;
import com.syntaxsofts.uiimprove.adapter.Shop_Card_Adapter;
import com.syntaxsofts.uiimprove.connectivity.AsyncTaskCompleteListener;
import com.syntaxsofts.uiimprove.connectivity.GetAllShops;
import com.syntaxsofts.uiimprove.model.Shop_Card_Item;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

// Implements the asynctask complete listener so that the task is really done asyncly
public class ShopFragment extends Fragment implements AsyncTaskCompleteListener<String> {
	
	// Context for the current fragment
	private Context rootContext;
	
	// Context for the application, derived from main activity
	private Context context;
	private ListView lstShopView;
	private ArrayList<Shop_Card_Item> lstShops;
	private AsyncTask<String, String, String> obj;
	
	int count;
	
	public ShopFragment() {	}
	public ShopFragment(Context context) {
		this.context = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
				
		View rootView = inflater.inflate(R.layout.fragment_shop, container, false);
		this.rootContext = rootView.getContext();
		lstShopView = (ListView) rootView.findViewById(R.id.list_shop);
		
		lstShopView.addFooterView(new View(rootView.getContext()));
		lstShopView.addHeaderView(new View(rootView.getContext()));		
		
		lstShops = new ArrayList<Shop_Card_Item>();
		
		obj = new GetAllShops(rootView.getContext(), this).execute("NULL");
		
		return rootView;
	}
	
	// Onclick of the shop card
	private class ShopListItemClickListener implements OnItemClickListener {

		private Context context;
		
		public ShopListItemClickListener(Context context) {
			this.context = context;
		}
		
		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int position,
				long id) {
			Toast.makeText(this.context, String.valueOf(position), Toast.LENGTH_SHORT).show();
			view.startAnimation(AnimationUtils.loadAnimation(rootContext, R.animator.animation_card_click));
			
			/*
			 * Next what to do: Now whenever a user clicks on the card, it should open a new activity
			 * The newly created activity shows the tabs of all the categories in the shop
			 * It should be self refreshable. Also swipeable. So basically whenever a user enters a tab
			 * He is presented with another set of cards of the sub categories of the selected category 
			 */
			
		}
	}

	// Function for populating the card list and setting the onclick listeners etc.
	@Override
	public void onTaskComplete(String result) {
		try {
			String[] splitString = obj.get().split("%");
			for(count = 0;count < splitString.length;count++){
				final String[] shopInfo = splitString[count].split("=");
				lstShops.add(new Shop_Card_Item(shopInfo[0], shopInfo[1], shopInfo[2]));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Shop_Card_Adapter adapter = new Shop_Card_Adapter(context, lstShops);
		lstShopView.setAdapter(adapter);
		lstShopView.setOnItemClickListener(new ShopListItemClickListener(rootContext));
	}
}
