package com.example.week3day4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.week3day4.GithubRepository.Repository;
import com.example.week3day4.GithubRepository.Result;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import static com.example.week3day4.Constants.GITHUB_REPO_URL;

public class Main2Activity extends AppCompatActivity {
    RecyclerView rvView;
    RecyclerViewAdapter rvAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        OkHttpHelper.ascyncRepoOkHttpApi(GITHUB_REPO_URL);
        rvView = findViewById(R.id.rvView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void repoEvent(RepoListEvent event){
        if(event != null){
            Repository repoResponse = event.getRepository();
            ArrayList<Result> repositoryArrayList = new ArrayList<>(repoResponse.getResults());
            System.out.println("The Repo Name is: " + repositoryArrayList.get(0).getName());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            rvView.setLayoutManager(layoutManager);
            rvAdapter = new RecyclerViewAdapter(repositoryArrayList);

            rvView.setAdapter(rvAdapter);
            //Log.d("TAG", "The Repo Name is: " + repositoryArrayList.get(0).getName());

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("TAG", "Register ");
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("TAG", "UnRegister ");
        EventBus.getDefault().unregister(this);
    }
}
