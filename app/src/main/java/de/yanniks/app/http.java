package de.yanniks.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.sliit.SplashScreenActivity;
import org.sliit.service.SharedPreferencesHelper;

import java.util.Arrays;

public class http extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        try {
            Uri data = getIntent().getData();
            if (data.equals(null)) {
            } else {
                String yanniksdepath = data.getPath();
                if (!yanniksdepath.isEmpty()) {
                    if (yanniksdepath.contains("impressum")) {
                        Intent imprint = new Intent(this,Start.class);
                        imprint.putExtra("fragment","imprint");
                        startActivity(imprint);
                    } else if (yanniksdepath.contains("cms/")) {
                        String webview1 = yanniksdepath.replace("/cms/de/", "");
                        String webview2 = webview1.replace("/cms/en/", "");
                        String webview3 = webview2.replace("/cms/", "");
                        String webview4 = webview3.replace(".html", "");
                        String webviewurl = webview4.replace(".htm", "");
                        Intent webview = new Intent(this, webview.class);
                        webview.putExtra("title", getString(R.string.webview));
                        webview.putExtra("url", webviewurl);
                        startActivity(webview);
                    } else if (yanniksdepath.contains("/appcheck")) {
                        Intent appcheck = new Intent(this,select.class);
                        appcheck.putExtra("yanniksdepath",yanniksdepath);
                        startActivity(appcheck);
                    } else if (yanniksdepath.contains("/google+")) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/113445355917245195373")));
                    } else if (yanniksdepath.contains("/twitter")) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://twitter.com/yanniksde")));
                    } else if (yanniksdepath.contains("/facebook")) {
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/132390900157794")));
                        } catch (Exception e) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/yanniks.de")));
                        }
                    } else {
                        startActivity(new Intent(this, Start.class));
                    }
                }
            }
        } catch (NullPointerException e) {
        }
        finish();
	}
}
