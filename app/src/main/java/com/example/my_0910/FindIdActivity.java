package com.example.my_0910;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class FindIdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id);
    }

    // 뒤로 가기 버튼 눌렀을 때 로그인 화면으로 돌아가기
    @Override
    public void onBackPressed() {
        finish();
    }
}
