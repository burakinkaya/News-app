package edu.sabanciuniv.cs310assignment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FragmentB extends Fragment {

    Context thiscontext;
    private View view;
    private RecyclerView recyclerView;
    ProgressBar prg;

    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            List<News> items = (List<News>)msg.obj;
            FragmentAdapter adp = new FragmentAdapter(thiscontext,items);
            recyclerView.setAdapter(adp);
            adp.notifyDataSetChanged();
            recyclerView.setVisibility(View.VISIBLE);

            prg.setVisibility(View.INVISIBLE);

            return true;
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thiscontext = container.getContext();
        view = inflater.inflate(R.layout.fragment_b, container, false);
        prg = view.findViewById(R.id.progressBarFragmentB);
        prg.setVisibility(View.VISIBLE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    private void initComponents() {
        recyclerView=view.findViewById(R.id.recyclerViewListofB);
        NewsRepository repo = new NewsRepository();
        repo.getDataByCategoryId(((NewsApp)getActivity().getApplication()).srv,dataHandler,2);

    }
}