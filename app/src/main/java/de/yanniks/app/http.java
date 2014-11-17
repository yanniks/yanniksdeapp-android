package de.yanniks.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class http extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        try {
            Uri data = getIntent().getData();
            if (data != null) {
                String yanniksdepath = data.getPath();
                if (!yanniksdepath.isEmpty()) {
                    if (yanniksdepath.contains("impressum")) {
                        Intent imprint = new Intent(this, Start.class);
                        imprint.putExtra("fragment", "imprint");
                        startActivity(imprint);
                    }else if (yanniksdepath.contains("cdporter")) {
                        Intent imprint = new Intent(this,Start.class);
                        imprint.putExtra("fragment","cdporter");
                        startActivity(imprint);
                    }else if (yanniksdepath.contains("cydia")) {
                        Intent imprint = new Intent(this,Start.class);
                        imprint.putExtra("fragment","cydia");
                        startActivity(imprint);
                    } else if (yanniksdepath.contains("cms/")) {
                        if (yanniksdepath.equals("/cms/de")  || yanniksdepath.equals("/cms/") || yanniksdepath.equals("/cms/de/") || yanniksdepath.equals("/cms/en/") || yanniksdepath.equals("/cms/en")) {
                            startActivity(new Intent(this, Start.class));
                        } else {
                            String webview1 = yanniksdepath.replace("/cms/de/", "");
                            String webview2 = webview1.replace("/cms/en/", "");
                            String webview3 = webview2.replace("/cms/", "");
                            String webview4 = webview3.replace(".html", "");
                            String webviewurl = webview4.replace(".htm", "");
                            Intent webview = new Intent(this, webview.class);
                            if (webviewurl.contains("disclaimer")) {
                                webview.putExtra("title","Disclaimer");
                            } else {
                                webview.putExtra("title", getString(R.string.webview));
                            }
                            webview.putExtra("url", webviewurl);
                            startActivity(webview);
                        }
                    } else if (yanniksdepath.contains("/google+")) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/113445355917245195373")));
                    } else if (yanniksdepath.contains("/twitter")) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/yanniksde")));
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
            finish();
        }
        finish();
	}
}
