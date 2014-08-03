/*
 * Class for getting the lsist of all the shops present in the framework
 * Gets the values from the cloud
 * 
 * Author: SJo	Created Date: 29-07-14	Version: 1.0
 */

package com.syntaxsofts.uiimprove.connectivity;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class GetAllShops extends AsyncTask<String, String, String> {

	private Context context;
	private ProgressDialog mDialog;
	private AsyncTaskCompleteListener<String> callback;
	
	/* Constructor method. Contains the callback as well
	 * Callback is used here because whenever the get() method
	 * for the asynctask is called, the request is sent immediately
	 * So the asynctask rather executes as a synchronous task. But when the callback is
	 * implemented, the system enters a wait loop until the callback function is called.
	 * Once the callback function is called, we can do the operations from the function.
	 */
	public GetAllShops(Context context, AsyncTaskCompleteListener<String> callback) { 
		this.context = context;
		this.callback = callback;
	}
	
	// Show loading dialog before request
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mDialog = new ProgressDialog(this.context);
		mDialog.setMessage("Loading...");
		mDialog.show();
	}
	
	// Send request and retrieve list of shops
	@Override
	protected String doInBackground(String... params) {
		
		HttpClient client = new DefaultHttpClient();
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String response;
		
		try {
			String URL = "http://shopsoft.syntaxsofts.com/phpServer/lstAllShops.php";
			HttpGet get = new HttpGet(URL);
			
			response = client.execute(get, responseHandler);
			return response.split("<!")[0].trim();
		}
		catch (Exception ex) {
			return "ERROR_GET_SHOP_LIST";
		}
	}
	
	// Dismiss the dialog and call the callback function 
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		mDialog.dismiss();
		callback.onTaskComplete(result);
	}

}
