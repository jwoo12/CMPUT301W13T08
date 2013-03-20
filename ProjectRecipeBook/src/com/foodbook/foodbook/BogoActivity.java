package com.foodbook.foodbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;



public class BogoActivity extends Activity {

	Uri imageFileUri;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bogo);
        
        setBogoPic();
        
        ImageButton button = (ImageButton)findViewById(R.id.TakeAPhoto);
        OnClickListener listener = new OnClickListener() {
            public void onClick(View v) {
                //takeAPhoto();
            	setBogoPic();
            }
        }; 
        button.setOnClickListener(listener);
        
        Button acceptButton = (Button)findViewById(R.id.Accept);
        
        acceptButton.setOnClickListener( new OnClickListener() {
            public void onClick(View v) {
            	processIntent(false);
            }        	
        });
        
        Button cancelButton = (Button)findViewById(R.id.Cancel);
        
        cancelButton.setOnClickListener( new OnClickListener() {
            public void onClick(View v) {
            	processIntent(true);
            }        	
        });

        
        
        
		        
        //takeAPhoto();
    }

   

    private Bitmap ourBMP;
    private void setBogoPic() {
    	Toast.makeText(this, "Generating Photo", Toast.LENGTH_LONG).show();
		ImageButton button = (ImageButton)findViewById(R.id.TakeAPhoto);
		ourBMP = BogoPicGen.generateBitmap(400, 400);
		button.setImageBitmap(ourBMP);
	}
    private File getPicturePath(Intent intent) {
        Uri uri = (Uri) intent.getExtras().get(MediaStore.EXTRA_OUTPUT);
        return new File(uri.getPath());
    }

    // call this to accept
    private void processIntent(boolean cancel) {
    	Intent intent = getIntent();
    	if (intent == null) {
    		return;
    	}
    	try {
    		if (intent.getExtras() != null) {    
    			if (cancel) {
    				Toast.makeText(this, "Photo Cancelled!", Toast.LENGTH_LONG).show();
    				setResult(RESULT_CANCELED);
    				finish();
    				return;
    			}
    			File intentPicture = getPicturePath(intent);
    			saveBMP(intentPicture, ourBMP);
    			setResult(RESULT_OK);
    		} else {
    			Toast.makeText(this, "Photo Cancelled: No Reciever?", Toast.LENGTH_LONG).show();
    			setResult(RESULT_CANCELED);
    		}
    	} catch (FileNotFoundException e) {
    		Toast.makeText(this, "Couldn't Find File to Write to?", Toast.LENGTH_LONG).show();
    		setResult(RESULT_CANCELED);    	
    	} catch (IOException e) {
    		Toast.makeText(this, "Couldn't Write File!", Toast.LENGTH_LONG).show();
    		setResult(RESULT_CANCELED);
    	}
    	finish();
    }
    private void saveBMP( File intentPicture, Bitmap ourBMP) throws IOException, FileNotFoundException {
    		OutputStream out = new FileOutputStream(intentPicture);
    		ourBMP.compress(Bitmap.CompressFormat.JPEG, 75, out);
    		out.close();
    }

}