package de.yanniks.app;

import android.os.Bundle;
import android.view.View;

import org.holoeverywhere.app.Activity;
import org.sliit.SplashScreenActivity;
import org.sliit.service.SharedPreferencesHelper;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.content.Intent;

public class Start extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
	}
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }  

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_opt_about){
        	showDialog(SharedPreferencesHelper.DIALOG_ABOUT);
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void impress (final View view) {
    	startActivity (new Intent (this, impress.class));
    }
    public void blog (final View view) {
    	startActivity (new Intent (this, SplashScreenActivity.class));
    }
}
