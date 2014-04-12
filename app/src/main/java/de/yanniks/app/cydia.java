package de.yanniks.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class cydia extends Fragment {
    private String curURL;
    public void init(String url) {
        curURL = url;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        init("http://yanniks.de/cms/api.php?launch=app&lang=" + getString(R.string.lang) + "&page=cydia");
        View view = inflater
                .inflate(R.layout.webview, container, false);
        if (curURL != null) {
            WebView webview = (WebView) view.findViewById(R.id.webview);
            webview.getSettings().setJavaScriptEnabled(true);
            webview.setWebViewClient(new webClient());
            if (isOnline() == true) {
                webview.loadUrl(curURL);
            } else {
                webview.loadUrl("file:///android_asset/error-" + getString(R.string.lang) + ".html");
            }
            webview.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress)
                {
                    getActivity().setTitle(getString(R.string.loading));
                    getActivity().setProgress(progress * 100);
                    if(progress == 100)
                        getActivity().getActionBar().setTitle(getString(R.string.cydia));
                }
            });
        }
        return view;
    }
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getBaseContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo i = cm.getActiveNetworkInfo();
        if ((i == null) || (!i.isConnected())) {
            return false;
        } else {
            return true;
        }
    }
    private class webClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }
}