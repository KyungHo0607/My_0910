package com.example.my_0910;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ReservationStatusActivity extends AppCompatActivity {

    private TextView reservationInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_status);

        reservationInfoTextView = findViewById(R.id.reservationInfoTextView);

        // 현재는 예약 정보가 없으므로 기본 메시지를 설정
        reservationInfoTextView.setText("예약된 내용이 없습니다.");
    }
}
