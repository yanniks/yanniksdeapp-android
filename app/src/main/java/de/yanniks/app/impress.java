package de.yanniks.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class impress extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.impress);
        getActionBar().setDisplayHomeAsUpEnabled(true);
	}
    public void social (final View view) {
    	startActivity (new Intent (this, social.class));
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
