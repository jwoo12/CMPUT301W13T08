package com.foodbook.foodbook;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * 
 * Adapter for gallery.
 * <p>code taken from: http://android-er.blogspot.ca/2012/07/implement-android-gallery-widget-with.html
 *
 */


public class GalleryBaseAdapter extends BaseAdapter {

	ArrayList<String> GalleryFileList;
	Context context;
	
	/**
	 * Constructor method for adapter
	 * @param cont
	 */

	public GalleryBaseAdapter(Context cont) {
		context = cont;
		GalleryFileList = new ArrayList<String>();
	}

	@Override
	public int getCount() {
		return GalleryFileList.size();
	}

	@Override
	public Object getItem(int position) {
		return GalleryFileList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Bitmap bm = BitmapFactory.decodeFile(GalleryFileList.get(position));
		Bitmap bm = decodeSampledBitmapFromUri(GalleryFileList.get(position), 200, 200);

		LinearLayout layout = new LinearLayout(context);
		layout.setLayoutParams(new Gallery.LayoutParams(250, 250));
		layout.setGravity(Gravity.CENTER);

		ImageView imageView = new ImageView(context);
		imageView.setLayoutParams(new Gallery.LayoutParams(200, 200));
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setImageBitmap(bm);

		layout.addView(imageView);
		return layout;

	}

	/**
	 * 
	 * Adds new item
	 * 
	 * @param newitem
	 */
	
	public void add(String newitem) {
		GalleryFileList.add(newitem);
	}
	
	/**
	 * 
	 * Resizes image to a smaller size
	 * 
	 * @param path
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */

	public Bitmap decodeSampledBitmapFromUri(String path, int reqWidth, int reqHeight) {
		Bitmap bm = null;

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		bm = BitmapFactory.decodeFile(path, options);

		return bm;
	}
	
	/**
	 * 
	 * Calculates sample size (for resizing)
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */

	public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float) height / (float) reqHeight);
			} else {
				inSampleSize = Math.round((float) width / (float) reqWidth);
			}
		}

		return inSampleSize;
	}

}
