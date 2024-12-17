package com.example.valorant;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Make the status bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(android.graphics.Color.TRANSPARENT);
        }

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        VideoView videoView = findViewById(R.id.splashVideoView);

        // Set the video path from the raw folder
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.v_splash);
        videoView.setVideoURI(videoUri);

        // Start the video
        videoView.start();

        // Listen for the video completion
        videoView.setOnCompletionListener(mediaPlayer -> {
            // Navigate to the next screen
            Intent intent = new Intent(SplashActivity.this, GetStartedActivity.class);
            startActivity(intent);
            finish(); // Close the SplashActivity
        });
    }
}
