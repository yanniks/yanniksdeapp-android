package de.yanniks.app;

import android.os.Bundle;
import org.sliit.SplashScreenActivity;
import android.view.View;
import org.holoeverywhere.app.Activity;
import android.content.Intent;

public class Start extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
	}
    public void impress (final View view) {
    	startActivity (new Intent (this, impress.class));
    }
    public void blog (final View view) {
    	startActivity (new Intent (this, SplashScreenActivity.class));
    }
}
