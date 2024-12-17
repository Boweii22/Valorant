package com.example.valorant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.valorant.R;
import com.example.valorant.models.Agent;

import java.util.List;
public class CharacterAdapter {
//public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
//
//    private List<Agent> characters;
//    private Context context;
//
//    public CharacterAdapter(List<Agent> characters, Context context) {
//        this.characters = characters;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_character, parent, false);
//        return new CharacterViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
//        Agent agent = characters.get(position);
//
//        holder.nameTextView.setText(agent.getName());
//        holder.descriptionTextView.setText(agent.getDescription());
//        holder.roleTextView.setText(agent.getRole().getDisplayName());
//
//        // Load agent image using Glide (ensure the image URL exists in your Agent class)
//        Glide.with(context)
//                .load(agent.getFullPortrait()) // Make sure the "fullPortrait" URL is in the Agent class
//                .placeholder(R.drawable.placeholder_image) // Placeholder image if loading fails
//                .into(holder.imageView);
//    }
//
//    @Override
//    public int getItemCount() {
//        return characters.size();
//    }
//
//    public void setCharacters(List<Agent> characters) {
//        this.characters = characters;
//        notifyDataSetChanged();
//    }
//
//    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
//
//        TextView nameTextView, descriptionTextView, roleTextView;
//        ImageView imageView;
//
//        public CharacterViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            nameTextView = itemView.findViewById(R.id.nameTextView);
//            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
//            roleTextView = itemView.findViewById(R.id.roleTextView);
//            imageView = itemView.findViewById(R.id.imageView);
//        }
//    }
}
