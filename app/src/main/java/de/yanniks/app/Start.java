package de.yanniks.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import org.sliit.SplashScreenActivity;
import org.sliit.service.SharedPreferencesHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.ImageView;

public class Start extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
	}
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
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
    @Override
    protected Dialog onCreateDialog(int id) {
    	Dialog dialog = null;
    	CharSequence title = null;
    	LayoutInflater inflater = null;
    	View dialogLayout = null;
    	AlertDialog.Builder builder = null;
        switch (id) {
        	case SharedPreferencesHelper.DIALOG_ABOUT:
        		title = getResources().getText(R.string.app_name) + " - " + getResources().getText(R.string.version) + " " + SharedPreferencesHelper.getVersionName(this);
	        	
	        	/*
	        	 * Without cancel button
	        	dialog = new Dialog(this);
	        	dialog.setContentView(R.layout.dialog_about);
	        	dialog.setTitle(title);
	        	*/
        		inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        		dialogLayout = inflater.inflate(R.layout.dialog_about, null);
        		builder = new AlertDialog.Builder(this);
        		builder.setView(dialogLayout)
        			   .setTitle(title)
        			   .setIcon(R.drawable.ic_launcher)
        			   .setNeutralButton(R.string.close, new DialogInterface.OnClickListener() {
        		           public void onClick(DialogInterface dialog, int id) {
        		                dialog.cancel();
        		           }
        		       });
        		dialog = builder.create();
	        	break;
        	case SharedPreferencesHelper.DIALOG_NO_CONNECTION:
        		title = getString(R.string.error);
        		inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        		dialogLayout = inflater.inflate(R.layout.dialog_no_connection, null);
        		builder = new AlertDialog.Builder(this);
        		builder.setView(dialogLayout)
        			   .setTitle(title)
        			   .setIcon(R.drawable.ic_launcher)
        			   .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
        		           public void onClick(DialogInterface dialog, int id) {
        		                dialog.cancel();
        		           }
        		       });
        		dialog = builder.create();
        		break;
            default:
            	dialog = null;
        }
        return dialog;
    }
}
