package com.syntaxsofts.uiimprove.connectivity;

import java.io.InputStream;

import com.syntaxsofts.uiimprove.adapter.Get_Cropped_Round_Image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class Get_Bitmap_From_Url extends AsyncTask<String, Void, Bitmap> {

	ImageView imgIcon;
	
	public Get_Bitmap_From_Url(ImageView imgIcon) {
		this.imgIcon = imgIcon;
	}
	
	@Override
	protected Bitmap doInBackground(String... url) {
		String urlImage = url[0];
		Bitmap icon = null;
		try {
			InputStream inImage = new java.net.URL(urlImage).openStream();
			icon = BitmapFactory.decodeStream(inImage);
		}
		catch (Exception ex) {
			Log.e("getbitmapfromurl", ex.getMessage());
		}
		return icon;
	}
	
	@Override
	protected void onPostExecute(Bitmap result) {
		imgIcon.setImageBitmap(Get_Cropped_Round_Image.getRoundedCornerBitmap(result, 50));
	}

}
