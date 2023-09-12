package edu.sabanciuniv.cs310assignment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.w3c.dom.Text;

public class ActivityDetails extends AppCompatActivity {

    ImageView imgDetails;
    TextView txtTitleDetail;
    TextView txtTextDetail;
    TextView txtDateDetail;

    TextView commentsButton;


    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            News news = (News) msg.obj;

            txtTitleDetail.setText(news.getTitle());
            txtTextDetail.setText(news.getText());
            txtDateDetail.setText(news.getDate());


            NewsRepository repo = new NewsRepository();
            repo.downloadImage(((NewsApp)getApplication()).srv,imgHandler,news.getImage());

            return true;
        }
    });

    Handler imgHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            Bitmap img = (Bitmap) msg.obj;
            imgDetails.setImageBitmap(img);

            return true;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.newsdetails);



        int id = getIntent().getIntExtra("id",1);

        imgDetails =findViewById(R.id.imgDetails);
        txtTitleDetail = findViewById(R.id.txtTitle);
        txtDateDetail = findViewById(R.id.txtDate);
        txtTextDetail = findViewById(R.id.textText);

        Toolbar toolbar = findViewById(R.id.DetailsToolBar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Details");

        commentsButton = findViewById(R.id.commentsButton);

        commentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDetails.this, Comments.class);
                intent.putExtra("news_id", id);
                startActivity(intent);
            }
        });
        NewsRepository repo = new NewsRepository();

        repo.getDataById(((NewsApp)getApplication()).srv,dataHandler,id);


    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }

        return true;
    }

}
