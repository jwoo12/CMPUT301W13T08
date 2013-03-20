package com.foodbook.foodbook;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class PhotoManager extends Activity {
	
	private Uri imageFileUri;
	private Button add;
	private ImageButton imgButton;
	private final int BOGO_REQUEST_CODE = 1;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo_manager);
		
		imgButton = (ImageButton) findViewById(R.id.photoManager_imageBtn);
		imgButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		add = (Button) findViewById(R.id.photoManager_addButton);
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				

				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		        
		        String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tmp";
		        File folderF = new File(folder);
		        if (!folderF.exists()) {
		            folderF.mkdir();
		        }
		        
		        String imageFilePath = folder + "/" + String.valueOf(System.currentTimeMillis()) + "jpg";
		        File imageFile = new File(imageFilePath);
		        imageFileUri = Uri.fromFile(imageFile);
		        
		        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
		        startActivityForResult(intent, BOGO_REQUEST_CODE);
				
			}
		});
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == BOGO_REQUEST_CODE && resultCode == RESULT_OK) {
			imgButton.setImageDrawable(Drawable.createFromPath(imageFileUri.getPath()));
		}
		/*
		 *         if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            TextView tv = (TextView) findViewById(R.id.status);
            if (resultCode == RESULT_OK) {
                tv.setText("Photo OK!");
                ImageButton button = (ImageButton) findViewById(R.id.TakeAPhoto);
                button.setImageDrawable(Drawable.createFromPath(imageFileUri.getPath()));
            } else if (resultCode == RESULT_CANCELED) {
                tv.setText("Photo canceled");
            } else {
                tv.setText("Not sure what happened!" + resultCode);
            }
        }
		 */
	}
	
}
