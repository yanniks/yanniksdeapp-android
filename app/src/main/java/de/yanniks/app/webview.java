package de.yanniks.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class webview extends Activity {
    private WebView mWebView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        final Intent intent = getIntent();
        final String title = intent.getStringExtra(("title"));
        getActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        String weburl = "http://yanniks.de/cms/api.php?launch=app&lang=" + getString(R.string.lang) + "&page=" + intent.getStringExtra("url");
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