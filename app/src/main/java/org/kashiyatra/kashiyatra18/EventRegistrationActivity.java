package org.kashiyatra.kashiyatra18;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class EventRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_registration);

        final WebView containerWbVw = findViewById(R.id.event_reg_page);
        WebSettings webSettings = containerWbVw.getSettings();
        webSettings.setJavaScriptEnabled(true);
        containerWbVw.setWebChromeClient(new WebChromeClient());
    }
}
