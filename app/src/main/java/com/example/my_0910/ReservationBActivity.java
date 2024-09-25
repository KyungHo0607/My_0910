package com.example.my_0910;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ReservationBActivity extends AppCompatActivity {

    private TextView reservationDateTextView;
    private TextView returnDateTextView;
    private CheckBox wheelchairCheckBox1;
    private CheckBox wheelchairCheckBox2;
    private Button reserveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_b);

        reservationDateTextView = findViewById(R.id.reservationDateTextView);
        returnDateTextView = findViewById(R.id.returnDateTextView);
        ImageView reservationCalendarIcon = findViewById(R.id.reservationCalendarIcon);
        ImageView returnCalendarIcon = findViewById(R.id.returnCalendarIcon);
        wheelchairCheckBox1 = findViewById(R.id.wheelchairCheckBox1);
        wheelchairCheckBox2 = findViewById(R.id.wheelchairCheckBox2);
        reserveButton = findViewById(R.id.reserveButton);

        // 예약 희망 일자 선택
        reservationCalendarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(reservationDateTextView);
            }
        });

        // 반납 희망 일자 선택
        returnCalendarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(returnDateTextView);
            }
        });

        // 1번 휠체어 체크박스 클릭 이벤트 처리
        wheelchairCheckBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wheelchairCheckBox1.isChecked()) {
                    // 2번 휠체어 체크박스 해제
                    wheelchairCheckBox2.setChecked(false);
                }
            }
        });

        // 2번 휠체어 체크박스 클릭 이벤트 처리
        wheelchairCheckBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wheelchairCheckBox2.isChecked()) {
                    // 1번 휠체어 체크박스 해제
                    wheelchairCheckBox1.setChecked(false);
                }
            }
        });

        // 예약하기 버튼 클릭 시 처리
        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 예약 성공 메시지 표시
                Toast.makeText(ReservationBActivity.this, "예약 신청이 되었습니다", Toast.LENGTH_SHORT).show();

                // MainActivity로 돌아가기 위한 인텐트 설정
                Intent intent = new Intent(ReservationBActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 스택에 있는 이전 액티비티를 지우고 이동
                startActivity(intent);
                finish(); // 현재 Activity 종료
            }
        });
    }

    // 날짜 선택기 다이얼로그 표시
    private void showDatePickerDialog(final TextView textView) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                ReservationBActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = year + "/" + (month + 1) + "/" + dayOfMonth;
                        textView.setText(selectedDate);
                    }
                },
                year, month, day);
        datePickerDialog.show();
    }
}
