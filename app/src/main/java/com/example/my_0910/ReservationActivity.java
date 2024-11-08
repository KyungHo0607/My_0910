package com.example.my_0910;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
// import android.widget.CheckBox; // 휠체어 체크박스 관련 불필요한 import 주석 처리
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Date;

public class ReservationActivity extends AppCompatActivity {

    private TextView reservationDateTextView;
    private TextView returnDateTextView;
    // private CheckBox wheelchairCheckBox1; // 휠체어 체크박스 주석 처리
    // private CheckBox wheelchairCheckBox2; // 휠체어 체크박스 주석 처리
    private Button reserveButton;
    private Button selectTownButton; // 읍면동 선택 버튼
    private Date reservationDate;
    private Date returnDate;
    private RadioGroup privacyConsentRadioGroup; // 동의 여부 라디오 그룹

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        // 툴바 설정
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("예약하기"); // 툴바의 제목 설정
        }

        reservationDateTextView = findViewById(R.id.reservationDateTextView);
        returnDateTextView = findViewById(R.id.returnDateTextView);
        ImageView reservationCalendarIcon = findViewById(R.id.reservationCalendarIcon);
        ImageView returnCalendarIcon = findViewById(R.id.returnCalendarIcon);
        // wheelchairCheckBox1 = findViewById(R.id.wheelchairCheckBox1); // 휠체어 체크박스 주석 처리
        // wheelchairCheckBox2 = findViewById(R.id.wheelchairCheckBox2); // 휠체어 체크박스 주석 처리
        reserveButton = findViewById(R.id.reserveButton);
        selectTownButton = findViewById(R.id.selectTownButton); // 읍면동 선택 버튼
        privacyConsentRadioGroup = findViewById(R.id.privacyConsentRadioGroup); // 개인정보 동의 여부

        // 읍면동 선택 버튼 클릭 시 대화상자 표시
        selectTownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTownSelectionDialog();
            }
        });

        // 예약 희망 일자 선택
        reservationCalendarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(reservationDateTextView, true);
            }
        });

        // 반납 희망 일자 선택
        returnCalendarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(returnDateTextView, false);
            }
        });

        /*
        // 1번 휠체어 체크박스 클릭 이벤트 처리
        wheelchairCheckBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wheelchairCheckBox1.isChecked()) {
                    wheelchairCheckBox2.setChecked(false); // 2번 휠체어 체크박스 해제
                }
            }
        });

        // 2번 휠체어 체크박스 클릭 이벤트 처리
        wheelchairCheckBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wheelchairCheckBox2.isChecked()) {
                    wheelchairCheckBox1.setChecked(false); // 1번 휠체어 체크박스 해제
                }
            }
        });
        */

        // 예약하기 버튼 클릭 시 처리
        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 개인정보 동의 여부 확인
                if (privacyConsentRadioGroup.getCheckedRadioButtonId() == R.id.radioDisagree) {
                    Toast.makeText(ReservationActivity.this, "개인정보 활용에 동의해야 예약이 가능합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 선택되지 않은 항목 검증
                StringBuilder missingSelection = new StringBuilder();
                if (selectTownButton.getText().toString().equals("읍면동 선택")) {
                    missingSelection.append("읍면동 선택\n");
                }
                if (reservationDate == null) {
                    missingSelection.append("예약희망 일자\n");
                }
                if (returnDate == null) {
                    missingSelection.append("반납희망 일자\n");
                }
                /*
                if (!wheelchairCheckBox1.isChecked() && !wheelchairCheckBox2.isChecked()) {
                    missingSelection.append("휠체어 선택\n");
                }
                */

                if (missingSelection.length() > 0) {
                    // 선택되지 않은 항목을 알림창으로 표시
                    new AlertDialog.Builder(ReservationActivity.this)
                            .setTitle("선택되지 않은 항목")
                            .setMessage("다음 항목을 선택해주세요:\n" + missingSelection.toString())
                            .setPositiveButton("확인", null)
                            .show();
                } else {
                    // 모든 항목이 선택되었을 경우 예약 처리
                    long diff = returnDate.getTime() - reservationDate.getTime();
                    long days = diff / (24 * 60 * 60 * 1000);
                    if (days > 14) {
                        Toast.makeText(ReservationActivity.this, "최대 대여 기간은 2주일입니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ReservationActivity.this, "예약 신청이 되었습니다", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ReservationActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    // 뒤로가기 버튼 기능 추가
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(ReservationActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // 날짜 선택기 다이얼로그 표시
    private void showDatePickerDialog(final TextView textView, final boolean isReservationDate) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                ReservationActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar selectedCalendar = Calendar.getInstance();
                        selectedCalendar.set(year, month, dayOfMonth);
                        Date selectedDate = selectedCalendar.getTime();
                        Date currentDate = new Date();

                        if (selectedDate.before(currentDate)) {
                            // 선택한 날짜가 현재 날짜보다 이전일 경우 알림 표시
                            Toast.makeText(ReservationActivity.this, "지나간 날짜는 선택할 수 없습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            // 선택한 날짜가 유효한 경우 설정
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
                            String dateString = dateFormat.format(selectedDate);

                            textView.setText(dateString);

                            if (isReservationDate) {
                                reservationDate = selectedDate;
                            } else {
                                returnDate = selectedDate;
                            }
                        }
                    }
                },
                year, month, day);
        datePickerDialog.show();
    }

    // 읍면동 선택 대화상자 표시
    private void showTownSelectionDialog() {
        final String[] towns = {"봉담읍"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("읍면동 선택")
                .setItems(towns, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectTownButton.setText(towns[which]);
                    }
                })
                .show();
    }
}
