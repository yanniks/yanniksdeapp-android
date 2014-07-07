package de.yanniks.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import org.sliit.service.SharedPreferencesHelper;

public class webview extends Activity {
    private WebView mWebView;
    String weburl;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        final Intent intent = getIntent();
        final String title = intent.getStringExtra(("title"));
        try {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e) {
            Log.i("yanniks.deApp", "Cannot set ActionBar as Home");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean ssl = prefs.getBoolean("usessl",false);
        if(intent.getStringExtra("url").contains("http")) {
            weburl = intent.getStringExtra("url");
        } else {
            if (!ssl) {
                weburl = "http://yanniks.de/cms/api.php?launch=app&lang=" + getString(R.string.lang) + "&page=" + intent.getStringExtra("url") + "&version=" + SharedPreferencesHelper.getVersionName(this);
            } else {
                weburl = "https://yanniksde-updatechecker.rhcloud.com/cms/api.php?launch=app&lang=" + getString(R.string.lang) + "&page=" + intent.getStringExtra("url") + "&version=" + SharedPreferencesHelper.getVersionName(this);
            }
        }
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(weburl);
        isOnline();
        mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress)
            {
                setTitle(getString(R.string.loading));
                setProgress(progress * 100);
                if(progress == 100)
                    if (title.equals("")) {
                        getActionBar().setTitle(intent.getStringExtra("url"));
                    } else {
                        getActionBar().setTitle(title);
                    }
            }
        });
    }
    public void isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getBaseContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo i = cm.getActiveNetworkInfo();
        if ((i == null) || (!i.isConnected())) {
            mWebView.loadUrl("file:///android_asset/error-" + getString(R.string.lang) + ".html");
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch(keyCode)
            {
                case KeyEvent.KEYCODE_BACK:
                    if(mWebView.canGoBack()){
                        mWebView.goBack();
                    }else{
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
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