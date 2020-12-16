package com.atoproduction.ls13webview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView refresh;
    private ImageView next;
    private ImageView previous;
    private WebView mWebView;
    private TextView input;
    private ContentLoadingProgressBar mProgressBar;
    private String url = "https://www.24h.com.vn/bong-da/video-man-city-west-brom-hang-cong-vo-duyen-toi-do-bat-dac-di-c48a1208617.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refresh = findViewById(R.id.button_refresh_url);
        next = findViewById(R.id.button_next);
        previous = findViewById(R.id.button_previous);
        mWebView = findViewById(R.id.webview);
        input = findViewById(R.id.text_url);
        mProgressBar = findViewById(R.id.progress_loading);


        input.setText(url);

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                super.onProgressChanged(view, progress);
                mProgressBar.setProgress(progress);
                if (progress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.d("thinhvh", "onPageStarted: ");
                MainActivity.this.url = url;
                input.setText(url);

                if (mWebView.canGoBack()) {
                    previous.setImageDrawable(getResources().getDrawable(R.drawable.ic_enable_back));
                } else {
                    previous.setImageDrawable(getResources().getDrawable(R.drawable.ic_disable_back));
                }
                if (mWebView.canGoForward()) {
                    next.setImageDrawable(getResources().getDrawable(R.drawable.ic_enable_forward));
                } else {
                    next.setImageDrawable(getResources().getDrawable(R.drawable.ic_disable_forward));
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d("thinhvh", "onPageFinished: ");
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Log.d("thinhvh", "onPageFinished: ");
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        mWebView.loadUrl(url);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWebView.canGoForward()) {
                    mWebView.goForward();
                }
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWebView.canGoBack()) {
                    mWebView.goBack();
                }


            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.reload();
            }
        });
    }

}
