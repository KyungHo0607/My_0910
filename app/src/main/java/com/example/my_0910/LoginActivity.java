package com.example.my_0910;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "login_prefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    private EditText loginId;
    private EditText loginPassword;
    private Button buttonLogin;
    private Button findIdButton;
    private Button findPasswordButton;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 로그인 상태 확인
        if (isLoggedIn()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_login); // 기존 로그인 XML 레이아웃 사용

        // UI 요소 초기화
        loginId = findViewById(R.id.loginId);
        loginPassword = findViewById(R.id.loginPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        findIdButton = findViewById(R.id.findIdButton);
        findPasswordButton = findViewById(R.id.findPasswordButton);
        signUpButton = findViewById(R.id.signupButton);

        // 로그인 버튼 클릭 이벤트 설정
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그인 상태 저장
                saveLoginState(true);
                Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });

        // 아이디 찾기 버튼 클릭 이벤트
        findIdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, FindIdActivity.class));
            }
        });

        // 비밀번호 찾기 버튼 클릭 이벤트
        findPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, FindPasswordActivity.class));
            }
        });

        // 회원가입 버튼 클릭 이벤트
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
    }

    // 로그인 상태를 SharedPreferences에 저장
    private void saveLoginState(boolean isLoggedIn) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    // 로그인 상태 확인
    private boolean isLoggedIn() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    // 로그인 검증 - 항상 true 반환
    private boolean validateLogin(String username, String password) {
        return true;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("앱 종료")
                .setMessage("종료하시겠습니까?")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
