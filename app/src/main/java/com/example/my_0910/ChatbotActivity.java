package com.example.my_0910;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ChatbotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot); // 새로 만든 레이아웃을 설정

        // 툴바 설정
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로 가기 버튼 활성화
            getSupportActionBar().setTitle("챗봇"); // 툴바 제목 설정
        }

        // 하단 메뉴 버튼 클릭 리스너
        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(ChatbotActivity.this, MainActivity.class);
            startActivity(intent);
        });

        Button memberButton = findViewById(R.id.memberButton);
        memberButton.setOnClickListener(v -> {
            Intent intent = new Intent(ChatbotActivity.this, CommunityActivity.class);
            startActivity(intent);
        });

        Button noticeButton = findViewById(R.id.noticeButton);
        noticeButton.setOnClickListener(v -> {
            Intent intent = new Intent(ChatbotActivity.this, NoticeActivity.class);
            startActivity(intent);
        });

        Button chatbotButton = findViewById(R.id.chatbotButton);
        chatbotButton.setOnClickListener(v -> {
            // 이미 챗봇 화면이므로 특별한 동작 필요 없음
        });
    }

    // 뒤로 가기 버튼 동작
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed(); // 뒤로 가기 동작
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // 뒤로 가기 버튼을 눌렀을 때 처리
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
