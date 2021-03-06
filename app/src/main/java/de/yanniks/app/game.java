package de.yanniks.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;

public class game extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e) {
            Log.i("yanniks.deApp", "Cannot set ActionBar as Home");
        }
        getActionBar().setTitle("2048");
        getActionBar().setSubtitle("Easteregg");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        String weburl = "file:///android_asset/2048/index-" + getString(R.string.lang) + ".html";
        WebView mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(weburl);
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