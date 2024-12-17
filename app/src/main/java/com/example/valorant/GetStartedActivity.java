package com.example.valorant;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatButton;

public class GetStartedActivity extends AppCompatActivity {

    private static final String FULL_TEXT = "We are \nValorant...";
    private static final long TYPING_DELAY = 200; // Typing delay in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

//        // Make the status bar transparent
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().getDecorView().setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//            getWindow().setStatusBarColor(android.graphics.Color.TRANSPARENT);
//        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Initialize Views
        TextView valorantText = findViewById(R.id.valorant_text);
        AppCompatButton getStartedButton = findViewById(R.id.getStartedBtn);


        // Start the typing effect
        startTypingEffect(valorantText, FULL_TEXT);

        // Load Animations
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);


        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        // Apply Animations
        valorantText.startAnimation(fadeIn);
//        valorantIcon.startAnimation(scaleUp);
        getStartedButton.startAnimation(bounce);
    }

    private void startTypingEffect(TextView textView, String text) {
        Handler handler = new Handler();
        StringBuilder currentText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            final int index = i;
            handler.postDelayed(() -> {
                currentText.append(text.charAt(index));
                textView.setText(currentText.toString());
            }, TYPING_DELAY * i);
        }
    }

    private void startTypingEffectWithCursor(TextView textView, String text) {
        Handler handler = new Handler();
        StringBuilder currentText = new StringBuilder();
        Runnable cursorRunnable = new Runnable() {
            boolean showCursor = true;

            @Override
            public void run() {
                if (showCursor) {
                    textView.setText(currentText.toString() + " _");
                } else {
                    textView.setText(currentText.toString());
                }
                showCursor = !showCursor;
                handler.postDelayed(this, 300); // Blink every 500ms
            }
        };

        handler.post(cursorRunnable);

        for (int i = 0; i < text.length(); i++) {
            final int index = i;
            handler.postDelayed(() -> {
                currentText.append(text.charAt(index));
            }, TYPING_DELAY * i);
        }
    }

}