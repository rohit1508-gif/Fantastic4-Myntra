package com.example.myntrahackathon.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myntrahackathon.ModalClasses.LeaderboardUser;
import com.example.myntrahackathon.R;

import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {

    List<LeaderboardUser> users;

    public LeaderboardAdapter(List<LeaderboardUser> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public LeaderboardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_leaderboard_user,parent,false);
        return new LeaderboardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardAdapter.ViewHolder holder, int position) {
        LeaderboardUser user = users.get(position);
        holder.tvName.setText(user.getName());
        holder.tvScore.setText(Integer.toString(user.getScore()));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvScore = itemView.findViewById(R.id.tvScore);
        }
    }
}
