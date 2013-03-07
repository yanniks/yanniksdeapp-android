package de.yanniks.app;

import android.os.Bundle;
import android.webkit.WebView;
import org.holoeverywhere.app.Activity;

public class gplus extends Activity {

	private WebView webview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gplus);
    	webview = (WebView) findViewById(R.id.gplusweb);
    	webview.getSettings().setJavaScriptEnabled(true);
    	webview.loadUrl("http://yanniks.de/google+");
    }
}
