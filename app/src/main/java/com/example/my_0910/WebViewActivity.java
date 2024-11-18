package com.example.my_0910;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view); // XML 레이아웃을 설정

        // WebView 초기화 및 설정
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient()); // 외부 브라우저가 아닌 WebView에서 열기

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // 자바스크립트 활성화
        webSettings.setUseWideViewPort(true); // 뷰포트를 화면 크기에 맞추기
        webSettings.setLoadWithOverviewMode(true); // 컨텐츠를 화면에 맞게 조정
        webSettings.setSupportZoom(true); // 줌 기능 활성화
        webSettings.setBuiltInZoomControls(true); // 줌 컨트롤 활성화
        webSettings.setDisplayZoomControls(false); // 줌 컨트롤러를 화면에 표시하지 않음
        webSettings.setDomStorageEnabled(true); // 로컬 스토리지 사용을 허용

        // User-Agent를 모바일 환경으로 설정
        webSettings.setUserAgentString("Mozilla/5.0 (Linux; Android 10; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Mobile Safari/537.36");

        // 인텐트로부터 URL을 받아와서 로드
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        if (url != null) {
            webView.loadUrl(url); // WebView에 URL 로드
        }
    }
}
