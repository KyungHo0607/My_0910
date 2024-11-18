package com.example.my_0910;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.Button;

public class FindLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_location);

        // 툴바 설정
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 툴바 제목 설정
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("기관 찾기");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // 뒤로가기 버튼 클릭 이벤트 처리
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 현재 액티비티 종료
            }
        });

        // 버튼 클릭 시 웹뷰로 URL 전달
        Button btnHscity = findViewById(R.id.btn_hscity);
        btnHscity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindLocationActivity.this, WebViewActivity.class);
                intent.putExtra("url", "https://www.hscity.go.kr/town/dept/BD_selectDeptCntnts.do?q_cntntsTy=directions&q_deptCode=55300620000");
                startActivity(intent);
            }
        });

    }
}
