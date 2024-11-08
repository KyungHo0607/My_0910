package com.example.my_0910;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 3000; // 3초 대기 후 화면 전환
    private static final String PREFS_NAME = "login_prefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 일정 시간 후 로그인 상태 확인하여 적절한 화면으로 이동
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLoggedIn()) {
                    // 로그인 상태가 유지된 경우 메인 화면으로 이동
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    // 로그인 상태가 아닌 경우 로그인 화면으로 이동
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                finish(); // SplashActivity 종료
            }
        }, SPLASH_TIME_OUT);
    }

    // 로그인 상태 확인 메서드
    private boolean isLoggedIn() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }
}
