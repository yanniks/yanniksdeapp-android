package de.yanniks.app;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webviewfragment extends Fragment {
    WebView mywebview;
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
        Bundle bundle = getArguments();
        String url = bundle.getString("url");
        final String title = bundle.getString("title");
        init(url);
        View view = inflater
                .inflate(R.layout.webview, container, false);
        if (curURL != null) {
            WebView mywebview = (WebView) view.findViewById(R.id.webview);
            mywebview.setWebViewClient(new webClient());
            if (!isOnline() == true) {
                mywebview.loadUrl("file:///android_asset/error-" + getString(R.string.lang) + ".html");
            } else {
                mywebview.loadUrl(curURL);
                mywebview.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress)
                {
                    getActivity().setTitle(getString(R.string.loading));
                    getActivity().setProgress(progress * 100);
                    if(progress == 100)
                        getActivity().getActionBar().setTitle(title);
                }
            });
        }
        }
        return view;
    }
    public boolean WebViewGoBack() {
        if(mywebview.canGoBack()){
            mywebview.goBack();
            return true;
        }
        return false;
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