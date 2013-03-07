package de.yanniks.app;

import android.os.Bundle;
import android.webkit.WebView;
import org.holoeverywhere.app.Activity;

public class facebook extends Activity {
	private WebView webview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facebook);
    	webview = (WebView) findViewById(R.id.fbweb);
    	webview.getSettings().setJavaScriptEnabled(true);
    	webview.loadUrl("http://yanniks.de/facebook");
    }
}
