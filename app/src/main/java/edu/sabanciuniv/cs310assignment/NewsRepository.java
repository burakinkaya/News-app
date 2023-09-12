package edu.sabanciuniv.cs310assignment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class NewsRepository {


    public void getAllData (ExecutorService srv, Handler uiHandler){

        srv.execute(()->{
            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getall");
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }

                List<News> Newsitems = new ArrayList<>();


                String jsonString = buffer.toString();

                JSONObject jsonObject = new JSONObject(jsonString);

                int id = jsonObject.getInt("serviceMessageCode");
                String title = jsonObject.getString("serviceMessageText");

                JSONArray array = jsonObject.getJSONArray("items");

                for (int i = 0; i < array.length(); i++) {
                    JSONObject current = array.getJSONObject(i);

                    News news = new News(current.getInt("id"),
                            current.getString("title"),
                            current.getString("text"),
                            current.getString("date"),
                            current.getString("image"),
                            current.getString("categoryName"));
                    Newsitems.add(news);


                }

                Message msg = new Message();
                msg.obj = Newsitems;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });

    }

    public void getAllCategories (ExecutorService srv, Handler uiHandler){

        srv.execute(()->{
            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getallnewscategories");
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }

                List<Categories> Categoriesitems = new ArrayList<>();


                String jsonString = buffer.toString();

                JSONObject jsonObject = new JSONObject(jsonString);

                int id = jsonObject.getInt("serviceMessageCode");
                String title = jsonObject.getString("serviceMessageText");

                JSONArray array = jsonObject.getJSONArray("items");

                for (int i = 0; i < array.length(); i++) {
                    JSONObject current = array.getJSONObject(i);

                    Categories categories = new Categories(current.getInt("id"),
                            current.getString("name"));
                    Categoriesitems.add(categories);
                }



                Message msg = new Message();
                msg.obj = Categoriesitems;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });


    }

    public void getDataByCategoryId(ExecutorService srv, Handler uiHandler,int id){


        srv.execute(()->{
            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getbycategoryid/" + String.valueOf(id));
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }

                String jsonString = buffer.toString();

                JSONObject jsonObject = new JSONObject(jsonString);

                int myyid = jsonObject.getInt("serviceMessageCode");
                String title = jsonObject.getString("serviceMessageText");

                JSONArray array = jsonObject.getJSONArray("items");



                List<News> NewsitemsbyCategory = new ArrayList<>();


                for (int i = 0; i < array.length(); i++) {
                    JSONObject current = array.getJSONObject(i);

                    News news = new News(current.getInt("id"),
                            current.getString("title"),
                            current.getString("text"),
                            current.getString("date"),
                            current.getString("image"),
                            current.getString("categoryName"));
                    NewsitemsbyCategory.add(news);
                }


                Message msg = new Message();
                msg.obj = NewsitemsbyCategory;
                uiHandler.sendMessage(msg);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });

    }

    public void getDataById(ExecutorService srv, Handler uiHandler,int id){


        srv.execute(()->{
            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getnewsbyid/" + String.valueOf(id));
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }

                String jsonString = buffer.toString();

                JSONObject jsonObject = new JSONObject(jsonString);

                int myid = jsonObject.getInt("serviceMessageCode");
                String title = jsonObject.getString("serviceMessageText");

                JSONArray array = jsonObject.getJSONArray("items");

                News news = new News();

                for (int i = 0; i < array.length(); i++) {
                    JSONObject current = array.getJSONObject(i);

                    news.setId(current.getInt("id"));
                    news.setTitle(current.getString("title"));
                    news.setDate(current.getString("date"));
                    news.setText(current.getString("text"));
                    news.setImage(current.getString("image"));
                    news.setCategoryName(current.getString("categoryName"));

                }





                Message msg = new Message();
                msg.obj = news;
                uiHandler.sendMessage(msg);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });

    }

    public void getCommentsByNewsId(ExecutorService srv, Handler uiHandler,int id){


        srv.execute(()->{
            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getcommentsbynewsid/" + String.valueOf(id));

                HttpURLConnection conn =(HttpURLConnection)url.openConnection();


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }


                String jsonString = buffer.toString();

                JSONObject jsonObject = new JSONObject(jsonString);

                int myyid = jsonObject.getInt("serviceMessageCode");
                String title = jsonObject.getString("serviceMessageText");

                JSONArray array = jsonObject.getJSONArray("items");

                List<Comment> CommentsbyNews = new ArrayList<>();

                for (int i = 0; i < array.length(); i++) {
                    JSONObject current = array.getJSONObject(i);

                    Comment comment = new Comment(current.getInt("id"),
                            current.getInt("news_id"),
                            current.getString("text"),
                            current.getString("name"));
                    CommentsbyNews.add(comment);
                }


                Message msg = new Message();
                msg.obj = CommentsbyNews;
                uiHandler.sendMessage(msg);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });

    }



    public void downloadImage(ExecutorService srv, Handler uiHandler,String path){
        srv.execute(()->{
            try {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                Bitmap bitmap =  BitmapFactory.decodeStream(conn.getInputStream());

                Message msg = new Message();
                msg.obj = bitmap;
                uiHandler.sendMessage(msg);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


    }

    public void sendCommentJson(ExecutorService srv, Handler uiHandler, JSONObject jsonObject){

        srv.execute(()->{

            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/savecomment");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type","application/JSON");


                BufferedOutputStream writer =
                        new BufferedOutputStream(conn.getOutputStream());


                writer.write(jsonObject.toString().getBytes(StandardCharsets.UTF_8));
                writer.flush();

                BufferedReader reader
                        = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();

                String line ="";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }

                JSONObject retVal = new JSONObject(buffer.toString());

                conn.disconnect();


                String retValStr = "service Message Code:" + retVal.getString("serviceMessageCode");

                Message msg = new Message();
                msg.obj = retValStr;

                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        });



    }




}


