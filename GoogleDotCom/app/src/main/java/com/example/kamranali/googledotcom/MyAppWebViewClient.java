package com.example.kamranali.googledotcom;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by Kamran ALi on 11/19/2016.
 */
public class MyAppWebViewClient extends WebViewClient {
    private Activity activity;

    public MyAppWebViewClient(MainActivity mainActivity) {
        this.activity = mainActivity;
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        showToast("Error While Loading Page...!");
        view.loadUrl("file:///android_asset/index.html");
    }

    private void showToast(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }
}
