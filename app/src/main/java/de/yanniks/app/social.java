package de.yanniks.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class social extends Activity {
    private GestureDetector gestureDetector;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.social);
        try {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e) {
            Log.i("yanniks.deApp", "Cannot set ActionBar as Home");
        }
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                startActivity(new Intent(social.this, game.class));
                return true;
            }
        });
	}
    private boolean isPackageInstalled() {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo("com.facebook.katana", PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
	public void facebook (View view) {
        if (isPackageInstalled()) {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.openinfb))
                    .setMessage(getString(R.string.openinfb_desc))
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/132390900157794")));
                        }
                    })
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/yanniks.de")));
                        }
                    })
                    .show();
        } else {
   		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/yanniks.de")));
   	    }
}
	public void gplus (View view) {
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/113445355917245195373")));
	}
	public void twitter (View view) {
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://twitter.com/yanniksde")));
	}
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	{
    	       switch (item.getItemId()) 
    	        {
    	        case android.R.id.home: 
    	            onBackPressed();
    	            break;
    	        default:
    	}
    	}
		return false;
    }
}