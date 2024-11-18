package com.example.my_0910;

public class OnboardingItem {
    private String title;
    private String content;
    private int imageResId;

    public OnboardingItem(String title, String content, int imageResId) {
        this.title = title;
        this.content = content;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getImageResId() {
        return imageResId;
    }
}
