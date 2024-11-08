package com.example.my_0910;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ReservationStatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_status);

        // 툴바 설정
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로 가기 버튼 활성화
            getSupportActionBar().setTitle("예약 현황"); // 제목 설정
        }
    }

    // 뒤로 가기 버튼 동작
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
