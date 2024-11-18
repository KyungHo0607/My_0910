package com.example.my_0910;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder> {

    private Context context;
    private List<OnboardingItem> onboardingItems;

    public OnboardingAdapter(Context context, List<OnboardingItem> onboardingItems) {
        this.context = context;
        this.onboardingItems = onboardingItems;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.onboarding_page, parent, false);
        return new OnboardingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        OnboardingItem item = onboardingItems.get(position);
        holder.imageView.setImageResource(item.getImageResId());
        holder.titleTextView.setText(item.getTitle());
        holder.descriptionTextView.setText(item.getContent());
    }

    @Override
    public int getItemCount() {
        return onboardingItems.size();
    }

    static class OnboardingViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;

        OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.onboarding_image);
            titleTextView = itemView.findViewById(R.id.onboarding_title);
            descriptionTextView = itemView.findViewById(R.id.onboarding_description);
        }
    }
}
