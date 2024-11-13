package com.example.my_0910;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
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

        // 툴바 설정
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("체어맨");
        }

        // "메인페이지" 하단 버튼 클릭 시 MainActivity로 이동
        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> {
            // 이미 MainActivity에 있으므로 특별한 동작 필요 없음
        });

        // "회원정보" 하단 버튼 클릭 시 CommunityActivity로 이동
        Button memberButton = findViewById(R.id.memberButton);
        memberButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CommunityActivity.class);
            startActivity(intent);
        });

        // "공지사항" 하단 버튼 클릭 시 NoticeActivity로 이동
        Button noticeButton = findViewById(R.id.noticeButton);
        noticeButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NoticeActivity.class);
            startActivity(intent);
        });

        // "챗봇" 하단 버튼 클릭 시 ChatbotActivity로 이동
        Button chatbotButton = findViewById(R.id.chatbotButton);
        chatbotButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChatbotActivity.class);
            startActivity(intent);
        });
    }

    // 메뉴 생성
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 메뉴를 inflate하여 툴바에 추가
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // 메뉴 항목 선택 처리
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_logout) {
            showLogoutConfirmationDialog(); // 로그아웃 대화상자 표시
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // 로그아웃 여부를 묻는 대화상자 표시
    private void showLogoutConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("로그아웃")
                .setMessage("로그아웃하시겠습니까?")
                .setPositiveButton("예", (dialog, which) -> logout())
                .setNegativeButton("아니오", (dialog, which) -> dialog.dismiss())
                .show();
    }

    // 로그아웃 처리
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

    // 물리적 뒤로 가기 버튼을 누를 때 앱 종료 여부 묻기
    @Override
    public void onBackPressed() {
        showExitConfirmationDialog();
    }

    // 앱 종료 여부를 묻는 대화상자 표시
    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("앱 종료")
                .setMessage("종료하시겠습니까?")
                .setPositiveButton("예", (dialog, which) -> finish())
                .setNegativeButton("아니오", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
