package com.example.my_0910;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // "a 기관 예약하기" 버튼 클릭 시 ReservationActivity로 이동
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReservationActivity.class);
                startActivity(intent);
            }
        });

        // "나의 예약 현황" 버튼 클릭 시 ReservationStatusActivity로 이동
        Button reservationStatusButton = findViewById(R.id.buttonMyReservationStatus);
        reservationStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReservationStatusActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // 뒤로 가기 버튼을 누르면 종료 여부를 묻는 대화상자 표시
        new AlertDialog.Builder(this)
                .setTitle("앱 종료")
                .setMessage("종료하시겠습니까?")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // '예'를 누르면 앱 종료
                        finish();
                    }
                })
                .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // '아니오'를 누르면 대화상자 닫기
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
