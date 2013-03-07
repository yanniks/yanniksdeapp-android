package de.yanniks.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.holoeverywhere.app.Activity;

public class impress extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.impress);
	}
    public void social (final View view) {
    	startActivity (new Intent (this, social.class));
    }
}
