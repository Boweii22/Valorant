package com.example.valorant.adapters;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.valorant.AgentDetailActivity;
import com.example.valorant.AgentSingleton;
import com.example.valorant.R;
import com.example.valorant.models.Agent;

import java.util.List;

public class AgentAdapter extends RecyclerView.Adapter<AgentAdapter.AgentViewHolder> {

    private final Context context;
    private List<Agent> agentList;

    public AgentAdapter(Context context, List<Agent> agentList) {
        this.context = context;
        this.agentList = agentList;
    }

    @NonNull
    @Override
    public AgentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.agent_item, parent, false);
        return new AgentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgentViewHolder holder, int position) {
        Agent agent = agentList.get(position);
        holder.nameTextView.setText(agent.getName());
        holder.developerNameTextView.setText(agent.getDeveloperName());

        AgentSingleton.getInstance().setAgent(agent);

        // Get the gradient colors from the agent object
        String[] gradientColors = agent.getBackgroundGradientColors();

        // Dynamically set the gradient background
        applyDynamicGradient(holder.itemView.findViewById(R.id.characterCard), gradientColors);

        Log.d("AgentAdapter", "Agent Name: " + agent.getName());
//        Log.d("AgentAdapter","Full Portrait: " + agent.getFullPortrait());

        Glide.with(context)
                .load(agent.getDisplayIcon())
                .into(holder.imageView);

        // Set transition animation for each agent item
        holder.itemView.setAnimation(android.view.animation.AnimationUtils.loadAnimation(context, R.anim.slide_in_left));


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, AgentDetailActivity.class);
            intent.putExtra("agent", agent); // Passing Parcelable
            Log.d("AgentAdapter", "Display Icon: " + agent.getDisplayIcon());
            Log.d("AgentAdapter", "Full Portrait: " + agent.getFullPortrait());
            context.startActivity(intent);
        });
    }

    private void applyDynamicGradient(View view, String[] hexColors) {
        int[] colors = new int[hexColors.length];
        for (int i = 0; i < hexColors.length; i++) {
            // Remove alpha (last two characters) for Android compatibility
            colors[i] = Color.parseColor("#" + hexColors[i].substring(0, 6));
        }

        GradientDrawable gradientDrawable = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, colors
        );
        float[] cornerRadii = new float[] {
                0f, 0f, // Top-left
                30f, 30f, // Top-right
                0f, 0f, // Bottom-right
                30f, 30f  // Bottom-left
        };
        gradientDrawable.setCornerRadii(cornerRadii); // Optional: Rounded
        view.setBackground(gradientDrawable);
    }

    @Override
    public int getItemCount() {
        return agentList.size();
    }

    public void updateList(List<Agent> newList) {
        this.agentList = newList;
        notifyDataSetChanged();
    }

    static class AgentViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, developerNameTextView;
        ImageView imageView;

        public AgentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.agentNameTextView);
            developerNameTextView = itemView.findViewById(R.id.agentDeveloperNameTextView);
            imageView = itemView.findViewById(R.id.agentImageView);
        }
    }
}
