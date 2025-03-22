package com.example.recycleview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.AnimatorSet;

public class splashActivity extends AppCompatActivity {

    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.logo);

        // Create individual animations
        ObjectAnimator rotate = ObjectAnimator.ofFloat(logo, "rotation", 0f, 360f);
        rotate.setDuration(2000); // 2 seconds

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(logo, "scaleX", 1f, 0.5f);
        scaleX.setDuration(3000); // 3 seconds

        ObjectAnimator scaleY = ObjectAnimator.ofFloat(logo, "scaleY", 1f, 0.5f);
        scaleY.setDuration(3000); // 3 seconds

        ObjectAnimator translateY = ObjectAnimator.ofFloat(logo, "translationY", 0f, 1000f);
        translateY.setDuration(2000); // 2 seconds

        ObjectAnimator alpha = ObjectAnimator.ofFloat(logo, "alpha", 1f, 0f);
        alpha.setDuration(6000); // 6 seconds

        // Create an AnimatorSet to chain the animations together sequentially
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(rotate, scaleX, scaleY, translateY, alpha); // Run animations sequentially
        animatorSet.start();

        // Delay the transition to MainActivity after the animations finish
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Transition to MainActivity after splash
                Intent intent = new Intent(splashActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Ensure splash activity is closed
            }
        }, 6000); // Wait until all animations are complete (last animation takes 6 seconds)
    }
}
