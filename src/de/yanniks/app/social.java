package de.yanniks.app;

import org.holoeverywhere.app.Activity;
import org.sliit.SplashScreenActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class social extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.social);
	}
    public void facebook (final View view) {
    	startActivity (new Intent (this, facebook.class));
    }
    public void gplus (final View view) {
    	startActivity (new Intent (this, gplus.class));
    }
    public void twitter (final View view) {
    	startActivity (new Intent (this, twitter.class));
    }
}
