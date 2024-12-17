package com.example.valorant.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.valorant.R;
import com.example.valorant.models.Ability;

import java.util.List;

public class AbilitiesAdapter extends RecyclerView.Adapter<AbilitiesAdapter.AbilityViewHolder> {

    private final Context context;
    private final List<Ability> abilities;

    public AbilitiesAdapter(Context context, List<Ability> abilities) {
        this.context = context;
        this.abilities = abilities;
    }

    @NonNull
    @Override
    public AbilityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ability, parent, false);
        return new AbilityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AbilityViewHolder holder, int position) {
        Ability ability = abilities.get(position);
        holder.abilityNameTextView.setText(ability.getDisplayName());
        holder.abilityDescriptionTextView.setText(ability.getDescription());

        // Apply fade-in animation for each item in RecyclerView
        Animation fadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        holder.itemView.startAnimation(fadeIn);

        Glide.with(context)
                .load(ability.getDisplayIcon())
                .into(holder.abilityIconImageView);
    }

    @Override
    public int getItemCount() {
        return abilities.size();
    }

    static class AbilityViewHolder extends RecyclerView.ViewHolder {
        ImageView abilityIconImageView;
        TextView abilityNameTextView;
        TextView abilityDescriptionTextView;

        public AbilityViewHolder(@NonNull View itemView) {
            super(itemView);
            abilityIconImageView = itemView.findViewById(R.id.abilityIconImageView);
            abilityNameTextView = itemView.findViewById(R.id.abilityNameTextView);
            abilityDescriptionTextView = itemView.findViewById(R.id.abilityDescriptionTextView);
        }
    }
}
