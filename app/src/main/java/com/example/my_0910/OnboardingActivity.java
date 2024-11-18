package com.example.my_0910;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private List<OnboardingItem> onboardingItems;
    private int currentPage = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isFirstTimeLaunch()) {
            setContentView(R.layout.activity_onboarding);
            viewPager = findViewById(R.id.viewPager);
            setupOnboardingItems();

            // OnboardingAdapter 생성 시 `onboardingItems` 리스트 전달
            OnboardingAdapter adapter = new OnboardingAdapter(this, onboardingItems);
            viewPager.setAdapter(adapter);

            findViewById(R.id.nextButton).setOnClickListener(view -> {
                if (currentPage < onboardingItems.size() - 1) {
                    viewPager.setCurrentItem(++currentPage);
                } else {
                    launchMainActivity();
                }
            });

            // 페이지 변경 리스너 추가 (현재 페이지 업데이트)
            viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    currentPage = position;
                }
            });

        } else {
            launchMainActivity();
        }
    }

    private void setupOnboardingItems() {
        onboardingItems = new ArrayList<>();
        onboardingItems.add(new OnboardingItem("앱 소개 및 주요 기능", "이 앱은 휠체어 대여 서비스를 제공합니다.", R.drawable.ic_app_intro));
        onboardingItems.add(new OnboardingItem("회원 가입 및 로그인 안내", "서비스를 이용하려면 회원가입 또는 로그인이 필요합니다.", R.drawable.ic_signup));
        onboardingItems.add(new OnboardingItem("예약 절차 안내", "대여를 희망하는 날짜를 선택하고 신청하세요.", R.drawable.ic_reserve));
        onboardingItems.add(new OnboardingItem("반납 절차와 주의사항", "사용 후에는 지정된 장소에 반납해주세요.", R.drawable.ic_return));
    }

    private boolean isFirstTimeLaunch() {
        SharedPreferences preferences = getSharedPreferences("onboarding_prefs", MODE_PRIVATE);
        return preferences.getBoolean("isFirstLaunch", true);
    }

    private void launchMainActivity() {
        SharedPreferences.Editor editor = getSharedPreferences("onboarding_prefs", MODE_PRIVATE).edit();
        editor.putBoolean("isFirstLaunch", false);
        editor.apply();
        startActivity(new Intent(OnboardingActivity.this, LoginActivity.class));
        finish();
    }
}
