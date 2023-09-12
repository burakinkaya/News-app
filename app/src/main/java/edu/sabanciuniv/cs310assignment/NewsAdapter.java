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

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private Context ctx;
    private List<News> items;

    public NewsAdapter(Context ctx, List<News> items) {
        this.ctx = ctx;
        this.items = items;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root= LayoutInflater.from(ctx).inflate(R.layout.newsrowlayout,parent,false);

        NewsViewHolder holder = new NewsViewHolder(root);
        holder.setIsRecyclable(false);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        holder.txtListDate.setText(items.get(holder.getAdapterPosition()).getDate());
        holder.txtListTitle.setText(items.get(holder.getAdapterPosition()).getTitle());
        NewsApp app = (NewsApp)((AppCompatActivity)ctx).getApplication();

        holder.downloadImage(app.srv,items.get(holder.getAdapterPosition()).getImage());

        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx,ActivityDetails.class);
                i.putExtra("id",items.get(holder.getAdapterPosition()).getId());
                ctx.startActivity(i);


            }
        });

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView txtListDate,txtListTitle;
        ImageView imgList;
        ConstraintLayout row;
        boolean imageDownloaded;

        Handler imgHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {

                Bitmap img = (Bitmap)msg.obj;
                imgList.setImageBitmap(img);
                imageDownloaded = true;
                return true;
            }
        });


        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            txtListDate = itemView.findViewById(R.id.txtListDate);
            txtListTitle = itemView.findViewById(R.id.txtListTitle);
            imgList = itemView.findViewById(R.id.imgList);
            row = itemView.findViewById(R.id.row_list);

        }

        public void downloadImage(ExecutorService srv, String path){

            if (!imageDownloaded){

                NewsRepository repo = new NewsRepository();
                repo.downloadImage(srv,imgHandler,path);


            }



        }

    }


}
