package com.logix.githubfetcher.ui.main;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.logix.githubfetcher.R;
import com.logix.githubfetcher.models.ApiResponse;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommitsAdapter extends RecyclerView.Adapter<CommitsAdapter.CommitViewHolder> {

    private ArrayList<ApiResponse> commitsList = new ArrayList<>();

    @NonNull
    @Override
    public CommitsAdapter.CommitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new CommitViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.commit_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommitsAdapter.CommitViewHolder holder, int position) {
        Log.d("Test", commitsList.get(position).getCommit().getCommitter().getName());
        holder.commitAuthorTextView.setText(commitsList.get(position).getCommit().getCommitter().getName());
        holder.commitMessageTextView.setText(commitsList.get(position).getCommit().getMessage());
        holder.commitHashTextView.setText(commitsList.get(position).getCommit().getTree().getSha());
        ImageLoader.getInstance().displayImage(commitsList.get(position).getCommitter().getAvatarUrl(), holder.commitAutherProfileImage);
    }

    public void setList(ArrayList<ApiResponse> commitsList){
        this.commitsList = commitsList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return commitsList.size();
    }

    public class CommitViewHolder extends RecyclerView.ViewHolder{
        TextView commitAuthorTextView, commitMessageTextView, commitHashTextView;
        ImageView commitAutherProfileImage;

        public CommitViewHolder(@NonNull View itemView) {
            super(itemView);
            commitAuthorTextView = itemView.findViewById(R.id.commitAuthorTextView);
            commitMessageTextView = itemView.findViewById(R.id.commitMessageTextView);
            commitHashTextView = itemView.findViewById(R.id.commitHashTextView);
            commitAutherProfileImage = itemView.findViewById(R.id.committerProfileImage);
        }
    }
}
