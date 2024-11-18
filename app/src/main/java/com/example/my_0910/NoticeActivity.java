package com.example.my_0910;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NoticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        // 툴바 설정
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로 가기 버튼 활성화
            getSupportActionBar().setTitle("공지사항"); // 툴바 제목 설정
        }

        // 하단 메뉴 버튼 클릭 리스너 설정
        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> {
            // MainActivity로 이동
            startActivity(new Intent(NoticeActivity.this, MainActivity.class));
        });

        Button memberButton = findViewById(R.id.memberButton);
        memberButton.setOnClickListener(v -> {
            // CommunityActivity로 이동
            startActivity(new Intent(NoticeActivity.this, CommunityActivity.class));
        });

        Button chatbotButton = findViewById(R.id.chatbotButton);
        chatbotButton.setOnClickListener(v -> {
            // ChatbotActivity로 이동
            startActivity(new Intent(NoticeActivity.this, ChatbotActivity.class));
        });
    }

    // 뒤로 가기 버튼 동작
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
