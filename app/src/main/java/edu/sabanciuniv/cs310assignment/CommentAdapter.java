package edu.sabanciuniv.cs310assignment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>{


    private Context ctx;
    private List<Comment> data;

    public CommentAdapter(Context ctx, List<Comment> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public CommentAdapter.CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root= LayoutInflater.from(ctx).inflate(R.layout.commentsrowlayout,parent,false);

        CommentViewHolder holder = new CommentViewHolder(root);
        holder.setIsRecyclable(false);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.CommentViewHolder holder, int position) {

        holder.commenterName.setText(data.get(holder.getAdapterPosition()).getName());
        holder.commentText.setText(data.get(holder.getAdapterPosition()).getText());
        NewsApp app = (NewsApp)((AppCompatActivity)ctx).getApplication();

        };

    @Override
    public int getItemCount() {
        return data.size();
    }


class CommentViewHolder extends RecyclerView.ViewHolder{

    TextView commenterName;
    TextView commentText;
    ConstraintLayout row;



    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);

        commenterName = itemView.findViewById(R.id.commenterName);
        commentText = itemView.findViewById(R.id.commentText);
        row = itemView.findViewById(R.id.commentrow_list);

    }

}

}
