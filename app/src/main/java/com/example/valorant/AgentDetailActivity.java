package com.example.valorant;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.valorant.adapters.AbilitiesAdapter;
import com.example.valorant.models.Agent;


import androidx.annotation.Nullable;
import com.google.gson.Gson;

import java.util.Random;

public class AgentDetailActivity extends AppCompatActivity {

    private ImageView agentImageView, backgroundAgentImage;
    private TextView agentNameTextView, agentRoleTextView, agentBiography;
    private RecyclerView abilitiesRecyclerView;
    private LinearLayout agent_background;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_detail);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent = getIntent();
        String displayName = intent.getStringExtra("role");

        // Initialize views
        agentImageView = findViewById(R.id.agentImageViewl);
        backgroundAgentImage = findViewById(R.id.agentImageView1);
        agentNameTextView = findViewById(R.id.nameTextView);
        agentBiography = findViewById(R.id.descriptionTextView);
        abilitiesRecyclerView = findViewById(R.id.abilitiesRecyclerView);
        agentRoleTextView = findViewById(R.id.roleTextView);
        agent_background = findViewById(R.id.agent_background);

        // Apply animations
        applyAnimations();


        Agent agents = AgentSingleton.getInstance().getAgent();



        // Get the passed Agent object
        Agent agent = getIntent().getParcelableExtra("agent");
        if(agents.getRole()!= null){
            Log.d("AgentDetailActivity", "Null: " + false);
            Log.d("Roles","The role name: " + agents.getRole().getDisplayName() );
            agentRoleTextView.setText(agents.getRole().getDisplayName());
        }else {
            Log.d("AgentDetailActivity", "Null: " + true);
        }
        if (agent != null) {
            Log.d("AgentDetailActivity", "Full Portrait URL: " + agent.getFullPortrait());
            Log.d("AgentDetailActivity", "Display Icon URL: " + agent.getDisplayIcon());
//            Log.d("AgentDetailActivity","Role: " + agent.getRole().getDisplayName());

            Log.d("AgentData", new Gson().toJson(agent));
            System.out.println(new Gson().toJson(agent).toString());

            // Get the gradient colors from the agent object
            String[] gradientColors = agent.getBackgroundGradientColors();

            // Dynamically set the gradient background
            applyRandomColorBackground(agent_background, gradientColors);

            // Set agent details
            agentNameTextView.setText(agent.getName());
            agentBiography.setText(agent.getDescription());
            Log.d("AgentDetailActivity","Agent Name" + agentNameTextView.getText().toString());
//            agentRoleTextView.setText(agent.getRole().getDisplayName());

            Glide.get(this).clearMemory();  // Clear memory cache
            Glide.with(this)
                    .load(agent.getFullPortraitV2())
                    .skipMemoryCache(true) // Skip memory cache
                    .diskCacheStrategy(DiskCacheStrategy.NONE) // Skip disk cache
                    .into(agentImageView);


            // Re-load the image

//
            Glide.with(this)
                    .load(agent.getFullPortraitV2())
                    .into(backgroundAgentImage);

//            Log.d("AgentDetailActivity","Full portrait: " + agent.getFullPortrait());

            // Set up RecyclerView for abilities
            abilitiesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            AbilitiesAdapter abilityAdapter = new AbilitiesAdapter(this, agent.getAbilities());
            abilitiesRecyclerView.setAdapter(abilityAdapter);
        }


    }

    private void applyAnimations() {
        // Fade-in animation for name and role text
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        agentNameTextView.startAnimation(fadeIn);
        agentRoleTextView.startAnimation(fadeIn);

        // Fade-in animation for description text
        agentBiography.startAnimation(fadeIn);

        // Scale animation for agent's image
        Animation scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        agentImageView.startAnimation(scaleUp);

        // Stop the scale animation after 1.5 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Stop the animation after 1.5 seconds
                agentImageView.clearAnimation();
                // Optional: reset to original size
                agentImageView.setScaleX(1.0f);
                agentImageView.setScaleY(1.0f);
            }
        }, 2000); // Time in milliseconds (1.5 seconds)

        // Slide-in from bottom animation for abilities RecyclerView
        Animation slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in_bottom);
        abilitiesRecyclerView.startAnimation(slideIn);
    }

    private void applyRandomColorBackground(View view, String[] hexColors) {
        // Ensure the hexColors array is not empty
        if (hexColors == null || hexColors.length == 0) {
            throw new IllegalArgumentException("hexColors array must contain at least one color");
        }

        // Pick a random color from the array
        int randomIndex = new Random().nextInt(hexColors.length);
        String randomColorHex = hexColors[randomIndex];

        // Add transparency (e.g., 50% transparency with alpha "80")
        String transparentColorHex = "#B3" + randomColorHex.substring(0, 6);

        // Parse the color
        int color = Color.parseColor(transparentColorHex);

        // Create a GradientDrawable with the selected color
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(color); // Set the transparent color
        gradientDrawable.setCornerRadii(new float[]{
                0f, 0f,      // Top-left
                0f, 0f,    // Top-right
                0f, 0f,      // Bottom-right
                0f, 0f     // Bottom-left
        }); // Set rounded corners

        // Apply the drawable as the background
        view.setBackground(gradientDrawable);
    }


}
