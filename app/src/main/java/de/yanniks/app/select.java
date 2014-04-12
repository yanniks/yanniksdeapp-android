package de.yanniks.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Arrays;

public class select extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cyandreamselect);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle(getString(R.string.learnmore_title));
        Intent intent = getIntent();
        String yanniksdepath = intent.getStringExtra("yanniksdepath");
        String lookup2 = yanniksdepath.replace("/appcheck/","");
        String lookup = lookup2.replace("/appcheck","");

        if (!lookup.isEmpty()) {
            page(lookup);
        }
        if (!Arrays.asList("htc_ace", "yakju", "occam", "t03gxx", "full_falcon").contains(Build.PRODUCT)) {
            TextView notsupported = (TextView) findViewById(R.id.notsupported);
            notsupported.setVisibility(View.VISIBLE);
            if (adbenabled() == false) {
                Button xda = (Button) findViewById(R.id.xda);
                xda.setVisibility(View.INVISIBLE);
                Button ah = (Button) findViewById(R.id.androidhilfe);
                ah.setVisibility(View.INVISIBLE);
                Button androidpit = (Button) findViewById(R.id.androidpit);
                androidpit.setVisibility(View.INVISIBLE);
            }
        }
    }
    public void xda (View view) {
        page("xda");
    }
    public void androidhilfe (View view) {
        page("ah");
    }
    public void androidpit (View view) {
        page("androidpit");
    }
    public void page (String page) {
        if (adbenabled() == true) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.yanniks.de/roms/cdinfo.php?lang=" + getString(R.string.lang) + "&device=" + Build.PRODUCT + "&adb=true&page=" + page)));
        } else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.yanniks.de/roms/cdinfo.php?lang=" + getString(R.string.lang) + "&device=" + Build.PRODUCT + "&page=" + page)));
        }
    }
    protected boolean adbenabled() {
        return (Settings.Secure.getInt(getContentResolver(), Settings.Secure.ADB_ENABLED, 0) == 1);
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