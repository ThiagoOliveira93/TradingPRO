package com.tradingpro.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.content.res.AssetManager;
import java.io.InputStream;
import java.io.IOException;
import java.util.Locale;

public class MainActivity extends Activity {
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        setContentView(webView);

        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setDatabaseEnabled(true);
        s.setAllowFileAccess(false);
        s.setAllowContentAccess(false);
        s.setBuiltInZoomControls(false);
        s.setDisplayZoomControls(false);
        s.setMediaPlaybackRequiresUserGesture(false);
        s.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new LocalAssetClient(getAssets()));
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        webView.loadUrl("https://appassets.androidplatform.net/assets/index.html");
    }

    @Override public void onBackPressed() {
        if (webView != null && webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }

    private static class LocalAssetClient extends WebViewClient {
        private final AssetManager assets;
        LocalAssetClient(AssetManager assets) { this.assets = assets; }

        @Override public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            String url = request.getUrl().toString();
            String prefix = "https://appassets.androidplatform.net/assets/";
            if (!url.startsWith(prefix)) return super.shouldInterceptRequest(view, request);
            String path = url.substring(prefix.length());
            try {
                InputStream in = assets.open(path);
                String mime = mime(path);
                return new WebResourceResponse(mime, "UTF-8", in);
            } catch (IOException ignored) {
                return super.shouldInterceptRequest(view, request);
            }
        }

        private static String mime(String p) {
            String l = p.toLowerCase(Locale.US);
            if (l.endsWith(".html")) return "text/html";
            if (l.endsWith(".js")) return "application/javascript";
            if (l.endsWith(".css")) return "text/css";
            if (l.endsWith(".png")) return "image/png";
            if (l.endsWith(".jpg") || l.endsWith(".jpeg")) return "image/jpeg";
            if (l.endsWith(".webp")) return "image/webp";
            if (l.endsWith(".json") || l.endsWith(".webmanifest")) return "application/json";
            return "application/octet-stream";
        }
    }
}
