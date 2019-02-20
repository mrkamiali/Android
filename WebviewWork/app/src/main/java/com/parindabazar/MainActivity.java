package com.parindabazar;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity implements WebViewClientClass.Showbar {
    public static String URL = "http://www.parindabazar.in/";
    private WebView webView;
    private SwipeRefreshLayout mySwipeRefreshLayout;
    private ImageView imageView;
    private AlertDialog.Builder builder;
    private boolean lastStatus = true;
    private WebViewClientClass webClient;
    private ProgressBar progress_bar_web;
    private BroadcastReceiver netReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null
                    && activeNetwork.isConnectedOrConnecting();
            showSnack(isConnected);
            lastStatus = isConnected;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            //if you want to change the color just change the hash code of transparentColor.
//            window.setStatusBarColor(ContextCompat.getColor(this, R.color.statusbarcolor));
//
//        }


        //webview initilization
        webView = (WebView) findViewById(R.id.webView);
        progress_bar_web = (ProgressBar) findViewById(R.id.progress_bar_web);
        mySwipeRefreshLayout = (SwipeRefreshLayout) this.findViewById(R.id.swipeContainer);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setVisibility(View.GONE);
        webView.setVisibility(View.GONE);
        settingUpWebView();


        //checking for the wifi connection.
        isConnected(this);

        //pull down to refresh
        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mySwipeRefreshLayout.isRefreshing()) {
                    mySwipeRefreshLayout.setRefreshing(false);
                    imageView.setVisibility(View.GONE);
                    webView.setVisibility(View.VISIBLE);
                    webView.reload();
                }
            }
        });

        //Stop local links and redirects from opening in browser instead of webview
        webClient = new WebViewClientClass(this, imageView, webView);
        webView.setWebViewClient(webClient);


        //restore the instance state on screen rotation.
        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState);
        } else {
            loadWebContent(URL);
        }
    }

    private void loadWebContent(String url) {
        webView.loadUrl("file:///android_asset/www/index.html");
        webView.setVisibility(View.VISIBLE);
    }

    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wifiInfo != null && wifiInfo.isConnected()) || (mobileInfo != null && mobileInfo.isConnected())) {
            return true;
        } else {
            lastStatus = false;
            showDialog();
            return false;
        }

    }

    public void showDialog() {
        builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Connect to INTERNET !")
                .setCancelable(false)
                .setPositiveButton("Open WIFI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void settingUpWebView() {

        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);

        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setDatabaseEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        webView.getSettings().setUserAgentString("Android WebView");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        webView.getSettings().setSupportMultipleWindows(false);
        //LayoutAlgorithms
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.getSettings().setUseWideViewPort(true);
//        webView.getSettings().setDomStorageEnabled(true);
//        webView.getSettings().setSavePassword(true);
//        webView.getSettings().setSaveFormData(true);

//        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
//        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setEnableSmoothTransition(true);
        webView.canGoBack();
        webView.setVerticalScrollBarEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setWebChromeClient(new ChromeClient());
    }

    @Override
    public void onBackPressed() {
        if (webView.copyBackForwardList().getCurrentIndex() >= 1) {
            Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_SHORT).show();
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private void showSnack(boolean isConnected) {
        if (!isConnected) {
            Toast.makeText(MainActivity.this, "Please connect to the internet", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Connected to internet", Toast.LENGTH_SHORT).show();
            if (!lastStatus) {//last = false && contd = true
                webClient.reload();
            }

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(netReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // register connection status listener
        registerReceiver(netReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        webView.reload();
    }

    @Override
    public void onstartloading() {
        progress_bar_web.setVisibility(View.VISIBLE);
    }

    @Override
    public void onstoploading() {
        progress_bar_web.setVisibility(View.INVISIBLE);

    }

    public class ChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            progress_bar_web.setProgress(newProgress);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState);
        }
    }


}

