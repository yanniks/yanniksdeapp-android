package de.yanniks.app;

import android.os.Bundle;
import android.webkit.WebView;
import org.holoeverywhere.app.Activity;

public class twitter extends Activity {

	private WebView webview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twitter);
    	webview = (WebView) findViewById(R.id.twitterweb);
    	webview.getSettings().setJavaScriptEnabled(true);
    	webview.loadUrl("http://yanniks.de/twitter");
    }
}
