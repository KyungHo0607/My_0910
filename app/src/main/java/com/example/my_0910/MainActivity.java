package com.example.my_0910;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "login_prefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 뒤로가기 버튼 콜백 설정
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showExitConfirmationDialog();
            }
        });

        setupToolbar();
        setupTopButtons();
        setupBottomNavigation();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로 가기 버튼 활성화
            getSupportActionBar().setTitle("체어맨");
        }
    }

    private void setupTopButtons() {
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(v -> navigateTo(ReservationActivity.class));

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(v -> navigateTo(FindLocationActivity.class));

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(v -> navigateTo(ReservationStatusActivity.class));
    }

    private void setupBottomNavigation() {
        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> {
            // 현재 MainActivity이므로 동작 없음
        });

        Button memberButton = findViewById(R.id.memberButton);
        memberButton.setOnClickListener(v -> navigateTo(CommunityActivity.class));

        Button noticeButton = findViewById(R.id.noticeButton);
        noticeButton.setOnClickListener(v -> navigateTo(NoticeActivity.class));

        Button chatbotButton = findViewById(R.id.chatbotButton);
        chatbotButton.setOnClickListener(v -> navigateTo(ChatbotActivity.class));
    }

    private void navigateTo(Class<?> targetActivity) {
        Intent intent = new Intent(MainActivity.this, targetActivity);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu); // 메뉴를 툴바에 추가
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) { // 툴바의 뒤로가기 버튼 ID
            showExitConfirmationDialog(); // 앱 종료 확인 대화상자 표시
            return true;
        } else if (id == R.id.menu_logout) { // 로그아웃 메뉴
            showLogoutConfirmationDialog(); // 로그아웃 대화상자
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showLogoutConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("로그아웃")
                .setMessage("로그아웃하시겠습니까?")
                .setPositiveButton("예", (dialog, which) -> logout())
                .setNegativeButton("아니오", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void logout() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, false);
        editor.apply();

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        Toast.makeText(MainActivity.this, "로그아웃되었습니다.", Toast.LENGTH_SHORT).show();
    }

    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("앱 종료")
                .setMessage("앱을 종료하시겠습니까?")
                .setPositiveButton("예", (dialog, which) -> finish()) // 앱 종료
                .setNegativeButton("아니오", (dialog, which) -> dialog.dismiss()) // 대화상자 닫기
                .show();
    }
}
