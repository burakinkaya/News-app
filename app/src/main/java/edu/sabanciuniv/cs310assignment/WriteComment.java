package edu.sabanciuniv.cs310assignment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;import org.json.JSONObject;

public class WriteComment extends AppCompatActivity {

        EditText EditName=null;

        EditText EditComment=null;

        TextView txtOutput=null;

        Button sendButton;

        ProgressBar prg;

        Handler sendCommentHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            String retVal = msg.obj.toString();
            txtOutput.setText(retVal);


            prg.setVisibility(View.INVISIBLE);
            return true;

        }
    });


        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);

            setContentView(R.layout.write_comment);

            Toolbar toolbar = findViewById(R.id.PostCommentsToolBar);

            setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            int id = getIntent().getIntExtra("news_id",1);

            EditName = findViewById(R.id.EditName);
            EditComment = findViewById(R.id.EditComment);
            sendButton = findViewById(R.id.sendButton);



            sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    prg = findViewById(R.id.progressBarSendComment);


                    prg.setVisibility(View.VISIBLE);


                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("name", EditName.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        obj.put("text", EditComment.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        obj.put("news_id", id);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if (EditName.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "One of your inputs are empty!", Toast.LENGTH_SHORT).show();
                        prg.setVisibility(View.INVISIBLE);
                    }

                    else if (EditComment.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "One of your inputs are empty!", Toast.LENGTH_SHORT).show();
                        prg.setVisibility(View.INVISIBLE);
                    }

                    else{
                        String jsonStringed = obj.toString();
                        ExecutorService srv = ((NewsApp)getApplication()).srv;
                        NewsRepository repo = new NewsRepository();
                        repo.sendCommentJson(srv,sendCommentHandler,obj);
                    }



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
