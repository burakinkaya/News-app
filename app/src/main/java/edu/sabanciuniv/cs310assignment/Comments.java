package edu.sabanciuniv.cs310assignment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Comments extends AppCompatActivity {

    TextView text;

    RecyclerView recyclerView;

    ImageView postCommentImage;

    ProgressBar prg;

    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            List<Comment> comments = (List<Comment>)msg.obj;
            CommentAdapter adp = new CommentAdapter(Comments.this,comments);
            recyclerView.setAdapter(adp);
            adp.notifyDataSetChanged();
            recyclerView.setVisibility(View.VISIBLE);
            prg.setVisibility(View.INVISIBLE);

            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {

     

        super.onCreate(savedInstanceState);

        setContentView(R.layout.comments);
        Toolbar toolbar = findViewById(R.id.CommentsToolBar);

        int id = getIntent().getIntExtra("news_id",1);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Comments");

        prg = findViewById(R.id.progressBarComments);


        recyclerView = findViewById(R.id.recyclerViewListofComments);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setVisibility(View.INVISIBLE);

        prg.setVisibility(View.VISIBLE);

        NewsRepository repo = new NewsRepository();

        repo.getCommentsByNewsId(((NewsApp)getApplication()).srv,dataHandler,id);



        postCommentImage = findViewById(R.id.postCommentID);

        postCommentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Comments.this, WriteComment.class);
                intent.putExtra("news_id", id);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
