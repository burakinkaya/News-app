package edu.sabanciuniv.cs310assignment;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import edu.sabanciuniv.cs310assignment.NewsRepository;

public class MainActivity extends AppCompatActivity {

    ProgressBar prg;

    TabLayout tabLayout;

    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.TabLayout);

        viewPager = findViewById(R.id.fragmentContainer);

        tabLayout.setupWithViewPager(viewPager);


        NewsFragmentAdapter newsFragmentAdapter = new NewsFragmentAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        NewsRepository repo = new NewsRepository();


        newsFragmentAdapter.addFragment(new FragmentA(),"economics");

        newsFragmentAdapter.addFragment(new FragmentB(),"sports");

        newsFragmentAdapter.addFragment(new FragmentC(),"politics");

        viewPager.setAdapter(newsFragmentAdapter);

    }



}