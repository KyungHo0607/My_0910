package com.example.my_0910;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CommunityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
    }

    // 뒤로 가기 버튼 동작
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
