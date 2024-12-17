package com.example.valorant;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.valorant.models.Agent;
import com.example.valorant.models.Character;

public class DetailActivity extends AppCompatActivity {

    private TextView nameTextView, descriptionTextView, roleTextView;
    private ImageView characterImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//        nameTextView = findViewById(R.id.nameTextView);
//        descriptionTextView = findViewById(R.id.descriptionTextView);
//        roleTextView = findViewById(R.id.roleTextView);
//        characterImageView = findViewById(R.id.characterImageView);
//
//        Agent agent = (Agent) getIntent().getSerializableExtra("agent");
//
//        if (agent != null) {
//            nameTextView.setText(agent.getDisplayName());
//            descriptionTextView.setText(agent.getDescription());
//            roleTextView.setText(agent.getRole());
//            Glide.with(this).load(agent.getFullPortrait()).into(characterImageView);
//        }
    }
}
